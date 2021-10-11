package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;

import java.util.List;
import java.util.Optional;

public interface IzdavackaKucaService {
    List<IzdavackaKuca> listAll();

    Optional<IzdavackaKuca> findById(long id);

    IzdavackaKuca createVinylPloca(IzdavackaKuca izdavackaKuca);
    IzdavackaKuca fetch(long idIzdavackaKuca);

    IzdavackaKuca deleteIzdavackaKuca(Long idIzdavackaKuca);

    List<IzdavackaKuca> filterIzdavackaKuca(String filter);
}
