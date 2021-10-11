package hr.fer.zavrsniRad.plocnik.service;

import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;

import java.util.List;

public interface RecenzijaService {
    List<Recenzija> findAllByIdVinylPloca(VinylPloca vinylPloca);

    Recenzija createRecenzija(Recenzija recenzijaReq);
}
