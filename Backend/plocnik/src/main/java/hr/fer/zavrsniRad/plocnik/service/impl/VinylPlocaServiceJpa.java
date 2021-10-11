package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.repo.VinylPlocaRepo;
import hr.fer.zavrsniRad.plocnik.service.EntityMissingException;
import hr.fer.zavrsniRad.plocnik.service.VinylPlocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VinylPlocaServiceJpa implements VinylPlocaService {

    @Autowired
    VinylPlocaRepo vinylPlocaRepo;

    @Override
    public List<VinylPloca> listAll() {
        return vinylPlocaRepo.findAllByOrderByNazivVinylPlocaAsc();
    }

    @Override
    public Optional<VinylPloca> findById(long idVinylPloca) {
        return vinylPlocaRepo.findById(idVinylPloca);
    }

    @Override
    public List<VinylPloca> getAllByIdIzvodac(Izvodac izvodac) {
        return vinylPlocaRepo.getAllByIdIzvodac(izvodac);
    }

    @Override
    public VinylPloca createVinylPloca(VinylPloca vinylPloca) {
        return vinylPlocaRepo.save(vinylPloca);
    }

    @Override
    public VinylPloca fetch(long idVinylPloca) {
        return findById(idVinylPloca).orElseThrow(
                () -> new EntityMissingException(VinylPloca.class)
        );
    }

    @Override
    public List<VinylPloca> getAllByIdIzdavackaKuca(IzdavackaKuca izdavackaKuca) {
        return vinylPlocaRepo.getAllByIdIzdavackaKuca(izdavackaKuca);
    }

    @Override
    public VinylPloca deleteVinylPloca(Long idVinylPloca) {
        VinylPloca vinylPloca = fetch(idVinylPloca);
        vinylPlocaRepo.delete(vinylPloca);
        return vinylPloca;
    }

    @Override
    public List<VinylPloca> filterVinylPloca(String filter) {
        return vinylPlocaRepo.filterVinylPlocaByNazivVinylPloca(filter);
    }

    @Override
    public List<VinylPloca> getBestRecords() {
        List<VinylPloca> ploce = vinylPlocaRepo.findAllByOrderByUkOcjenaDesc();
        List<VinylPloca> remove = new ArrayList<>();
        for(VinylPloca ploca : ploce){
            if(ploca.getUkOcjena()==null){
                remove.add(ploca);
            }
        }
        ploce.removeAll(remove);
        List<VinylPloca> firstNElementsList = ploce.stream().limit(5).collect(Collectors.toList());
        return firstNElementsList;
    }
}
