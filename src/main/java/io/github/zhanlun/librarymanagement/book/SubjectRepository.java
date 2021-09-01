package io.github.zhanlun.librarymanagement.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findByName(String name);

    @Query("SELECT s FROM Subject s")
    Page<Subject> search(Pageable pageable);
}
