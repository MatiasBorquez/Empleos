package boros.service;

import java.util.List;

import org.springframework.data.domain.*;

import boros.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	
	void eliminar(Integer idCategoria);
	Page<Categoria> buscarTodas(Pageable page);
}

