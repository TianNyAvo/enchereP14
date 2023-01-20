package s5.cloud.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.model.recharge.Account;
import s5.cloud.enchere.service.AccountService;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.login.UserService;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;

@RestController
public class RechargeController {
     @Autowired
     private TokenService tokenS;

     @Autowired
     private UserService userS;

     @Autowired 
     private AccountService accountS;

     @PostMapping("/customer/{customerid}/recharge")
     public ResponseEntity<Object> recharge(@PathVariable int customerid,@RequestBody Account account, @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid); 
               if (user == null)throw new Exception("User not found");
               if(user.getRole().getId()==1)throw new Exception("You are not a customer");
               account.setCustomer(user);
               Success success= new Success(accountS.recharger(account), "Recharge done");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }
     @GetMapping("/customer/{customerid}/recharge/etatcompte")
     public ResponseEntity<Object>etatCompte(@PathVariable int customerid, @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse){
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid); 
               if (user == null)throw new Exception("User not found");
               if(user.getRole().getId()==1)throw new Exception("You are not a customer");
             

               Success success= new Success(accountS.stateAccount(customerid), "Recharge done");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }
     @GetMapping("/customer/{customerid}/recharges")
     public ResponseEntity<Object>historique(@PathVariable int customerid, @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse){
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users user = userS.findById(customerid); 
               if (user == null)throw new Exception("User not found");
               if(user.getRole().getId()==1)throw new Exception("You are not a customer");
             
               Success success= new Success(accountS.historiqueValide(customerid), "Recharge done");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

}
