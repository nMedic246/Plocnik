package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.JeZanra;
import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zanr;

import java.util.List;

public interface JeZanraService {
    List<JeZanra> getAllByIdVinylPloca(VinylPloca vinylPloca);
    List<JeZanra> getAllByIdZanr(Zanr zanr);

    JeZanra createJZ(JeZanra jeZanra);
}
