package njust.controller;

import io.swagger.annotations.Api;
import njust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "课程业务")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
