package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.Administrator;
import njust.domain.User;
import njust.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "管理员业务")
@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController {

    private AdminstratorService adminstratorService;

    @Autowired
    public void setAdminstratorService(AdminstratorService adminstratorService) {
        this.adminstratorService = adminstratorService;
    }

    @ApiOperation(value = "管理员登陆")
    @GetMapping
    public ResponseEntity<Administrator> loginUser(@RequestBody Administrator administrator){
        return null;
    }
}
