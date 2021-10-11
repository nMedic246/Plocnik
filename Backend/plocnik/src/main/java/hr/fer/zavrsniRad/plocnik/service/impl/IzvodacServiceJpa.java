package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.repo.IzvodacRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.IzvodacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IzvodacServiceJpa implements IzvodacService {

    @Autowired
    private IzvodacRepo izvodacRepo;

    @Override
    public List<Izvodac> listAll() {
        return izvodacRepo.findAllByOrderByNazivIzvodac();
    }

    @Override
    public Optional<Izvodac> findById(long idIzvodac) {
        return izvodacRepo.findById(idIzvodac);
    }

    @Override
    public Izvodac fetch(long idIzvodac) {
        return findById(idIzvodac).orElseThrow(
                () -> new EntityMissingException(Izvodac.class)
        );
    }

    @Override
    public Izvodac createIzvodac(Izvodac izvodac) {
        return izvodacRepo.save(izvodac);
    }

    @Override
    public Izvodac deleteIzvodac(Long idIzvodac) {
        Izvodac izvodac = fetch(idIzvodac);
        izvodacRepo.delete(izvodac);
        return izvodac;
    }

    @Override
    public List<Izvodac> filterIzvodac(String filter) {
        return izvodacRepo.filterIzvodacByNazivIzvodac(filter);
    }
}
