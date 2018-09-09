package njust.controller;

import io.swagger.annotations.Api;
import njust.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "比赛信息业务")
public class CompetitionController {
    private CompetitionService competitionService;

    @Autowired
    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
}
