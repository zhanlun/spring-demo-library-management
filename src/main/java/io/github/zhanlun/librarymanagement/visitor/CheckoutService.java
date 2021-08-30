package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Checkout getCheckout(Integer id) {
        Optional<Checkout> checkoutOptional = checkoutRepository.findById(id);
        if (checkoutOptional.isEmpty()) {
            throw new IllegalStateException();
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

    public Checkout updateCheckout(Integer id, Checkout checkout) {
        if (!checkoutRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        checkout.setId(id);
        return checkoutRepository.save(checkout);
    }
}
