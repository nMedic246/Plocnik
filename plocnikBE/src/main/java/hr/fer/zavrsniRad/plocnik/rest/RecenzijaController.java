package hr.fer.zavrsniRad.plocnik.rest;

import hr.fer.zavrsniRad.plocnik.domain.Izvodac;
import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import hr.fer.zavrsniRad.plocnik.domain.Recenzija;
import hr.fer.zavrsniRad.plocnik.domain.VinylPloca;
import hr.fer.zavrsniRad.plocnik.helpers.KorisnikDTO;
import hr.fer.zavrsniRad.plocnik.helpers.RecenzijaDTO;
import hr.fer.zavrsniRad.plocnik.helpers.ReplyDTO;
import hr.fer.zavrsniRad.plocnik.service.KorisnikService;
import hr.fer.zavrsniRad.plocnik.service.RecenzijaService;
import hr.fer.zavrsniRad.plocnik.service.RequestDeniedException;
import hr.fer.zavrsniRad.plocnik.service.VinylPlocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recenzija")
public class RecenzijaController {

    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private VinylPlocaService vinylPlocaService;
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/{id}/novaRecenzija")
    @Secured("ROLE_KORISNIK")
    @Transactional
    public ResponseEntity<Object> createRecenzija(@PathVariable("id") String plocaId, @RequestBody RecenzijaDTO recenzijaDTO, @AuthenticationPrincipal User user){
        try{
            Recenzija recenzija = new Recenzija(recenzijaDTO.getTekst(),recenzijaDTO.getOcjena(),korisnikService.findByKorisnickoIme(user.getUsername()),vinylPlocaService.fetch(Long.valueOf(plocaId)));
            recenzija=recenzijaService.createRecenzija(recenzija);

            if(recenzija != null){
                return ResponseEntity.status(HttpStatus.OK).body(new ReplyDTO("Recenzija uspješno dodana!"));
            }
        } catch (RequestDeniedException ex){
            return ResponseEntity.badRequest().body(ex);
        }
        return ResponseEntity.badRequest().body(new RequestDeniedException("Neispravan zahtjev"));
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Object> getRecenzijePloce(@PathVariable("id") String id){
        try {
            List<Recenzija> recenzije = recenzijaService.findAllByIdVinylPloca(vinylPlocaService.fetch(Long.valueOf(id)));
            return ResponseEntity.status(HttpStatus.OK).body(RecenzijaDTO.fromRecenzijaListToRecenzijaDTOList(recenzije));
        } catch (RequestDeniedException ex) {
            return ResponseEntity.badRequest().body(ex);
        }
    }
}
