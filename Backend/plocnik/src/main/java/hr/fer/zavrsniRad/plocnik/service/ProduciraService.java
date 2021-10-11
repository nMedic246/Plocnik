package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.domain.Producira;

import java.util.List;

public interface ProduciraService {
    List<Producira> getAllByIdPjesma(Pjesma pjesma);
}
