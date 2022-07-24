package boros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import boros.model.Usuario;

public interface UsuariosRepository  extends JpaRepository<Usuario, Integer>{

    Usuario findById(int idUsuario);
    Usuario findByUsername(String username);
	List<Usuario> findByFechaRegistroNotNull();
	
	@Modifying
    @Query("UPDATE Usuarios u SET u.estatus=0 WHERE u.id = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);
	
	@Modifying
    @Query("UPDATE Usuarios u SET u.estatus=1 WHERE u.id = :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);
}
