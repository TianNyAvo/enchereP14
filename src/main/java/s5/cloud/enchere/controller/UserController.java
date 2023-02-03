package s5.cloud.enchere.controller;

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
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.login.UserService;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;

@RestController
public class UserController {
     @Autowired
     private UserService userS;
     @Autowired
     private TokenService tokenS;

     @PostMapping("/customer")
     public ResponseEntity<Object> inscription(@RequestBody Users user, @RequestHeader HttpHeaders headers,
               HttpServletResponse httpResponse) {
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
          try {
               Users findUser = userS.login(administrator);
               Success success = new Success(tokenS.getCurrentToken(findUser, true), "Login réussi");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PutMapping("/customer/{customerid}")
     public ResponseEntity<Object> update(@RequestBody Users user, @RequestHeader HttpHeaders headers,
               HttpServletResponse httpResponse, @PathVariable int customerid) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users userC = userS.findById(customerid);
               if (userC == null)
                    throw new Exception("User not found");
               if (userC.getRole().getId() == 1)
                    throw new Exception("You are not a customer");
               Success success = new Success(userS.updateUser(userC, user), "changement réussi");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @GetMapping("/customer/{customerid}")
     public ResponseEntity<Object> getUser(@RequestHeader HttpHeaders headers,
               HttpServletResponse httpResponse, @PathVariable int customerid) {
          try {
               if (tokenS.hasToken(headers, httpResponse) == null) {
                    Error error = new Error(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
                    return ResponseEntity.badRequest().body(error);
               }
               Users userC = userS.findById(customerid);
               if (userC == null)
                    throw new Exception("User not found");
               if (userC.getRole().getId() == 1)
                    throw new Exception("You are not a customer");

               Success success = new Success(userC, "changement réussi");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

     @PostMapping("/administrator/login")
     public ResponseEntity<Object> loginadmin(@RequestBody Users administrator) {
          try {
               Users findUser = userS.login(administrator);
               Success success = new Success(tokenS.getCurrentToken(findUser, false), "Login réussi");
               return ResponseEntity.ok().body(success);
          } catch (Exception e) {
               Error error = new Error(400, e.getMessage());
               return ResponseEntity.badRequest().body(error);
          }
     }

}
