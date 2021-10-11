package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlikaRepo extends JpaRepository<Slika,Long> {
    Slika findByKorisnik(Korisnik korisnik);
    Slika findByVinylPloca(VinylPloca vinylPloca);
    Slika findByIzvodac(Izvodac izvodac);
    Slika findByIzdavackaKuca(IzdavackaKuca izdavackaKuca);
}
