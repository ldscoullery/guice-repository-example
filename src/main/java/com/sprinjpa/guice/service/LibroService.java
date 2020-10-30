package com.sprinjpa.guice.service;

import com.sprinjpa.guice.model.Libro;
import com.sprinjpa.guice.repository.LibroRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class LibroService {

    private LibroRepository libroRepository;

    @Inject
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro get(long id) {
        return libroRepository.findOne(id);
    }

//    public List<Libro> listAll() {
//        return libroRepository.findAll();
//    }

    public Libro filterByUUID(UUID uuid) {
        return libroRepository.findByUuidIs(uuid);
    }

    public List<Libro> filter(String subString) {
        return libroRepository.findByTituloContainingIgnoreCase(subString);
    }



    /** @throws IllegalStateException if the entity exists. */
    @Transactional
    public void add(Libro libro) {
        boolean exists = libroRepository.exists(libro.getId());
        if(exists)
            throw new IllegalStateException("Libro with id:" + libro.getId() + " already exists");
        libroRepository.save(libro);
    }

    /** @throws IllegalStateException if the entity exists. */
    @Transactional
    public void update(Libro libro) {
        boolean exists = libroRepository.exists(libro.getId());
        if(!exists)
            throw new IllegalStateException("Libro with id:" + libro.getId() + " not exists");
        libroRepository.save(libro);
    }

    /** @throws IllegalStateException if one of the entities exists. */
    @Transactional
    public void addAll(Collection<Libro> libros) {
        for (Libro product : libros) {
            add(product);
        }
    }
}
