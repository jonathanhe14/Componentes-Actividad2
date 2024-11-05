package ac.cr.cenfotec.actividadevaluada.actividadevaluada2.controllers;

import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.BD;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.entity.Libro;
import ac.cr.cenfotec.actividadevaluada.actividadevaluada2.service.LibroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class LibroController {
    // Evitar ejecutar init() si ya se ejecuto
    private boolean initComplete = false;

    @Autowired
    private LibroService libroService;

    Logger logger = LoggerFactory.getLogger(LibroController.class);

    @RequestMapping("/libro")
    public String home(Model model) {
        try{
            return "/libro/index";
        }catch (Exception e){
            logger.error("Error ",e);
            return "error";
        }

    }

    @RequestMapping(value = "/libro/init", method = RequestMethod.GET)
    public String init() {
        try {
            if (!initComplete) {
                for (int i = 0; i < 10; i++) {
                    libroService.saveLibro(
                            new Libro(0, BD.libros[i], BD.autores[i], BD.aniosPublicacion[i], BD.editoriales[i], (int) (5 * Math.random())));
                }
                initComplete = true;
            }
        } catch (Exception e) {
            logger.error("Error during init: ", e);
            return "error"; // O una página de error más específica
        }
        return "/libro/index";
    }


    @RequestMapping(value = "/libro/insertar", method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute("libro", new Libro());
        return "libro/insertar";
    }

    @RequestMapping(value = "/libro/insertar", method = RequestMethod.POST)
    public String insertarAction(Libro libro, BindingResult result, Model model) {
        libroService.saveLibro(libro);
        return "/libro/index";
    }

    @RequestMapping(value = "/libro/editar/{id}", method = RequestMethod.GET)
    public String editar(Model model, @PathVariable long id) {
        try {
            Libro libro = libroService.getLibro(id);
            model.addAttribute("libro", libro);
            model.addAttribute("id", libro.getId());
            model.addAttribute("titulo", libro.getTitulo());
            model.addAttribute("autor", libro.getAutor());
            model.addAttribute("publicacion", libro.getPublicacion());
            model.addAttribute("editorial", libro.getEditorial());
            model.addAttribute("edicion", libro.getEdicion());
            return "/libro/editar";
        } catch (Exception e) {
            logger.error("Can't recover person from database", e);
            return "notFound";
        }
    }

    @RequestMapping(value = "/libro/editar/{id}", method = RequestMethod.POST)
    public String guardarEdicion(Model model, @PathVariable long id,
                                 Libro editado, BindingResult result) {
        try {
            Libro libro = libroService.getLibro(id);
            libro.setTitulo(editado.getTitulo());
            libro.setAutor(editado.getAutor());
            libro.setPublicacion(editado.getPublicacion());
            libro.setEditorial(editado.getEditorial());
            libro.setEdicion(editado.getEdicion());
            libroService.saveLibro(libro);
            return "/libro/index";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping(value = "/libro/borrar/{id}", method = RequestMethod.GET)
    public String prepararBorrado(Model model, @PathVariable long id) {
        try {
            Libro libro = libroService.getLibro(id);
            model.addAttribute("libro", libro);
            return "/libro/borrar";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping(value = "/libro/borrar/{id}", method = RequestMethod.POST)
    public String ejecutarBorrado(Model model, @PathVariable long id) {
        try {
            libroService.deleteLibro(id);
            return "/libro/index";
        } catch (Exception e) {
            logger.error("Can't edit", e);
            return "error";
        }
    }

    @RequestMapping(value = "/libro/listar")
    public String listar(Model model) {
        try {
            init();
            model.addAttribute("libros", libroService.getLibros());
            return "/libro/listar";
        } catch (Exception e) {
            logger.error("Error in /libro/listar: ", e);
            return "error";
        }
    }
}
