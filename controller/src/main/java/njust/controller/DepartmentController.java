package njust.controller;

import io.swagger.annotations.Api;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "学院业务")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
