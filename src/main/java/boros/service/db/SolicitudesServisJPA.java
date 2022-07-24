package boros.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import boros.model.Solicitudes;
import boros.repository.SolicitudesRepocitory;
import boros.service.ISolicitudesService;

@Service
public class SolicitudesServisJPA implements ISolicitudesService{

    @Autowired
    private SolicitudesRepocitory solicitudesRepo;

    @Override
    public void guardar(Solicitudes solicitudes) {
        solicitudesRepo.save(solicitudes);
    }

    @Override
    public List<Solicitudes> buscarTodas() {
        return solicitudesRepo.findAll();
    }

    @Override
    public Solicitudes buscarPorId(Integer idSolicitudes) {
        Optional<Solicitudes> opcional = solicitudesRepo.findById(idSolicitudes);
        if(opcional.isPresent()){
            return opcional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idSolicitudes) {
        solicitudesRepo.deleteById(idSolicitudes);
        
    }

    @Override
    public Page<Solicitudes> buscarTodas(Pageable page) {
        return solicitudesRepo.findAll(page);
    }
    
}
