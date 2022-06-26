package boros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boros.model.Vacante;
import boros.service.IVacanteService;

@Controller
@RequestMapping(value = "/vacantes")
public class VacantesController {

    @Autowired
	private IVacanteService serviceVacante;
    
    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("id", idVacante);
        return "mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacante.buscarVacanteId(idVacante);
        System.out.println("Vacante: " + vacante);
        model.addAttribute("vacante", vacante);

        //Buscamos los detalles de la vacante en id BC...

        return "detalle";
    }
}
