package boros.service;

import java.util.List;

import boros.model.Usuario;

public interface IUsuarioService {
    /*Usar Pantalla del archivo formRegistro.html
     * El metodo para mostrar el formulario para registrar y el metodo para guardar el usuario
     *  debera estar en el Controlador HomeController
     * Al guardar el usuario se le debe asignar el perfil del usuario y la fecha del registro
     * sera la fecha actual del registro
     */

     void guardar(Usuario usuario);
     void eliminar(Integer idUsuario);
     List<Usuario> buscarTodos();
     Usuario buscarPorId(Integer idUsuario);
     Usuario buscarPorUsuario(String username);
     List <Usuario> buscarRegistrados();
     int bloquear(int idUsuario);
     int activar(int idUsuario);
}
