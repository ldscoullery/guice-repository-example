package com.sprinjpa.guice.model;

import com.sprinjpa.guice.enums.ProductStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
	
    @Id
	private Long id;

    @Column(name="name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private ProductStatus status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created", insertable = true, updatable = false)
	private Date dateCreated;

	@PrePersist
	public void prePersist() {
		this.dateCreated = new Date();
	}

	public Product() {
	}
	public Product(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Product(Long id, String name, ProductStatus productStatus) {
		super();
		this.id = id;
		this.name = name;
		this.status = productStatus;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
	
}
