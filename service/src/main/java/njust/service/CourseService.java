package njust.service;

import njust.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    Course deleteCourse(Integer courseId);
    Course findCourseById(Integer courseId);
//    List<Course> findAll();
    Page<Course> findCourse(Pageable pageable,Integer depId);

}
