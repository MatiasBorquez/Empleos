package boros.service.db;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import boros.model.Categoria;
import boros.repository.CategoriasRepository;
import boros.service.ICategoriasService;

@Service
@Primary
public class CategoriasServisJPA implements ICategoriasService{

    @Autowired
    private CategoriasRepository categoriasRepo;

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepo.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriasRepo.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        categoriasRepo.deleteById(idCategoria);
        
    }

    @Override
    public Page<Categoria> buscarTodas(Pageable page) {
        return categoriasRepo.findAll(page);

    }
    
}