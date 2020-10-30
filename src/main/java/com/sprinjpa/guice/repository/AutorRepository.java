package com.sprinjpa.guice.repository;

import com.sprinjpa.guice.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    /** You can define JPA queries **/
    @Query("select p from Autor p where p.name = :name")
    public List<Autor> findByNameIs(@Param("name") String name);

    /** No need to define @Query here, Spring DATA-JPA supports
     *  resolution of keywords inside method names. **/
    public List<Autor> findByNameContainingIgnoreCase(String searchString);
}
