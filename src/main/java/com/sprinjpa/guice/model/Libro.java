package com.sprinjpa.guice.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @Column(name= "libro_id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @NaturalId
    @Column(name = "sbn", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(Long id, String titulo, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro(Long id, String titulo, UUID uuid, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Libro libro = (Libro) o;
//        return Objects.equals(id, libro.id) &&
//                Objects.equals(titulo, libro.titulo) &&
//                Objects.equals(autor, libro.autor);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, titulo, autor);
//    }
//
//    @Override
//    public String toString() {
//        return "Libro{" +
//                "id=" + id +
//                ", titulo='" + titulo + '\'' +
//                ", autor=" + autor +
//                '}';
//    }
}

/**
 * Las tablas van a tener la siguiente estructura
 * AUTOR
 * |  LIBRO_ID  | TITULO        | AUTOR_ID  |
 * |    8       | Caos Total    | 3         |
 * y queremos que esta tabla tenga la relación con Autor
 */
