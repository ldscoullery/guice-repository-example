package com.sprinjpa.guice.repository;

import com.sprinjpa.guice.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LibroRepository extends CrudRepository<Libro, Long> {

//    /** You can define JPA queries **/
    @Query("select p from Libro p where p.titulo = :titulo")
    public List<Libro> findByTituloIs(@Param("titulo") String titulo);

    //    /** You can define JPA queries **/
    @Query("select p from Libro p where p.uuid = :uuid")
    public Libro findByUuidIs(@Param("uuid") UUID uuid);

    /** No need to define @Query here, Spring DATA-JPA supports
     *  resolution of keywords inside method names. **/
    public List<Libro> findByTituloContainingIgnoreCase(String searchString);

}
