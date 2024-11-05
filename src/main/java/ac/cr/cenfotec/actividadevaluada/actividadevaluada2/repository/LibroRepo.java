package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.repository;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro,Long> {
}