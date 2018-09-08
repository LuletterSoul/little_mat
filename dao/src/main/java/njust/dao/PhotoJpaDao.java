package njust.dao;

import njust.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoJpaDao extends JpaRepository<Photo,Integer> {
}
