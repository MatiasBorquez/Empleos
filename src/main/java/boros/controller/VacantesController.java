package boros.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    private ICategoriasService serviceCategoria;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> vacantes = serviceVacante.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "vacantes/listVacantes";
        
    }

    @GetMapping("/create")
    public String create(Vacante vacante, Model model) {
        model.addAttribute("categorias", serviceCategoria.buscarTodas());
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un Error: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            //String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
            // Procesamos la variable nombreImagen
            vacante.setImagen(nombreImagen); 
            }
        }
        serviceVacante.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro Guardado.");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        model.addAttribute("id", idVacante);
        return "mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacante.buscarVacanteId(idVacante);
        model.addAttribute("vacante", vacante);

        //Buscamos los detalles de la vacante en id BC...

        return "detalle";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
