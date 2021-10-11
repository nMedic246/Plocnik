package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.JeZanra;
import hr.fer.zavrsniRad.plocnik.domain.Zanr;
import hr.fer.zavrsniRad.plocnik.service.JeZanraService;
import hr.fer.zavrsniRad.plocnik.service.RequestDeniedException;
import hr.fer.zavrsniRad.plocnik.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zanr")
public class ZanrController {

    @Autowired
    private JeZanraService jeZanraService;
    @Autowired
    private ZanrService zanrService;

    @GetMapping("/list")
    public List<Zanr> getZanrovi(){return zanrService.findAll();}
    @GetMapping("/{nazivZanr}")
    public ResponseEntity<Object> getZanr(@PathVariable("nazivZanr") String nazivZanr){
        try {
            List<JeZanra> zanr=jeZanraService.getAllByIdZanr(zanrService.findByNazivZanr(nazivZanr));
            return ResponseEntity.status(HttpStatus.OK).body(zanr);
        }catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
}
