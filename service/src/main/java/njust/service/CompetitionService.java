package njust.service;

import njust.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
    Competition save(Competition competition);
    Competition deleteCompetition(Integer competitionId);
    Competition findCompetitionById(Integer competitionId);
    Page<Competition> findAll(Pageable pageable);
}
