package hr.fer.zavrsniRad.plocnik.repo;

import hr.fer.zavrsniRad.plocnik.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface KorisnikRepo extends JpaRepository<Korisnik, Long> {
    int countByKorisnickoIme(String korisnickoIme);

    int countByEmail(String email);
    Korisnik findByKorisnickoIme(String korisnickoIme);

    List<Korisnik> findAllByKorisnickoIme(String korisnickoIme);

    boolean existsByKorisnickoImeAndIdKorisnikNot(String korisnickoIme,Long idKorisnik);

    @Modifying
    @Query("update Korisnik k set k.korisnickoIme=:korisnickoIme where k.idKorisnik=:idKorisnik")
    void updateKorisnickoIme(@Param(value ="idKorisnik")Long idKorisnik,@Param("korisnickoIme") String korisnickoIme);

    @Modifying
    @Query("update Korisnik k set k.zaporka= :novaZaporka where k.idKorisnik= :idKorisnik")
    void updateZaporka(@Param(value="idKorisnik")Long idKorisnik, @Param(value="novaZaporka") String novaZaporka);
}
