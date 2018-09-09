package njust.controller;

import io.swagger.annotations.Api;
import njust.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "管理员业务")
public class AdministratorController {

    private AdminstratorService adminstratorService;

    @Autowired
    public void setAdminstratorService(AdminstratorService adminstratorService) {
        this.adminstratorService = adminstratorService;
    }
}
