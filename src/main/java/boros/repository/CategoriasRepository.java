package boros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import boros.model.Categoria;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer>{
public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{
    
}
