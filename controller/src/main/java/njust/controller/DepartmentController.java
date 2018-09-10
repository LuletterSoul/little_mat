package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.Course;
import njust.domain.Department;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "学院业务")
@RestController
@RequestMapping(value = "/deps")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value="添加课程")
    @PostMapping(value = "/{depId}/courses")
    public ResponseEntity<Course> createCourse(@PathVariable("depId") Integer depId,
                                               @RequestBody Course course){
        return null;
    }

    @ApiOperation(value="添加学院")
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.save(department),HttpStatus.CREATED);
    }

    @ApiOperation(value="获取所有学院")
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.findAll(),HttpStatus.OK);
    }

}
