package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Sede;

import java.util.List;

public interface ISedeService {

    Long saveSede(Sede nueva);

    List<Sede> getSedes();

    Sede getSedes(long id);

    void updateSedes(Sede nuevaSede);

    void deleteSede(long id);
}
