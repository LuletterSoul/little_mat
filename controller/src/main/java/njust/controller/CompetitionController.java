package njust.controller;


import io.swagger.annotations.ApiOperation;
import njust.domain.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "添加比赛（测试通过）")
    @PostMapping
    public ResponseEntity<Competition> addCompetition(@RequestBody Competition competition){
        return new ResponseEntity<>(competitionService.save(competition),HttpStatus.CREATED);
    }

    @ApiOperation(value = "删除比赛（测试通过）")
    @DeleteMapping(value = "/{comId}")
    public ResponseEntity<Competition> deleteCompetition(@PathVariable("comId")Integer comId){
        return new ResponseEntity<>(competitionService.deleteCompetition(comId),HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="更新比赛信息（测试通过）")
    @PatchMapping(value = "/{comId}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable("comId")Integer comId,
                                                         @RequestParam("comName")String comName){
        return new ResponseEntity<>(competitionService.updateCompetition(comId,comName),HttpStatus.OK);
    }

    @ApiOperation(value="获得所有比赛信息（测试通过）")
    @GetMapping
    public ResponseEntity<Page<Competition>> findCompetition(@PageableDefault(size = 10, sort = {
            "comId"}, direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(competitionService.findAll(pageable),HttpStatus.OK);
    }

    @ApiOperation(value="获得一项比赛信息（测试通过）")
    @GetMapping(value = "/{comId}")
    public ResponseEntity<Competition> findCompetition(@PathVariable("comId")Integer comId){
        return new ResponseEntity<>(competitionService.findCompetitionById(comId),HttpStatus.OK);
    }
}
