package s5.cloud.enchere.controller.backoffice;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s5.cloud.enchere.model.backoffice.Settings;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.backofficce.SettingsService;

@RestController
public class SettingsController {
    private final SettingsService settingsService;
    private final TokenService tokenService;

    public SettingsController(SettingsService settingsService, TokenService tokenService) {
        this.settingsService = settingsService;
        this.tokenService = tokenService;
    }

    @GetMapping("/settings")
    public ResponseEntity<Object> getSettings(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(settingsService.getAllSettings()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @PutMapping("/setting/{id}")
    public ResponseEntity<Object> updateSetting(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer id, @RequestBody Settings requestSettings) {
        try {
            if (tokenService.hasToken(headers, httpResponse) == null) {
                throw new IllegalArgumentException("Invalid token");
            }
            Settings setting = settingsService.getSettingById(id);
            if (setting == null) {
                throw new IllegalArgumentException("Setting not found");
            }
            setting.setValue(requestSettings.getValue());
            settingsService.updateSettings(setting);
            return ResponseEntity.ok().body(new Success("Setting updated"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }
}
