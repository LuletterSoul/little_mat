package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.Course;
import njust.domain.Department;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @ApiOperation(value="为学院添加某门课程（测试通过）")
    @PutMapping(value = "/{depId}/courses/{courseId}")
    public ResponseEntity<Course> addCourse(@PathVariable("depId") Integer depId,
                                            @PathVariable("courseId") Integer courseId){
        return new ResponseEntity<>(departmentService.addCourse(depId,courseId),HttpStatus.OK);
    }

    @ApiOperation(value="添加学院（测试通过）")
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.save(department),HttpStatus.CREATED);
    }

    @ApiOperation(value="获取所有学院（测试通过）")
    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartments(@PageableDefault(size = 20, sort = {
            "depId"}, direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(departmentService.findAll(pageable),HttpStatus.OK);
    }

}
