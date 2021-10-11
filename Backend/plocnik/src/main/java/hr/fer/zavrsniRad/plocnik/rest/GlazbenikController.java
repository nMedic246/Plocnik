package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Glazbenik;
import hr.fer.zavrsniRad.plocnik.domain.Grupa;
import hr.fer.zavrsniRad.plocnik.service.GlazbenikService;
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
@RequestMapping("/glazbenik")
public class GlazbenikController {
    @Autowired
    private GlazbenikService glazbenikService;

    @GetMapping("/list")
    public List<Glazbenik> listIzvodac (){
        return glazbenikService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGrupaByIdIzvodac(@PathVariable("id") String id) {
        try {
           Glazbenik glazbenik=glazbenikService.fetch(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(glazbenik);
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
}
