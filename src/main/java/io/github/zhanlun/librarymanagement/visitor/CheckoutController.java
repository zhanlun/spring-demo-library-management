package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkouts")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<List<Checkout>> getCheckouts() {
        List<Checkout> checkouts = checkoutService.getCheckouts();
        return ResponseEntity.ok().body(checkouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getCheckout(@PathVariable("id") Integer id) {
        Checkout checkout = checkoutService.getCheckout(id);
        return ResponseEntity.ok().body(checkout);
    }

    @PostMapping
    public ResponseEntity<Checkout> addCheckout(@RequestBody Checkout checkout) {
        Checkout createdCheckout = checkoutService.addCheckout(checkout);
        return new ResponseEntity<>(createdCheckout, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checkout> updateCheckout(@RequestBody Checkout checkout, @PathVariable("id") Integer id) {
        Checkout createdCheckout = checkoutService.updateCheckout(id, checkout);
        return ResponseEntity.ok().body(createdCheckout);
    }
}
