package s5.cloud.enchere.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.login.Token;



public interface TokenRepository extends JpaRepository <Token,String> {
    @Query(value="SELECT id,\"value\",creation_date,expiration_date,users_id from v_valid_token_admin WHERE users_id=?", nativeQuery=true)
    Token findAdminValid(Integer administratorId);

    @Query(value="SELECT * from v_valid_token_customer WHERE users_id=?", nativeQuery=true)
    Token findCustomerValid(Integer customerId);

    @Query(value = "select nextval('token_id_seq')", nativeQuery =
            true)
    Integer getSeqValue();


    @Query(value = "select * from v_valid_token where value = ?1 limit 1", nativeQuery = true)
    Token findByTokenValue(String tokenValue);

    @Query(value = "delete from token where value= ?1", nativeQuery = true)
    void deleteTokenByValue(String token_value);
}
