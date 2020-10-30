package com.sprinjpa.guice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @Column(name= "autor_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nacionality")
    private String nacionality;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // mappedBy va a ser el campo en la otra clase. Si no especifico
    // el FetchType, por default es FetchType.LAZY a menos que especifique otro
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(Long id, String nombre, String nacionalidad) {
        this.id = id;
        this.name = nombre;
        this.nacionality = nacionalidad;
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

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                '}';
    }
}

/**
 * Las tablas van a tener la siguiente estructura
 * AUTOR
 * |  AUTOR_ID  | NOMBRE        | NACIONALIDAD |
 * |    3       | Juan Perez    | Español      |
 * y queremos que esta tabla quede limpia, es decir la referencia la tenga Libro
 */
