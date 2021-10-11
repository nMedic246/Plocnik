package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.repo.IzdavackaKucaRepo;
import hr.fer.zavrsniRad.plocnik.repo.RecenzijaRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.IzdavackaKucaService;
import hr.fer.zavrsniRad.plocnik.service.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IzdavackaKucaServiceJpa implements IzdavackaKucaService {

    @Autowired
    IzdavackaKucaRepo izdavackaKucaRepo;


    @Override
    public List<IzdavackaKuca> listAll() {
        return izdavackaKucaRepo.findAllByOrderByNazivIzdavackaKucaAsc();
    }

    @Override
    public Optional<IzdavackaKuca> findById(long id) {
        return izdavackaKucaRepo.findById(id);
    }

    @Override
    public IzdavackaKuca createVinylPloca(IzdavackaKuca izdavackaKuca) {
        return izdavackaKucaRepo.save(izdavackaKuca);
    }

    @Override
    public IzdavackaKuca fetch(long idIzdavackaKuca) {
        return findById(idIzdavackaKuca).orElseThrow(
                () -> new EntityMissingException(IzdavackaKuca.class)
        );
    }

    @Override
    public IzdavackaKuca deleteIzdavackaKuca(Long idIzdavackaKuca) {
        IzdavackaKuca izdavackaKuca = fetch(idIzdavackaKuca);
        izdavackaKucaRepo.delete(izdavackaKuca);
        return izdavackaKuca;
    }

    @Override
    public List<IzdavackaKuca> filterIzdavackaKuca(String filter) {
        return izdavackaKucaRepo.filterIzdavackaKucaByNazivIzdavackaKuca(filter);
    }
}
