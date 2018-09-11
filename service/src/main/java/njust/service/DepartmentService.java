package njust.service;

import njust.domain.Course;
import njust.domain.Department;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);
    Department deleteDepartment(Integer departmentId);
    Department findDepartmentById(Integer departmentId);
    List<Department> findAll();

    Course addCourse(Integer depId,Integer courseId);
}
