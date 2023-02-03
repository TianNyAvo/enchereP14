package s5.cloud.enchere.controller.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import s5.cloud.enchere.model.backoffice.User;
import s5.cloud.enchere.model.login.Token;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.service.TokenService;
//import s5.cloud.enchere.service.backofficce.UService;
import s5.cloud.enchere.service.backofficce.UsersService;
import s5.cloud.enchere.util.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    private final UsersService userS;
    private final TokenService tokenService;

    //private final UService userService;

    @Autowired
    public AdministratorController(UsersService userS, TokenService tokenService) {
        this.userS = userS;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Users administrator) {
        try {
            administrator.setPassword(BCrypt.hashPassword(administrator.getPassword()));
            System.out.println(administrator.getPassword());
            Users admin= userS.findAdminByEmailAndPassword(administrator.getEmail(),administrator.getPassword());
            if(admin==null) {
                throw new IllegalArgumentException("Email ou mot de passe incorrect");
            }
            Token currentToken=this.tokenService.findTokenByIdadministrator(admin.getId());
            if(currentToken!=null) {
                this.tokenService.deleteToken(currentToken);
            }
            currentToken=new Token();
            String token= TokenService.generateToken(administrator.getEmail(),administrator.getPassword());
            
            currentToken.setUsers(admin);
            currentToken.setValue(token);
            currentToken.setCreation_date(new Timestamp(new Date().getTime()));
            currentToken.setExpiration_date(new Timestamp(new Date().getTime()+Token.EXPIRATION));
            tokenService.save(currentToken);
            return ResponseEntity.ok().body(new Success(currentToken));
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Erreur lors de la génération du token"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }
    // save admin
    @PostMapping("")
    public ResponseEntity<Object> saveAdmin(@RequestBody Users admin) {
        try {
            admin.setPassword(BCrypt.hashPassword(admin.getPassword()));
            admin.setJoinedDate(new Timestamp(new Date().getTime()));
            Users adminSaved = userS.saveAdmin(admin);
            return ResponseEntity.ok().body(new Success("Admin saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }
}
