package s5.cloud.enchere.controller.backoffice;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.backofficce.V_MeilleurEncherisseurService;
import s5.cloud.enchere.service.backofficce.V_MeilleurVendeurService;
import s5.cloud.enchere.service.backofficce.V_RppService;

@RestController
public class StatistiqueController {
    private final V_RppService v_rppService;
    private final TokenService tokenService;

    private final V_MeilleurEncherisseurService v_meilleurEncherisseurService;

    private final V_MeilleurVendeurService v_meilleurVendeurService;
    @Autowired
    public StatistiqueController(V_RppService v_rppService, TokenService tokenService, V_MeilleurEncherisseurService v_meilleurEncherisseurService, V_MeilleurVendeurService v_meilleurVendeurService) {
        this.v_rppService = v_rppService;
        this.tokenService = tokenService;
        this.v_meilleurEncherisseurService = v_meilleurEncherisseurService;
        this.v_meilleurVendeurService = v_meilleurVendeurService;
    }

    @GetMapping("/recette/annee/{annee}")
    public ResponseEntity<Object> getRecetteAnnee(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer annee) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(v_rppService.getRecetteAnnee(annee)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400,e.getMessage()));
        }
    }

    @GetMapping("/encherisseurs/desc")
    public ResponseEntity<Object> getEncherisseursDesc(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(v_meilleurEncherisseurService.getMeilleurEncherisseur()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400,e.getMessage()));
        }
    }

    @GetMapping("/vendeurs/desc")
    public ResponseEntity<Object> getVendeursDesc(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(v_meilleurVendeurService.getMeilleursVendeurs()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400,e.getMessage()));
        }
    }
}
