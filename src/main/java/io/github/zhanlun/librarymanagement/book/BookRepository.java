package io.github.zhanlun.librarymanagement.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b LEFT JOIN b.checkouts bc " +
            "WHERE (:name is null OR LOWER(b.name) LIKE LOWER(concat('%', :name, '%'))) " +
            "AND (:subjectId is null OR b.subject.id = :subjectId) ")
    Page<Book> search(Pageable pageable, @Param("name") String name, @Param("subjectId") Integer subjectId);

    @Query("SELECT DISTINCT b FROM Book b WHERE b.name LIKE %:name%")
    @Transactional(readOnly = true)
    List<Book> findByName(@Param("name") String name);
}
