package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Region;

import java.util.List;

public interface IRegionService {

    Long saveRegion(Region nuevo);

    List<Region> getRegion();

    Region getRegion(long id);

    void updateRegion(Region nuevaRegion);

    void deleteRegion(long id);
}
