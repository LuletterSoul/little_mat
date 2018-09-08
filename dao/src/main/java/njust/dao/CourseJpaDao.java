package njust.dao;

import njust.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaDao  extends JpaRepository<Course,Integer> {
}
