package boros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boros.model.Usuario;
import boros.service.IUsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios); 
        return "usuarios/listUsuarios";
        
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes){
        usuarioService.eliminar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario a sido eliminado");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/unlock/{id}")
    public String activar(@PathVariable("id") int idUsuario, RedirectAttributes attributes){
        usuarioService.activar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue activado");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/lock/{id}")
    public String bloquear(@PathVariable("id") int idUsuario, RedirectAttributes attributes){
        usuarioService.bloquear(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y tendra acceso al sistema.");
        return "redirect:/usuarios/index";
    }
}
