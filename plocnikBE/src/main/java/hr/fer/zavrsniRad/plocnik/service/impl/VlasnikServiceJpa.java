package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.Vlasnik;
import hr.fer.zavrsniRad.plocnik.repo.VlasnikRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.VlasnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VlasnikServiceJpa implements VlasnikService {
    @Autowired
    private VlasnikRepo vlasnikRepo;
    @Override
    public Optional<Vlasnik> findById(long id) {
        return vlasnikRepo.findById(id);
    }

    @Override
    public Vlasnik fetch(long idVlasnik) {
        return findById(idVlasnik).orElseThrow(
                () -> new EntityMissingException(Vlasnik.class)
        );
    }

    @Override
    public List<Vlasnik> listAll() {
        return vlasnikRepo.findAll();
    }
}
