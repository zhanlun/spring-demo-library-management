package io.github.zhanlun.librarymanagement.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT DISTINCT b FROM Book b WHERE b.name LIKE %:name%")
    @Transactional(readOnly = true)
    List<Book> findByName(@Param("name") String name);
}
