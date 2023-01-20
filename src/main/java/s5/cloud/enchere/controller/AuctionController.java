package s5.cloud.enchere.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import s5.cloud.enchere.model.enchere.Auction;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.model.enchere.AuctionOperation;
import s5.cloud.enchere.model.enchere.Galerie;
import s5.cloud.enchere.model.enchere.RechercheAvance;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.enchere.AuctionOperationService;
import s5.cloud.enchere.service.enchere.AuctionService;
import s5.cloud.enchere.service.enchere.HAuctionService;
import s5.cloud.enchere.service.enchere.PAuctionService;
import s5.cloud.enchere.service.enchere.VAuctionService;
import s5.cloud.enchere.service.enchere.VAuctionService;
import s5.cloud.enchere.service.login.UserService;

@RestController
public class AuctionController {
     @Autowired
     private UserService userS;

     
     @Autowired
     private VAuctionService vauctionS;

     @Autowired
     private AuctionService auctionS;

     @Autowired
     private TokenService tokenS;

     @Autowired
     private AuctionOperationService auctionOS;
     @Autowired
     private HAuctionService hauc;

     @Autowired
     private PAuctionService pauc;

     @GetMapping("/encheres/{page}")
     public ResponseEntity<Object> listeEnchere(@PathVariable int page) {
          try {
               Success success = new Success(vauctionS.findAll(page), "liste des enchere");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @GetMapping("/encheres/encours/{page}")
     public ResponseEntity<Object> listeEnchereEnCours(@PathVariable int page) {
          try {
               Success success = new Success(vauctionS.findEnCours(page), "liste des enchere");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @GetMapping("/enchere/{id}")
     public ResponseEntity<Object> ficheEnchere(@PathVariable int id) {
          try {
               Success success = new Success(auctionS.findById(id), "fiche d'une enchere");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }
     @PostMapping("/encheres/recherche")
     public ResponseEntity<Object> rechercheEnchere(@RequestBody RechercheAvance crit) {
          try {

               Success success = new Success(auctionS.findByCriteria(crit), "recherche d'une enchere");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               e.printStackTrace();
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PostMapping("/customer/{customerid}/enchere/{enchereid}/offre")
     public ResponseEntity<Object> encherir(@PathVariable int customerid, @PathVariable int enchereid, @RequestBody AuctionOperation operation,
               @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid);
               if (user == null)
                    throw new Exception("User not found");
               if (user.getRole().getId() == 1)
                    throw new Exception("You are not a customer");
               operation.setCustomer(new Users(customerid));
               operation.setAuctionId(enchereid);
               Success success = new Success(auctionOS.create(operation), "Recharge done");
               return ResponseEntity.ok().body(success);

          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               e.printStackTrace();
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PostMapping("/customer/{customerid}/enchere")
     public ResponseEntity<Object> insert(@RequestBody Auction enchere, @PathVariable int customerid,
               @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid);
               if (user == null)
                    throw new Exception("User not found");
               if (user.getRole().getId() == 1)
                    throw new Exception("You are not a customer");
               enchere.setSeller(new Users(customerid));
               enchere.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
               Success success = new Success(auctionS.insert(enchere), "insertion réussie");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               e.printStackTrace();
               return ResponseEntity.badRequest().body(error);
          }
     }
     

     @PutMapping("/customer/{customerid}/enchere/{auctionid}/photo")
     public ResponseEntity<Object> addPhoto(@RequestBody List<Galerie> galerie, @PathVariable int customerid,
               @PathVariable int auctionid,
               @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid);
               if (user == null)
                    throw new Exception("User not found");
               if (user.getRole().getId() == 1)
                    throw new Exception("You are not a customer");
               Success success = new Success(auctionS.addPhoto(galerie, auctionid), "insertion réussie");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @GetMapping("/customer/{seller_id}/encheres")
     public ResponseEntity<Object> getHistoryById(@PathVariable int seller_id,
               @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(),
                              "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(seller_id);
               if (user == null)
                    throw new Exception("User not found");
               if (user.getRole().getId() == 1)
                    throw new Exception("You are not a customer");

               Success success = new Success(hauc.getHistoryByIdUser(seller_id), "Historique des encheres");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @GetMapping("/customer/{customer_id}/encheres/participation")
     public ResponseEntity<Object> getParticipationById(@PathVariable int customer_id,
               @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {

               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(),
                              "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customer_id);
               if (user == null)
                    throw new Exception("User not found");
               if (user.getRole().getId() == 1)
                    throw new Exception("You are not a customer");

               Success success = new Success(pauc.getParticipationByIdUser(customer_id), "Participations aux encheres");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }
}
