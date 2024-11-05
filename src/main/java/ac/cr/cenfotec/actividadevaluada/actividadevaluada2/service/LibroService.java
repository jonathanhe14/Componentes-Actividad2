package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Libro;

import java.util.List;

public interface LibroService {

    Long saveLibro(Libro nuevo);

    List<Libro> getLibros();

    Libro getLibro(long id);

    void updateLibro(Libro nuevoLibro);

    void deleteLibro(long id);
}

