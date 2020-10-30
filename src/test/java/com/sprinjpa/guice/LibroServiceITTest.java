package com.sprinjpa.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.sprinjpa.guice.model.Autor;
import com.sprinjpa.guice.model.Libro;
import com.sprinjpa.guice.repository.LibroRepository;
import com.sprinjpa.guice.service.LibroService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class LibroServiceITTest {

    private static final String TEST_PERSISTENCE_UNIT_NAME = "testDB";
    private static final String REPOSITORIES_BASE_PACKAGE_NAME = LibroRepository.class.getPackage().getName();

    private LibroService libroService;
    private LibroRepository libroRepository;

    public LibroServiceITTest() {
        Injector injector = createInjector();
        libroService = injector.getInstance(LibroService.class);
        libroRepository = injector.getInstance(LibroRepository.class);
    }

    private static Injector createInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT,
                new GuiceModule(TEST_PERSISTENCE_UNIT_NAME, REPOSITORIES_BASE_PACKAGE_NAME));
    }

    @Before
    public void clear() {
        libroRepository.deleteAll();
    }

    @Test
    public void testAdd() {
        UUID uuid1 = generateType4UUID();
        UUID uuid2 = generateType4UUID();
        UUID uuid3 = generateType4UUID();
        UUID uuid4 = generateType4UUID();
        UUID uuid5 = generateType4UUID();

        Autor a1 = new Autor(1L, "Pablo Perez", "Español");
        Autor a2 = new Autor(2L, "Elena Gomez", "Mexicana");
        Autor a3 = new Autor(3L, "Miguel Lopez", "Chileno");

        Libro l1 = new Libro(1L, "Programar en Java", uuid1, a2);
        Libro l2 = new Libro(2L, "Como vestirse con estilo", uuid2, a3);
        Libro l3 = new Libro(3L, "Como cocinar sin quemarsse", uuid3, a1);
        Libro l4 = new Libro(4L, "Programar en Cobol es divertido", uuid4, a2);
        Libro l5 = new Libro(5L, "Programar en Cobol no es divertido", uuid5, a2);

        a1.setLibros(Arrays.asList(l3));
        a2.setLibros(Arrays.asList(l1, l4, l5));
        a3.setLibros(Arrays.asList(l2));


        libroService.add(l1); // va a fallar por la constraint de fk de autor. Primero hay que guardar al autor
        libroService.add(l2);

        Libro libro = libroService.filterByUUID(uuid1);

        Libro libro2 = libroService.get(2L);

    }

    public static UUID generateType4UUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
