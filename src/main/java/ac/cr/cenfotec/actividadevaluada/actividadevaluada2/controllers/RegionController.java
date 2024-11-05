package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.controllers;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.BD;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Region;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Sede;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.IRegionService;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.ISedeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegionController {

    // Evitar ejecutar init() si ya se ejecuto
    private boolean initComplete = false;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private ISedeService sedeService;

    Logger logger = LoggerFactory.getLogger(RegionController.class);


    @RequestMapping("/region")
    public String home(Model model) {
        return "/region/index";
    }

    @RequestMapping(value="/region/init", method = RequestMethod.GET)
    public String init() {

        if (!initComplete) {
            for (int i = 0; i < BD.regiones.length ; i++) {

                regionService.saveRegion(new Region(0, BD.regiones[i]));

            }
        }

        initComplete = true;

        return "/region/index";
    }

    @RequestMapping(value="/region/insertar", method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute("region",new Region());
        return "/region/insertar";
    }

    @RequestMapping(value="/region/insertar", method = RequestMethod.POST)
    public String insertarAction(Region region, BindingResult result, Model model) {
        regionService.saveRegion(region);
        return "/region/index";
    }


    @RequestMapping("/region/listar")
    public String listar(Model model) {
        init();
        model.addAttribute("regiones", regionService.getRegion());
        return "/region/listar";
    }

    @RequestMapping(value="/region/listar_sedes/{id}", method = RequestMethod.GET)
    public String listar_sedes(Model model, @PathVariable long id) {
        List<Sede> sedes_region = new ArrayList<Sede>();

        for (Sede sede : sedeService.getSedes()) {
            if (sede.getId_region() == id) {

                sedes_region.add(sede);
            }
        }

        model.addAttribute("region", regionService.getRegion(id));
        model.addAttribute("sede", sedes_region);
        return "/region/listar_sedes";
    }
}
