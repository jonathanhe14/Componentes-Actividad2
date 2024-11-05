package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.imp;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Sede;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.repository.ISedeRepo;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeService implements ISedeService {

    @Autowired
    ISedeRepo sedeRepo;

    @Override
    public Long saveSede(Sede nueva) {
        Sede saved = sedeRepo.save(nueva);
        return saved.getId();
    }

    @Override
    public List<Sede> getSedes() {
        return sedeRepo.findAll();
    }

    @Override
    public Sede getSedes(long id) {
        return sedeRepo.getReferenceById(id);
    }

    @Override
    public void updateSedes(Sede nuevaSede) {
        sedeRepo.save(nuevaSede);
    }

    @Override
    public void deleteSede(long id) {
        sedeRepo.deleteById(id);
    }
}
