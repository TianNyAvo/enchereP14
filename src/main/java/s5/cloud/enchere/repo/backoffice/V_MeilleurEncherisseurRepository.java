package s5.cloud.enchere.repo.backoffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import s5.cloud.enchere.model.backoffice.V_MeilleurEncherisseur;

import java.util.List;

@Repository
public interface V_MeilleurEncherisseurRepository extends JpaRepository<V_MeilleurEncherisseur,Integer> {
    @Query(value = "SELECT * FROM v_meilleur_enrichisseur", nativeQuery = true)
    List<Object> getMeilleurEncherisseur();
}
