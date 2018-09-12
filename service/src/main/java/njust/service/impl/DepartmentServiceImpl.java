package njust.service.impl;

import njust.dao.CourseJpaDao;
import njust.dao.DepartmentJpaDao;
import njust.domain.Course;
import njust.domain.Department;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentJpaDao departmentJpaDao;

    private CourseJpaDao courseJpaDao;

    @Autowired
    public void setCourseJpaDao(CourseJpaDao courseJpaDao) {
        this.courseJpaDao = courseJpaDao;
    }

    @Autowired
    public void setDepartmentJpaDao(DepartmentJpaDao departmentJpaDao) {
        this.departmentJpaDao = departmentJpaDao;
    }

    @Override
    public Department save(Department department) {
        return departmentJpaDao.save(department);
    }

    @Override
    public Department deleteDepartment(Integer departmentId) {
        Department department = departmentJpaDao.findOne(departmentId);
        departmentJpaDao.delete(department);
        return department;
    }

    @Override
    public Department findDepartmentById(Integer departmentId) {
        return departmentJpaDao.findOne(departmentId);
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentJpaDao.findAll(pageable);
    }

    @Override
    public Course addCourse(Integer depId, Integer courseId) {
        Course course = courseJpaDao.findOne(courseId);
        Department department = departmentJpaDao.findOne(depId);
        course.getDepartments().add(department);
        department.getCourses().add(course);
        departmentJpaDao.save(department);
        return course;
    }


}
