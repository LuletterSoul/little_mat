package njust.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import njust.service.CompetitionService;


@Api(description = "比赛信息业务")
@RestController
@RequestMapping(value = "/competitions")
public class CompetitionController
{
    private CompetitionService competitionService;

    @Autowired
    public void setCompetitionService(CompetitionService competitionService)
    {
        this.competitionService = competitionService;
    }
}
