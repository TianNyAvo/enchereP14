package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.backoffice.V_RPP;
import s5.cloud.enchere.repo.backoffice.V_RppRepository;

import java.util.List;

@Service
public class V_RppService {
    private final V_RppRepository v_rppRepository;

    @Autowired
    public V_RppService(V_RppRepository v_rppRepository) {
        this.v_rppRepository = v_rppRepository;
    }

    public List<V_RPP> findAll() {
        return v_rppRepository.findAll();
    }

    public List<Object> getRecetteAnnee(Integer annee) {
        return v_rppRepository.getRecetteAnnee(annee);
    }
}
