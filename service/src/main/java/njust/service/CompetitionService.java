package njust.service;

import njust.domain.Competition;

import java.util.List;

public interface CompetitionService {
    Competition save(Competition competition);
    Competition deleteCompetition(Integer competitionId);
    Competition findCompetitionById(Integer competitionId);
    List<Competition> findAll();
}
