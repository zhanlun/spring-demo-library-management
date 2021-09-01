package io.github.zhanlun.librarymanagement.visitor;

import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public List<Checkout> getCheckouts() {
        return checkoutRepository.findAll();
    }

    public Checkout getCheckout(Integer id) throws NotFoundException {
        Optional<Checkout> checkoutOptional = checkoutRepository.findById(id);
        if (checkoutOptional.isEmpty()) {
            throw NotFoundException.createWith("Checkout with id " + id + " not found");
        }
        return checkoutOptional.get();
    }

    public Checkout addCheckout(Checkout checkout) {
        if (checkout.getCheckoutDate() == null) {
            Calendar calendar = Calendar.getInstance();
            Date now = new Date();
            calendar.setTime(now);
            calendar.add(Calendar.DATE, 30);
            Date due = calendar.getTime();

            checkout.setCheckoutDate(now);
            checkout.setDueDate(due);
        }
        return checkoutRepository.save(checkout);
    }

    public Checkout updateCheckout(Integer id, Checkout checkout) throws NotFoundException {
        if (!checkoutRepository.existsById(id)) {
            throw NotFoundException.createWith("Checkout with id " + id + " not found");
        }
        checkout.setId(id);
        return checkoutRepository.save(checkout);
    }

    public Page<Checkout> getCheckouts(Map<String, String> allRequestParams) {
        int start = Integer.parseInt(allRequestParams.get("_start"));
        int end = Integer.parseInt(allRequestParams.get("_end"));
        int pageSize = end - start;
        int pageStart = start / pageSize;
        String sort = allRequestParams.get("_sort");
        boolean isDesc = allRequestParams.get("_order").equalsIgnoreCase("DESC");
        if (sort == null) {
            sort = "name";
        }
        if (sort.equals("book.id")) {
            sort = "book.name";
        }
        Sort sortBy = Sort.by(sort);
        sortBy = isDesc ? sortBy.descending() : sortBy.ascending();

        Pageable pageable = PageRequest.of(pageStart, pageSize, sortBy);

        // filters
        String bookIdString = allRequestParams.get("book.id");
        Integer bookId = bookIdString == null ? null : Integer.parseInt(bookIdString);
        String visitorIdString = allRequestParams.get("visitor.id");
        Integer visitorId = visitorIdString == null ? null : Integer.parseInt(visitorIdString);

        return checkoutRepository.search(pageable, bookId, visitorId);
    }

    public List<Checkout> getCheckoutsByIdList(Integer[] idList) {
        return checkoutRepository.findAllById(List.of(idList));
    }
}
