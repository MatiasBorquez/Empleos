package boros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import boros.model.Usuario;

public interface UsuariosRepository  extends JpaRepository<Usuario, Integer>{

}
