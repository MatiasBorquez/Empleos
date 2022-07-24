package boros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boros.model.Solicitudes;

public interface SolicitudesRepocitory extends JpaRepository<Solicitudes,Integer>{
    
}
