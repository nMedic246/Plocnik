package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.repo.MjestoRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MjestoServiceJpa implements MjestoService {

    @Autowired
    private MjestoRepo mjestoRepo;
    @Override
    public Optional<Mjesto> findById(long id) {
        return mjestoRepo.findById(id);
    }

    @Override
    public Mjesto fetch(long idMjesto) {
        return findById(idMjesto).orElseThrow(
                () -> new EntityMissingException(Mjesto.class)
        );
    }

    @Override
    public List<Mjesto> listAll() {
        return mjestoRepo.findAll();
    }
}
