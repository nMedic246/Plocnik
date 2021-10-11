package hr.fer.zavrsniRad.plocnik.service.impl;

import hr.fer.zavrsniRad.plocnik.domain.Pjesma;
import hr.fer.zavrsniRad.plocnik.domain.Sadrzi;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.repo.SadrziRepo;
import hr.fer.zavrsniRad.plocnik.service.SadrziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SadrziServiceJpa implements SadrziService {
    @Autowired
    private SadrziRepo sadrziRepo;

    @Override
    public List<Sadrzi> getAllByIdVinylPloca(VinylPloca vinylPloca) {
        List<Sadrzi> sadrzi=sadrziRepo.getAllByIdVinylPloca(vinylPloca);
        return sadrzi;
    }
}
