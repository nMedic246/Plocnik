package hr.fer.zavrsniRad.plocnik.rest;


import hr.fer.zavrsniRad.plocnik.domain.Mjesto;
import hr.fer.zavrsniRad.plocnik.domain.Vlasnik;
import hr.fer.zavrsniRad.plocnik.service.VlasnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vlasnik")
public class VlasnikController {
    @Autowired
    private VlasnikService vlasnikService;

    @GetMapping("/list")
    public List<Vlasnik> listMjesta() {
        return vlasnikService.listAll();
    }
}
