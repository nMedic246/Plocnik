package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.VrstaPloce;
import hr.fer.zavrsniRad.plocnik.service.VrstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vrsta")
public class VrstaController {

    @Autowired
    private VrstaService vrstaService;

    @GetMapping("/list")
    public List<VrstaPloce> listVrsta(){
        return vrstaService.listAll();
    }
}
