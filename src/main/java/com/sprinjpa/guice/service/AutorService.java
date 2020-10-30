package com.sprinjpa.guice.service;

import com.sprinjpa.guice.model.Autor;
import com.sprinjpa.guice.repository.AutorRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class AutorService {

    private AutorRepository autorRepository;

    @Inject
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor get(long id) {
        return autorRepository.findOne(id);
    }

    public List<Autor> listAll() {
        return autorRepository.findAll();
    }

    public List<Autor> filter(String subString) {
        return autorRepository.findByNameContainingIgnoreCase(subString);
    }

    /** @throws IllegalStateException if the entity exists. */
    @Transactional
    public void add(Autor autor) {
        boolean exists = autorRepository.exists(autor.getId());
        if(exists)
            throw new IllegalStateException("Autor with id:" + autor.getId() + " already exists");
        autorRepository.save(autor);
    }

    /** @throws IllegalStateException if one of the entities exists. */
    @Transactional
    public void addAll(Collection<Autor> autors) {
        for (Autor product : autors) {
            add(product);
        }
    }
}
