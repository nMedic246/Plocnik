package hr.fer.zavrsniRad.plocnik.service.impl;


import hr.fer.zavrsniRad.plocnik.domain.Glazbenik;
import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.repo.GlazbenikRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.GlazbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlazbenikServiceJpa implements GlazbenikService {
    @Autowired
    private GlazbenikRepo glazbenikRepo;

    @Override
    public List<Glazbenik> listAll() {
        return glazbenikRepo.findAll();
    }

    @Override
    public Optional<Glazbenik> findByIzvodacId(long idIzvodac) {
        return glazbenikRepo.findByIzvodacId(idIzvodac);
    }

    @Override
    public Glazbenik fetch(long idIzvodac) {
        return findByIzvodacId(idIzvodac).orElseThrow(
                () -> new EntityMissingException(Glazbenik.class)
        );
    }

    @Override
    public Glazbenik createGlazbenik(Glazbenik glazbenik) {
        return glazbenikRepo.save(glazbenik);
    }
}
