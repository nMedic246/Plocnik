package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.Vlasnik;

import java.util.List;
import java.util.Optional;

public interface VlasnikService {
    Optional<Vlasnik> findById(long id);
    Vlasnik fetch(long idVlasnik);
    List<Vlasnik> listAll();
}
