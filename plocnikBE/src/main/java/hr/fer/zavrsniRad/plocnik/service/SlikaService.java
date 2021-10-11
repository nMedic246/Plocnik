package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.*;

public interface SlikaService {
    Slika createSlika(Slika slika);
    Slika findByKorisnik(Korisnik korisnik);
    Slika findByVinylPloca(VinylPloca vinylPloca);
    Slika findByIzvodac(Izvodac izvodac);
    Slika findByIzdavackaKuca(IzdavackaKuca izdavackaKuca);

}
