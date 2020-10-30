package com.sprinjpa.guice.repository;

import java.util.List;

import com.sprinjpa.guice.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** 
 * Guice-repository uses Spring Spring DATA-JPA's JpaRepository interface, so you can
 * use it exactly the same way as Spring DATA-JPA. 
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	/** You can define JPA queries **/
    @Query("select p from Product p where p.name = :name")
    public List<Product> findByNameIs(@Param("name") String name);

	/** No need to define @Query here, Spring DATA-JPA supports 
	 *  resolution of keywords inside method names. **/
    public List<Product> findByNameContainingIgnoreCase(String searchString);
}
