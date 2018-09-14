package njust.service.impl;

import njust.dao.CourseJpaDao;
import njust.dao.DepartmentJpaDao;
import njust.dao.UserJpaDao;
import njust.domain.Course;
import njust.domain.Department;
import njust.domain.User;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentJpaDao departmentJpaDao;

    private CourseJpaDao courseJpaDao;

    private UserJpaDao userJpaDao;

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

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
        Set<User> users = department.getUsers();
        for(User user:users){
            user.setDepartment(null);
            userJpaDao.save(user);
        }
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
