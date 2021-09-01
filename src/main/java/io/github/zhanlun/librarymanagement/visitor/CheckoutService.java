package io.github.zhanlun.librarymanagement.visitor;

import io.github.zhanlun.librarymanagement.book.BookRepository;
import io.github.zhanlun.librarymanagement.exception.BadRequestException;
import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static io.github.zhanlun.librarymanagement.model.PagingUtil.getPageable;

@Service
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final BookRepository bookRepository;
    private final VisitorRepository visitorRepository;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository, BookRepository bookRepository, VisitorRepository visitorRepository) {
        this.checkoutRepository = checkoutRepository;
        this.bookRepository = bookRepository;
        this.visitorRepository = visitorRepository;
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

    public Checkout addCheckout(Checkout checkout) throws BadRequestException {
        if (checkout.getBook() == null || !bookRepository.existsById(checkout.getBook().getId())) {
            throw BadRequestException.createWith("Book is not valid");
        }
        if (checkout.getVisitor() == null || !visitorRepository.existsById(checkout.getVisitor().getId())) {
            throw BadRequestException.createWith("Visitor is not valid");
        }
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

    public Checkout updateCheckout(Integer id, Checkout checkout) throws NotFoundException, BadRequestException {
        if (!checkoutRepository.existsById(id)) {
            throw NotFoundException.createWith("Checkout with id " + id + " not found");
        }
        if (checkout.getBook() == null || !bookRepository.existsById(checkout.getBook().getId())) {
            throw BadRequestException.createWith("Book is not valid");
        }
        if (checkout.getVisitor() == null || !visitorRepository.existsById(checkout.getVisitor().getId())) {
            throw BadRequestException.createWith("Visitor is not valid");
        }
        checkout.setId(id);
        return checkoutRepository.save(checkout);
    }

    public Page<Checkout> getCheckouts(Map<String, String> allRequestParams) {
        Pageable pageable = getPageable(allRequestParams);

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
