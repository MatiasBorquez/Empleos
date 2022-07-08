package boros.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import boros.model.Vacante;

public interface IVacanteService {
    List<Vacante> buscarTodas();
    Vacante buscarVacanteId(Integer idVacante);
    void guardar(Vacante vacante);
    List<Vacante> buscarDesacadas();
    void eliminar(Integer idVacante);
    List<Vacante> buscarByExample(Example<Vacante> example);
    Page<Vacante> buscarTodas(Pageable page);
}
