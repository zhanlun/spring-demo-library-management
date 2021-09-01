package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    @Query("SELECT DISTINCT v FROM Visitor v WHERE v.lastName LIKE %:lastName%")
    List<Visitor> findByLastName(@Param("lastName") String lastName);

    @Query("SELECT DISTINCT v FROM Visitor v WHERE v.firstName LIKE %:firstName%")
    List<Visitor> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT DISTINCT v FROM Visitor v WHERE v.firstName LIKE %:name% OR v.lastName LIKE %:name% ")
    List<Visitor> searchByName(@Param("name") String name);

    @Query("SELECT v FROM Visitor v  WHERE (:name is null OR LOWER(v.firstName) LIKE LOWER(concat('%', :name, '%')) OR LOWER(v.lastName) LIKE LOWER(concat('%', :name, '%'))) ")
    Page<Visitor> search(Pageable pageable, @Param("name") String name);
}
