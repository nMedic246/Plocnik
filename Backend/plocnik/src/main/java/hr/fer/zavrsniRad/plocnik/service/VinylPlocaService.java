package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;

import java.util.List;
import java.util.Optional;

public interface VinylPlocaService {
    List<VinylPloca> listAll();

    Optional<VinylPloca> findById(long id);

    List<VinylPloca> getAllByIdIzvodac(Izvodac izvodac);

    VinylPloca createVinylPloca(VinylPloca vinylPloca);

    VinylPloca fetch(long idVinylPloca);

    List<VinylPloca> getAllByIdIzdavackaKuca(IzdavackaKuca izdavackaKuca);

    VinylPloca deleteVinylPloca(Long idVinylPloca);

    List<VinylPloca> filterVinylPloca(String filter);

    List<VinylPloca> getBestRecords();
}

