package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.repo.RecenzijaRepo;
import hr.fer.zavrsniRad.plocnik.service.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecenzijaServiceJpa implements RecenzijaService {

    @Autowired
    private RecenzijaRepo recenzijaRepo;
    @Override
    public List<Recenzija> findAllByIdVinylPloca(VinylPloca vinylPloca) {
        return recenzijaRepo.findAllByIdVinylPlocaOrderByDatumPostavljanjaDesc(vinylPloca);
    }

    @Override
    public Recenzija createRecenzija(Recenzija recenzijaReq) {
        return recenzijaRepo.save(recenzijaReq);
    }
}
