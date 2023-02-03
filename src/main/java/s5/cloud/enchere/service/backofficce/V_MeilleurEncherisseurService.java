package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.repo.backoffice.V_MeilleurEncherisseurRepository;

import java.util.List;

@Service
public class V_MeilleurEncherisseurService {
    private final V_MeilleurEncherisseurRepository v_meilleurEncherisseurRepository;

    @Autowired
    public V_MeilleurEncherisseurService(V_MeilleurEncherisseurRepository v_meilleurEncherisseurRepository) {
        this.v_meilleurEncherisseurRepository = v_meilleurEncherisseurRepository;
    }

    public List<Object> getMeilleurEncherisseur() {
        return v_meilleurEncherisseurRepository.getMeilleurEncherisseur();
    }
}
