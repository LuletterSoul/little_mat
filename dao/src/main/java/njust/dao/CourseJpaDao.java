package njust.dao;

import njust.domain.Course;
import njust.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaDao  extends JpaRepository<Course,Integer> {
    Page<Course> findCoursesByDepartmentsOrderByCourseId(Department depId, Pageable pageable);
}
