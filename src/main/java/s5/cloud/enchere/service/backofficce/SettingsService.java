package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.backoffice.Settings;
import s5.cloud.enchere.repo.backoffice.SettingsRepository;

@Service
public class SettingsService {
    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    // Crud operations
    public void createSettings(s5.cloud.enchere.model.backoffice.Settings settings) {
        this.settingsRepository.save(settings);
    }

    public void updateSettings(s5.cloud.enchere.model.backoffice.Settings settings) {
        this.settingsRepository.save(settings);
    }

    public void deleteSettings(s5.cloud.enchere.model.backoffice.Settings settings) {
        this.settingsRepository.delete(settings);
    }

    public s5.cloud.enchere.model.backoffice.Settings getSettingById(Integer id) {
        return this.settingsRepository.findById(id).orElse(null);
    }

    public Iterable<s5.cloud.enchere.model.backoffice.Settings> getAllSettings() {
        return this.settingsRepository.findAll();
    }
}
