package s5.cloud.enchere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import s5.cloud.enchere.model.login.Token;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.repo.TokenRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public static String generateToken(String login, String password) {
        String originalInput = login + password + String.valueOf(new Timestamp(new Date().getTime()));
        String token = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return token;
    }

    // get the seqvalue
    public Integer getSequenceValue() {
        return tokenRepository.getSeqValue();
    }

    // method that find a token by idadministrator
    public Token findTokenByIdadministrator(Integer idadministrator) {
        return tokenRepository.findAdminValid(idadministrator);
    }

    // method that find a token by idadministrator
    public Token findTokenByIdcustomer(Integer idcustomer) {
        return tokenRepository.findCustomerValid(idcustomer);
    }

    public void save(Token currentToken) {
        tokenRepository.save(currentToken);
    }

    public Token findByTokenValue(String token_value) {
        return tokenRepository.findByTokenValue(token_value);
    }

    public Token hasToken(HttpHeaders headers, HttpServletResponse response) throws IOException {
        String token = this.getToken(headers);
        if(token==null || token.equals("")){
            return null;
        }
        token = token.replace("Bearer ", "");
        Token tokenObj = this.findByTokenValue(token);
        return tokenObj;
    }

    public ResponseEntity<Object> notConnected() {
        return ResponseEntity.badRequest().body("Not connected");
    }

    public String getToken(HttpHeaders headers) {
        String token = headers.getFirst("Authorization");
        if (token == null) {
            return null;
        }
        return token;
    }

    public void deleteToken(String tokenValue) {
        tokenRepository.deleteTokenByValue(tokenValue);
    }

    public void delete(Token currentToken) {
        tokenRepository.delete(currentToken);
    }

    public Token getCurrentToken(Users user) {
        Token currentToken = findTokenByIdadministrator(user.getId());
        if (currentToken != null) {
             currentToken.setUsers(null);
             currentToken.setId(null);
             return currentToken;
        }
        currentToken = new Token();
        String token = TokenService.generateToken(user.getEmail(),user.getPassword());
        currentToken.setUsers(user);
        currentToken.setValue(token);
        currentToken.setCreation_date(new Date());
        currentToken.setExpiration_date(new Date(new Date().getTime() + Token.EXPIRATION));
        currentToken.setId(getSequenceValue());
        save(currentToken);
        currentToken.setUsers(null);
        currentToken.setId(null);
        return currentToken;
   }
    public void deleteToken(Token currentToken) {
        tokenRepository.delete(currentToken);
    }
}
