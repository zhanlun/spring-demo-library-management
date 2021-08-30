package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
