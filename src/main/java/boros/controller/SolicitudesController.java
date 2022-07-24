package boros.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boros.model.Solicitudes;
import boros.model.Usuario;
import boros.model.Vacante;
import boros.service.*;
import boros.util.Utileria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/solicitudes")
public class SolicitudesController {
    
    @Value("${empleosapp.ruta.cv}")
    private String ruta;

    // Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private ISolicitudesService serviceSolicitudes;

    @Autowired
    private IVacanteService serviceVacantes;

    // Inyectamos una instancia desde nuestro ApplicationContext
    
    
    @Autowired
   	private IUsuarioService serviceUsuarios;
       
    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Solicitudes> lista = serviceSolicitudes.buscarTodas();
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }
    
    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<Solicitudes> lista = serviceSolicitudes.buscarTodas(page);
        model.addAttribute("solcitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/create/{idVacante}")
    public String crear(Solicitudes solicitud, @PathVariable Integer idVacante,Model model){
        Vacante vacante = serviceVacantes.buscarVacanteId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String guardar(Solicitudes solicitud, BindingResult result, Model model, HttpSession session,
        @RequestParam("archivoCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication authentication){
            //Recuperamos el username que inicia la sesión
            String username = authentication.getName();
            if(result.hasErrors()){
                System.out.println("Existieron errores");
                return "solicitudes/formSolicitud";
            }

            if(!multiPart.isEmpty()){
                String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
                if (nombreArchivo!= null){
                    solicitud.setFile(nombreArchivo);
                }
            }
            //Busca el objeto usuario en BD
            Usuario usuario = serviceUsuarios.buscarPorUsuario(username);

            solicitud.setUsuario(usuario);
            solicitud.setFecha(new Date());
            // Guardar el objeto solicitud en la db
            serviceSolicitudes.guardar(solicitud);
            attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
            return "redirect:/";
        }

    @GetMapping(value="/delete/{id}")
    public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
        //elinimar la solicitud
        serviceSolicitudes.eliminar(idSolicitud);
        attributes.addFlashAttribute("msg", "La solicitud fue eliminada");

        return "redirect:/solicitudes/indexPaginate";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
