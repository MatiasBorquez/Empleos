package boros.service;

import java.util.List;

import org.springframework.data.domain.*;

import boros.model.Solicitudes;

public interface ISolicitudesService {
    void guardar(Solicitudes solicitudes);
    List<Solicitudes> buscarTodas();
    Solicitudes buscarPorId(Integer idSolicitudes);
    void eliminar(Integer idSolicitudes);
    Page<Solicitudes> buscarTodas(Pageable page);
}
