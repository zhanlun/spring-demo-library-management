package io.github.zhanlun.librarymanagement.visitor;

import io.github.zhanlun.librarymanagement.exception.BadRequestException;
import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/checkouts")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<List<Checkout>> getCheckouts(@RequestParam Map<String, String> allRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        List<Checkout> results;
        long total;
        Page<Checkout> pageResult = checkoutService.getCheckouts(allRequestParams);
        results = pageResult.toList();
        total = pageResult.getTotalElements();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<List<Checkout>> getCheckoutsById(@RequestParam(name = "id", required = false) Integer[] idList) {
        HttpHeaders headers = new HttpHeaders();
        List<Checkout> results;
        long total;
        List<Checkout> subjectsByIdList = checkoutService.getCheckoutsByIdList(idList);
        results = subjectsByIdList;
        total = subjectsByIdList.size();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getCheckout(@PathVariable("id") Integer id) throws NotFoundException {
        Checkout checkout = checkoutService.getCheckout(id);
        return ResponseEntity.ok().body(checkout);
    }

    @PostMapping
    public ResponseEntity<Checkout> addCheckout(@Valid @RequestBody Checkout checkout) throws BadRequestException {
        Checkout createdCheckout = checkoutService.addCheckout(checkout);
        return new ResponseEntity<>(createdCheckout, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checkout> updateCheckout(@Valid @RequestBody Checkout checkout, @PathVariable("id") Integer id) throws NotFoundException, BadRequestException {
        Checkout createdCheckout = checkoutService.updateCheckout(id, checkout);
        return ResponseEntity.ok().body(createdCheckout);
    }
}
