package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.controllers;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.BD;
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

import java.math.BigDecimal;

@Controller
public class SedeController {
    // Evitar ejecutar init() si ya se ejecuto
    private boolean initComplete = false;

    @Autowired
    private ISedeService sedeService;

    @Autowired
    private IRegionService regionService;

    Logger logger = LoggerFactory.getLogger(SedeController.class);

    @RequestMapping("/sedes")
    public String home(Model model) {
        return "/sedes/index";
    }

    @RequestMapping(value = "/sedes/init", method = RequestMethod.GET)
    public String init() {

        if (!initComplete) {
           for (int i = 0; i < 2; i++) {

               sedeService.saveSede(
                      new Sede(0, BD.nombresSedes[i],BD.provincias[i], BD.cantones[i], BD.distritos[i],BD.apertura[i], (int)(BD.regiones.length *Math.random())));
           }
        }

        initComplete = true;

        return "/sedes/index";
    }

    @RequestMapping(value = "/sedes/insertar", method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute("sede", new Sede());
        model.addAttribute("regiones", regionService.getRegion());
        return "/sedes/insertar";
    }

    @RequestMapping(value = "/sedes/insertar", method = RequestMethod.POST)
    public String insertarAction(Sede sede, BindingResult result, Model model) {
        sedeService.saveSede(sede);
        return "/sedes/index";
    }

    @RequestMapping(value = "/sedes/editar/{id}", method = RequestMethod.GET)
    public String editar(Model model, @PathVariable long id) {
        try {
            Sede sede = sedeService.getSedes(id);
            model.addAttribute("sede", sede);
            model.addAttribute("nombre", sede.getNombre());
            model.addAttribute("provincia", sede.getProvincia());
            model.addAttribute("canton", sede.getCanton());
            model.addAttribute("distrito", sede.getDistrito());
            model.addAttribute("apertura", sede.getApertura());
            model.addAttribute("id_region", sede.getId_region());
            model.addAttribute("region", regionService.getRegion());
            return "/sedes/editar";
        } catch (Exception e) {
            logger.error("Can't recover product from database", e);
            return "notFound";
        }
    }

    @RequestMapping(value = "/sedes/editar/{id}", method = RequestMethod.POST)
    public String guardarEdicion(Model model, @PathVariable long id,
                                 Sede editado, BindingResult result) {
        try {
            Sede sede = sedeService.getSedes(id);
            sede.setNombre(editado.getNombre());
            sede.setProvincia(editado.getProvincia());
            sede.setCanton(editado.getCanton());
            sede.setDistrito(editado.getDistrito());
            sede.setApertura(editado.getApertura());
            sede.setId_region(editado.getId_region());
            sedeService.saveSede(sede);
            return "/sedes/index";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping(value = "/sedes/borrar/{id}", method = RequestMethod.GET)
    public String prepararBorrado(Model model, @PathVariable long id) {
        try {
            Sede sede = sedeService.getSedes(id);
            model.addAttribute("sede", sede);
            return "/sedes/borrar";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping(value = "/sedes/borrar/{id}", method = RequestMethod.POST)
    public String ejecutarBorrado(Model model, @PathVariable long id) {
        try {
            sedeService.deleteSede(id);
            return "/sedes/index";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping("/sedes/listar")
    public String listar(Model model) {
        init();
        model.addAttribute("sedes", sedeService.getSedes());
        model.addAttribute("regiones", regionService.getRegion());
        return "/sedes/listar";
    }
}
