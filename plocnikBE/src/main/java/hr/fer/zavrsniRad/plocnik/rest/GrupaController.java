package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.service.GrupaService;
import hr.fer.zavrsniRad.plocnik.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupa")
public class GrupaController {
    @Autowired
    private GrupaService grupaService;


    @GetMapping("/list")
    public List<Grupa> listIzvodac (){
        return grupaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGrupaByIdIzvodac(@PathVariable("id") String id) {
        try {
            Grupa grupa=grupaService.fetch(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(grupa);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
}
