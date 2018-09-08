package njust.dao;

import njust.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceJpaDao extends JpaRepository<Resource,Integer> {
}
