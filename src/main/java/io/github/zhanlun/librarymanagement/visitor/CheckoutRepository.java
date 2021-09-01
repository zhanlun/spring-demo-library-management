package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    @Query("SELECT c FROM Checkout c WHERE (:bookId is null OR c.book.id = :bookId) " +
            "AND (:visitorId is null OR c.visitor.id = :visitorId) ")
    Page<Checkout> search(Pageable pageable, Integer bookId, Integer visitorId);
}
