package s5.cloud.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.login.UserService;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;

@RestController
public class UserController  {
     @Autowired
     private UserService userS;
     @Autowired
     private TokenService tokenS;

     @PostMapping("/customer")
     public ResponseEntity<Object> inscription(@RequestBody Users user, @RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
          try {
               Success success = new Success(userS.inscriptionCustomer(user), "Inscription réussie");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PostMapping("/customer/login")
     public ResponseEntity<Object> login(@RequestBody Users administrator) {
          try{
               Users findUser=userS.login(administrator);
               Success success = new Success(tokenS.getCurrentToken(findUser), "Login réussi");
               return ResponseEntity.ok().body(success);
          }catch(Exception e){
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PostMapping("/administrator/login")
     public ResponseEntity<Object> loginadmin(@RequestBody Users administrator) {
          try{
               Users findUser=userS.login(administrator);
               Success success = new Success(tokenS.getCurrentToken(findUser), "Login réussi");
               return ResponseEntity.ok().body(success);
          }catch(Exception e){
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }
}
