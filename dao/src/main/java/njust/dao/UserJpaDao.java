package njust.dao;

import njust.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaDao extends JpaRepository<User,Integer> {
}
