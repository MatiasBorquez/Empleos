package boros.service.db;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import boros.model.Vacante;
import boros.repository.VacantesRepository;
import boros.service.IVacanteService;

@Service
@Primary
public class VacantesServisJPA implements IVacanteService{

    @Autowired
    private VacantesRepository vacantesRepo;

    @Override
    public List<Vacante> buscarTodas() {
        return vacantesRepo.findAll();
    }

    @Override
    public Vacante buscarVacanteId(Integer idVacante) {
        Optional<Vacante> optional = vacantesRepo.findById(idVacante);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepo.save(vacante);
        
    }

    @Override
    public List<Vacante> buscarDesacadas() {
        return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {
        vacantesRepo.deleteById(idVacante);
        
    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepo.findAll(example);
    }

    @Override
    public Page<Vacante> buscarTodas(Pageable page) {
        return vacantesRepo.findAll(page);

    }

}
