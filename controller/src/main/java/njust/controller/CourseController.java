package njust.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njust.domain.Course;
import njust.service.CourseService;


@Api(description = "课程业务")
@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService)
    {
        this.courseService = courseService;
    }

    // @ApiOperation(value = "获取资料")
    // @GetMapping
    // public ResponseEntity<List<Resource>> getAuctionMsgs(@ApiParam("课程Id")
    // @RequestParam("courseId") Integer courseId,
    // @ApiParam("院系Id")@RequestParam("depId") Integer depId,
    // @RequestParam(required = false,defaultValue = "") Integer type){
    // return null;
    // }

    /**
     * 若前端传入的查询参数为空，则分页查出库内的课程信息,默认每页10条信息，按课程号降序排列 如果传入了查询参数，根据参数筛选出课程信息
     *
     * @param depId
     * @return
     */
    @ApiOperation(value = "获取课程（测试通过）")
    @GetMapping
    public ResponseEntity<Page<Course>> findCourses(@PageableDefault(size = 20, sort = {
        "courseId"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                   @ApiParam("课程所属的院系Id") @RequestParam(value = "depId", required = false, defaultValue = "") Integer depId)
    {
        return new ResponseEntity<>(courseService.findCourse(pageable,depId),HttpStatus.OK);
    }

    @ApiOperation(value = "获取单门课程信息（测试通过）")
    @GetMapping(value="/{courseId}")
    public ResponseEntity<Course> findCourses(@PathVariable(value = "courseId") Integer couseId)
    {
        return new ResponseEntity<>(courseService.findCourseById(couseId),HttpStatus.OK);
    }
    // @ApiOperation(value = "获取一门课的全部资料")
    // @GetMapping(value = "/{courseId}")
    // public ResponseEntity<List<Resource>> getAuctionMsgs(@PathVariable("courseId") Integer
    // courseId){
    // return null;
    // }

    @ApiOperation(value="添加课程（测试通过）")
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseService.save(course),HttpStatus.CREATED);
    }

    @ApiOperation(value="删除课程（测试通过）")
    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable(value = "courseId") Integer courseId){
        return new ResponseEntity<>(courseService.deleteCourse(courseId),HttpStatus.NO_CONTENT);
    }
}
