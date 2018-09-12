package njust.dao;

import njust.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountJpaDao extends JpaRepository<Account,Integer> {

    @Query("select a from Account a where a.username=:username and a.password=:password")
    Account login(@Param("username") String username,@Param("password") String password);
}
