package njust.dao;

import njust.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorJpaDao extends JpaRepository<Administrator,Integer> {
}
