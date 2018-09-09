package njust.service.impl;

import njust.dao.CompetitionJpaDao;
import njust.domain.Competition;
import njust.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionJpaDao competitionJpaDao;

    @Autowired
    public void setCompetitionJpaDao(CompetitionJpaDao competitionJpaDao) {
        this.competitionJpaDao = competitionJpaDao;
    }

    @Override
    public Competition save(Competition competition) {
        return competitionJpaDao.save(competition);
    }

    @Override
    public Competition deleteCompetition(Integer competitionId) {
        Competition competition =  competitionJpaDao.findOne(competitionId);
        competitionJpaDao.delete(competition);
        return competition;
    }

    @Override
    public Competition findCompetitionById(Integer competitionId) {
        return competitionJpaDao.findOne(competitionId);
    }

    @Override
    public List<Competition> findAll() {
        return competitionJpaDao.findAll();
    }
}
