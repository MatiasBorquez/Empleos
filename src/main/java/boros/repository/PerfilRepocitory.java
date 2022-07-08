package boros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import boros.model.Perfil;

public interface PerfilRepocitory  extends JpaRepository<Perfil, Integer>{
    
}
