package njust.service.impl;

import njust.dao.CourseJpaDao;
import njust.dao.DepartmentJpaDao;
import njust.dao.DownloadRecordJpaDao;
import njust.dao.ResourceJpaDao;
import njust.domain.Course;
import njust.domain.Department;
import njust.domain.DownloadRecord;
import njust.domain.Resource;
import njust.service.CourseService;
import njust.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseJpaDao courseJpaDao;
    private DepartmentJpaDao departmentJpaDao;
    private ResourceJpaDao resourceJpaDao;
    private DownloadRecordJpaDao downloadRecordJpaDao;

    @Autowired
    public void setDownloadRecordJpaDao(DownloadRecordJpaDao downloadRecordJpaDao) {
        this.downloadRecordJpaDao = downloadRecordJpaDao;
    }

    @Autowired
    public void setResourceJpaDao(ResourceJpaDao resourceJpaDao) {
        this.resourceJpaDao = resourceJpaDao;
    }

    @Autowired
    public void setDepartmentJpaDao(DepartmentJpaDao departmentJpaDao) {
        this.departmentJpaDao = departmentJpaDao;
    }

    @Autowired
    public void setCourseJpaDao(CourseJpaDao courseJpaDao) {
        this.courseJpaDao = courseJpaDao;
    }

    @Override
    public Course save(Course course) {
        return courseJpaDao.save(course);
    }

    @Override
    public Course deleteCourse(Integer courseId) {
        Course course = courseJpaDao.findOne(courseId);
        Set<Resource> resources = course.getResources();
        for(Resource resource:resources){
            FileUtil.deleteFile(resource.getPath());
            downloadRecordJpaDao.delete(resource.getDownloadRecords());
            resourceJpaDao.delete(resource);
        }
        Set<Department> departments = course.getDepartments();
        for(Department department:departments){
            department.getCourses().remove(course);
            departmentJpaDao.save(department);
        }
        courseJpaDao.delete(course);
        return course;
    }

    @Override
    public Course findCourseById(Integer courseId) {
        return courseJpaDao.findOne(courseId);
    }

//    @Override
//    public List<Course> findAll() {
//        return courseJpaDao.findAll();
//    }

    @Override
    public Page<Course> findCourse(Pageable pageable,Integer depId) {
        if(depId != null){
            Department department = departmentJpaDao.findOne(depId);
            return courseJpaDao.findCoursesByDepartmentsOrderByCourseId(department,pageable);
        }else{
            return courseJpaDao.findAll(pageable);
        }
    }
}
