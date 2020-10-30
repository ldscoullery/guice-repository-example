package com.sprinjpa.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.sprinjpa.guice.model.Autor;
import com.sprinjpa.guice.model.Libro;
import com.sprinjpa.guice.repository.AutorRepository;
import com.sprinjpa.guice.repository.LibroRepository;
import com.sprinjpa.guice.service.AutorService;
import com.sprinjpa.guice.service.LibroService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class AutorServiceITTest {

    private static final String TEST_PERSISTENCE_UNIT_NAME = "testDB";
    private static final String REPOSITORIES_BASE_PACKAGE_NAME = AutorRepository.class.getPackage().getName();

    private AutorService autorService;
    private AutorRepository autorRepository;
    private LibroService libroService;
    private LibroRepository libroRepository;

    public AutorServiceITTest() {
        Injector injector = createInjector();
        autorService = injector.getInstance(AutorService.class);
        autorRepository = injector.getInstance(AutorRepository.class);
        libroService = injector.getInstance(LibroService.class);
        libroRepository = injector.getInstance(LibroRepository.class);
    }

    private static Injector createInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT,
                new GuiceModule(TEST_PERSISTENCE_UNIT_NAME, REPOSITORIES_BASE_PACKAGE_NAME));
    }

    @Before
    public void clear() {
        autorRepository.deleteAll();
    }

    @Test
    public void testAdd() {
        Autor a1 = new Autor(1L, "Pablo Perez", "Español");
        Autor a2 = new Autor(2L, "Elena Gomez", "Mexicana");
        Autor a3 = new Autor(3L, "Miguel Lopez", "Chileno");

        Libro l1 = new Libro(1L, "Programar en Java", a2);
        Libro l2 = new Libro(2L, "Como vestirse con estilo", a3);
        Libro l3 = new Libro(3L, "Como cocinar sin quemarse", a1);
        Libro l4 = new Libro(4L, "Programar en Cobol es divertido", a2);
        Libro l5 = new Libro(5L, "Hibernate basico", a2);

        a1.setLibros(Arrays.asList(l3));
        a2.setLibros(Arrays.asList(l1, l4, l5));
        a3.setLibros(Arrays.asList(l2));

        autorService.add(a1);
        autorService.add(a2);
        autorService.add(a3);

        Libro result = libroService.filter("Java").get(0);

        assertNotNull(autorService.get(1l));
        assertNotNull(autorService.get(2l));
        assertNotNull(autorService.get(3l));

    }

    @Test
    public void testFilter() {
        autorService.add(new Autor(1L, "Eduardo", "Galeano"));
        autorService.add(new Autor(2L, "Eckhart", "Tolle"));

        List<Autor> filteredAuthors = autorService.filter("Tolle");
        assertTrue(filteredAuthors.size() == 1);
        assertTrue(filteredAuthors.get(0).getId() == 2l);
    }

    @Test
    public void testAddAll() {
        autorService.addAll(Arrays.asList(
                new Autor(1L, "Eduardo", "Galeano"),
                new Autor(2L, "Eckhart", "Tolle"),
                new Autor(3L, "Isabel", "Allende")
        ));

        assertNotNull(autorService.get(1l));
        assertNotNull(autorService.get(2l));
        assertNotNull(autorService.get(3l));

    }

    @Test
    public void testAddAllRollback() {
        try {
            autorService.addAll(Arrays.asList(
                    new Autor(1L, "Eduardo", "Galeano"),
                    new Autor(2L, "Eckhart", "Tolle"),
                    new Autor(1L, "Isabel", "Allende")
            ));
            fail("Expected exception for id duplication");
        } catch (RuntimeException e) {
            //Expected rollback done
            assertNull(autorService.get(1l));
            assertNull(autorService.get(2l));
        }
    }
}
