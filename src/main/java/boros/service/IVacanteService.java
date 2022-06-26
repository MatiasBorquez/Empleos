package boros.service;

import java.util.List;

import boros.model.Vacante;

public interface IVacanteService {
    List<Vacante> buscarTodas();
    Vacante buscarVacanteId(Integer idVacante);
}
