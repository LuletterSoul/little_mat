package njust.service;

import njust.domain.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    Course deleteCourse(Integer courseId);
    Course findCourseById(Integer courseId);
    List<Course> findAll();
}
