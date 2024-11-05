package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.imp;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Region;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.repository.IRegionRepo;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements IRegionService {

    @Autowired
    IRegionRepo regionRepo;

    @Override
    public Long saveRegion(Region nuevo) {
        Region saved = regionRepo.save(nuevo);
        return saved.getId();
    }

    @Override
    public List<Region> getRegion() {

        return regionRepo.findAll();
    }

    @Override
    public Region getRegion(long id) {
        return regionRepo.getReferenceById(id);
    }

    @Override
    public void updateRegion(Region nuevaRegion) {
        regionRepo.save(nuevaRegion);
    }

    @Override
    public void deleteRegion(long id) {
        regionRepo.deleteById(id);
    }
}
