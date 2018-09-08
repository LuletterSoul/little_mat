package njust.dao;

import njust.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionJpaDao extends JpaRepository<Competition,Integer> {
}
