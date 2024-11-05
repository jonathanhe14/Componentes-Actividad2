package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.imp;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Libro;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.repository.LibroRepo;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImp implements LibroService {

    @Autowired
    LibroRepo libroRepo;
    @Override
    public Long saveLibro(Libro nuevo) {
        Libro saved = libroRepo.save(nuevo);
        return saved.getId();
    }

    @Override
    public List<Libro> getLibros() {
        return libroRepo.findAll();
    }

    @Override
    public Libro getLibro(long id) {
        return libroRepo.getReferenceById(id);
    }

    @Override
    public void updateLibro(Libro nuevoLibro) {
        libroRepo.save(nuevoLibro);
    }

    @Override
    public void deleteLibro(long id) {
        libroRepo.deleteById(id);
    }
}
