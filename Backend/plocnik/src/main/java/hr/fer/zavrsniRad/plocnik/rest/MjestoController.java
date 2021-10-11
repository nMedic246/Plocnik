package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.IzdavackaKuca;
import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.service.MjestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mjesto")
public class MjestoController {
    @Autowired
    private MjestoService mjestoService;

    @GetMapping("/list")
    public List<Mjesto> listMjesta() {
        return mjestoService.listAll();
    }
}
