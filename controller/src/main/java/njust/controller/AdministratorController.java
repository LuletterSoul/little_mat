package njust.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.Administrator;
import njust.service.AdministratorService;


@Api(description = "管理员业务")
@RestController
@RequestMapping(value = "/administrators")
public class AdministratorController
{

    private AdministratorService administratorService;

    @Autowired
    public void setAdministratorService(AdministratorService administratorService)
    {
        this.administratorService = administratorService;
    }

    @ApiOperation(value = "管理员登陆（测试通过）")
    @GetMapping
    public ResponseEntity<Administrator> loginAdministrator(@RequestParam(value = "username")String username,
                                                            @RequestParam(value = "password")String password)
    {
        return new ResponseEntity<>(administratorService.loginAdministrator(username,password),HttpStatus.OK);
    }
}
