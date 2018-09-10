package njust.controller;

import io.swagger.annotations.Api;
import njust.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "比赛信息业务")
@RestController
@RequestMapping(value = "/competition")
public class CompetitionController {
    private CompetitionService competitionService;

    @Autowired
    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
}
