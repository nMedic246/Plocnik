package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.JeZanra;
import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.domain.Zanr;
import hr.fer.zavrsniRad.plocnik.repo.JeZanraRepo;
import hr.fer.zavrsniRad.plocnik.service.JeZanraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeZanraServiceJpa implements JeZanraService {
    @Autowired
    JeZanraRepo jeZanraRepo;

    @Override
    public List<JeZanra> getAllByIdVinylPloca(VinylPloca vinylPloca) {
        return jeZanraRepo.getAllByIdVinylPloca(vinylPloca);
    }

    @Override
    public List<JeZanra> getAllByIdZanr(Zanr zanr) {
        return  jeZanraRepo.getAllByIdZanr(zanr);
    }

    @Override
    public JeZanra createJZ(JeZanra jeZanra) {
        return jeZanraRepo.save(jeZanra);
    }
}
