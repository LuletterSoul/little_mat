package njust.service;

import njust.domain.Course;
import njust.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);
    Department deleteDepartment(Integer departmentId);
    Department findDepartmentById(Integer departmentId);
    Page<Department> findAll(Pageable pageable);
    Course addCourse(Integer depId,Integer courseId);
}
