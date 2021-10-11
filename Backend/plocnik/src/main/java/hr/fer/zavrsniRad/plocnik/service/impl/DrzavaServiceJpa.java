package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Drzava;
import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.repo.DrzavaRepo;
import hr.fer.zavrsniRad.plocnik.service.DrzavaService;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrzavaServiceJpa implements DrzavaService {
    @Autowired
    private DrzavaRepo drzavaRepo;
    @Override
    public List<Drzava> listAll() {
        return drzavaRepo.findAll();
    }

    @Override
    public Drzava fetch(long idDrzava) {
        return findById(idDrzava).orElseThrow(
                () -> new EntityMissingException(Drzava.class)
        );
    }

    @Override
    public Optional<Drzava> findById(long id) {
        return drzavaRepo.findById(id);
    }


}
