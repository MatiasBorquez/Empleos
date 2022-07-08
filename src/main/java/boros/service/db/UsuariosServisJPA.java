package boros.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boros.model.Usuario;
import boros.repository.UsuariosRepository;
import boros.service.IUsuarioService;

@Service
public class UsuariosServisJPA implements IUsuarioService{

    @Autowired
    private UsuariosRepository usuariosRepo;

    @Override
    public void guardar(Usuario usuario) {
        usuariosRepo.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepo.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepo.findAll();
    }
    
}
