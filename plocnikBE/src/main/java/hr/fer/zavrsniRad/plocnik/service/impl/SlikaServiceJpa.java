package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.*;
import hr.fer.zavrsniRad.plocnik.repo.SlikaRepo;
import hr.fer.zavrsniRad.plocnik.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlikaServiceJpa implements SlikaService {
    @Autowired
    private SlikaRepo slikaRepo;

    @Override
    public Slika createSlika(Slika slika) {
        return slikaRepo.save(slika);
    }

    @Override
    public Slika findByKorisnik(Korisnik korisnik) {
        return slikaRepo.findByKorisnik(korisnik);
    }

    @Override
    public Slika findByVinylPloca(VinylPloca vinylPloca) {
        return slikaRepo.findByVinylPloca(vinylPloca);
    }

    @Override
    public Slika findByIzvodac(Izvodac izvodac) {
        return slikaRepo.findByIzvodac(izvodac);
    }

   @Override
    public Slika findByIzdavackaKuca(IzdavackaKuca izdavackaKuca) {
        return slikaRepo.findByIzdavackaKuca(izdavackaKuca);
    }
}
