package s5.cloud.enchere.controller.backoffice;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.service.AccountService;
import s5.cloud.enchere.service.TokenService;

@RestController
public class AccountController {
    private final AccountService accountService;
    private final TokenService tokenService;

    public AccountController(AccountService accountService, TokenService tokenService) {
        this.accountService = accountService;
        this.tokenService = tokenService;
    }

    @GetMapping("/refund_requests")
    public ResponseEntity<Object> getRefundRequests(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(accountService.getRefundRequests()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @PutMapping("/refund_request/{id}/accept")
    public ResponseEntity<Object> acceptRefundRequest(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer id,@RequestBody Users user) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            Integer admin_id=user.getId();
            accountService.acceptRefundRequest(id,admin_id);
            return ResponseEntity.ok().body(new Success("Refund request accepted"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @GetMapping("/accepted_refund_requests")
    public ResponseEntity<Object> getAcceptedRefundRequests(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(accountService.getAcceptedRefundRequests()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }
}
