package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.repo.GrupaRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.GrupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupaServiceJpa implements GrupaService {

    @Autowired
    private GrupaRepo grupaRepo;

    @Override
    public List<Grupa> listAll() {
        return grupaRepo.findAll();
    }

    @Override
    public Optional<Grupa> findByIzvodacId(long id) {
        return grupaRepo.findByIzvodacId(id);
    }

    @Override
    public Grupa fetch(long idIzvodac) {
        return findByIzvodacId(idIzvodac).orElseThrow(
                () -> new EntityMissingException(Grupa.class)
        );
    }

    @Override
    public Grupa createGrupa(Grupa grupa) {
        return grupaRepo.save(grupa);
    }
}
