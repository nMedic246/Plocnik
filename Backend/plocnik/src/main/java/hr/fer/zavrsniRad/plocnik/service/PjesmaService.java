package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.Pjesma;

import java.util.Optional;

public interface PjesmaService {
    Pjesma fetch(Long idPjesma);
    Optional<Pjesma> findById(long id);
}
