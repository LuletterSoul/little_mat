package njust.service.impl;

import io.swagger.annotations.ApiOperation;
import njust.dao.CompetitionJpaDao;
import njust.dao.ResourceJpaDao;
import njust.domain.Competition;
import njust.domain.Resource;
import njust.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;


@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionJpaDao competitionJpaDao;
    private ResourceJpaDao resourceJpaDao;

    @Autowired
    public void setResourceJpaDao(ResourceJpaDao resourceJpaDao) {
        this.resourceJpaDao = resourceJpaDao;
    }

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
        Set<Resource> resources = competition.getResources();
        File fileTemp ;
        for(Resource resource:resources){
            fileTemp = new File(resource.getPath());
            if(fileTemp.exists()){
                fileTemp.delete();
            }
            resourceJpaDao.delete(resource);
        }
        competitionJpaDao.delete(competition);
        return competition;
    }

    @Override
    public Competition findCompetitionById(Integer competitionId) {
        return competitionJpaDao.findOne(competitionId);
    }

    @Override
    public Page<Competition> findAll(Pageable pageable) {
        return competitionJpaDao.findAll(pageable);
    }

    @Override
    public Competition updateCompetition(Integer comId, String comName) {
        Competition competition = competitionJpaDao.findOne(comId);
        competition.setComName(comName);
        return competitionJpaDao.save(competition);
    }


}
