package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.ImaUKolekciji;
import hr.fer.zavrsniRad.plocnik.domain.Producira;
import hr.fer.zavrsniRad.plocnik.helpers.ReplyDTO;
import hr.fer.zavrsniRad.plocnik.service.PjesmaService;
import hr.fer.zavrsniRad.plocnik.service.ProduciraService;
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
@RequestMapping("/pjesma")
public class PjesmaController {

    @Autowired
    private PjesmaService pjesmaService;
    @Autowired
    private ProduciraService produciraService;

    @GetMapping("/{id}/producent")
    public ResponseEntity<Object> getProducenti(@PathVariable("id") String idPjesma){
        try {
            List<Producira> producira = produciraService.getAllByIdPjesma(pjesmaService.fetch(Long.valueOf(idPjesma)));
            return ResponseEntity.status(HttpStatus.OK).body(producira);
        }catch (RequestDeniedException ex) {
            ReplyDTO reply = new ReplyDTO("Nema podataka o producentu!");
            return ResponseEntity.badRequest().body(reply);
        }
    }
}
