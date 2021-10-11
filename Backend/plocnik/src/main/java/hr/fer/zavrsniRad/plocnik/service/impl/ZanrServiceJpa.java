package hr.fer.zavrsniRad.plocnik.service.impl;


import hr.fer.zavrsniRad.plocnik.domain.Zanr;
import hr.fer.zavrsniRad.plocnik.repo.ZanrRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZanrServiceJpa  implements ZanrService {

    @Autowired
    private ZanrRepo zanrRepo;


    @Override
    public List<Zanr> findAll() {
        return zanrRepo.findAll();
    }

    @Override
    public Zanr findByNazivZanr(String nazivZanr) {
        return zanrRepo.findByNazivZanr(nazivZanr);
    }

    @Override
    public Optional<Zanr> findById(long id) {
        return zanrRepo.findById(id);
    }

    @Override
    public Zanr fetch(Long idZanr) {
        return findById(idZanr).orElseThrow(
                () -> new EntityMissingException(Zanr.class)
        );
    }
}
