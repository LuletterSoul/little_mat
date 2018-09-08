package njust.dao;

import njust.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaDao extends JpaRepository<Account,Integer> {
}
