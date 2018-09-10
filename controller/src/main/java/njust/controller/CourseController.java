package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "课程业务")
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    @ApiOperation(value = "根据课程和类型查找资料")
    @GetMapping(value = "/{courseId}/type/{type}")
    public ResponseEntity<List<Resource>> getAuctionMsgs(@PathVariable("courseId") Integer courseId,
                                                         @PathVariable("type") Integer type){
        return null;
    }

    @ApiOperation(value = "获取一门课的全部资料")
    @GetMapping(value = "/{courseId}")
    public ResponseEntity<List<Resource>> getAuctionMsgs(@PathVariable("courseId") Integer courseId){
        return null;
    }
}
