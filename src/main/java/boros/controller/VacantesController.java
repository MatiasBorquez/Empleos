package boros.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boros.model.Vacante;
import boros.service.ICategoriasService;
import boros.service.IVacanteService;
import boros.util.Utileria;

@Controller
@RequestMapping(value = "/vacantes")
public class VacantesController {

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private IVacanteService serviceVacante;

    @Autowired
    // @Qualifier("categoriasServisJPA")
    private ICategoriasService serviceCategoria;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> vacantes = serviceVacante.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "vacantes/listVacantes";

    }

    @GetMapping("/create")
    public String create(Vacante vacante, Model model) {
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
            @RequestParam("archivoImagen") MultipartFile multiPart) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un Error: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            // String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            // String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }
        serviceVacante.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro Guardado.");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
        serviceVacante.eliminar(idVacante);
        attributes.addFlashAttribute("msg", "La vacante a sido eliminada");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacante.buscarVacanteId(idVacante);
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacante.buscarVacanteId(idVacante);
        model.addAttribute("vacante", vacante);

        // Buscamos los detalles de la vacante en id BC...

        return "detalle";
    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = serviceVacante.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("categorias", serviceCategoria.buscarTodas());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
