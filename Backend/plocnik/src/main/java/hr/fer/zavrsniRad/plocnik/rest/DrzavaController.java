package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Drzava;
import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.service.DrzavaService;
import hr.fer.zavrsniRad.plocnik.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drzava")
public class DrzavaController {
    @Autowired
    private DrzavaService drzavaService;

    @GetMapping("/list")
    public List<Drzava> listDrzave() {
        return drzavaService.listAll();
    }
}
