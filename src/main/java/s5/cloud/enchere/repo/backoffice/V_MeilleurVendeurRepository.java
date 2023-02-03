package s5.cloud.enchere.repo.backoffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import s5.cloud.enchere.model.backoffice.V_meilleurVendeur;

import java.util.List;

@Repository
public interface V_MeilleurVendeurRepository extends JpaRepository<V_meilleurVendeur,Integer> {
    @Query(value = "SELECT * FROM v_meilleur_vendeur", nativeQuery = true)
    List<Object> getMeilleursVendeurs();
}
