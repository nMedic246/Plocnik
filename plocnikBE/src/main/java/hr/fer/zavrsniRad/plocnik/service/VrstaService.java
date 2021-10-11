package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.VrstaPloce;

import java.util.List;
import java.util.Optional;

public interface VrstaService {
    List<VrstaPloce> listAll();

    Optional<VrstaPloce> findById(long id);

    VrstaPloce fetch(long idVrsta);
}
