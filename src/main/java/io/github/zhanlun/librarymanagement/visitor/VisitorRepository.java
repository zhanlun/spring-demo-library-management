package io.github.zhanlun.librarymanagement.visitor;

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
}
