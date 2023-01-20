package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.repo.backoffice.V_MeilleurVendeurRepository;

import java.util.List;

@Service
public class V_MeilleurVendeurService {
    private final V_MeilleurVendeurRepository v_meilleurVendeurRepository;

    @Autowired
    public V_MeilleurVendeurService(V_MeilleurVendeurRepository v_meilleurVendeurRepository) {
        this.v_meilleurVendeurRepository = v_meilleurVendeurRepository;
    }

    public List<Object> getMeilleursVendeurs() {
        return v_meilleurVendeurRepository.getMeilleursVendeurs();
    }
}
