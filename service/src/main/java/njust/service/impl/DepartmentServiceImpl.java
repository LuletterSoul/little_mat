package njust.service.impl;

import njust.dao.DepartmentJpaDao;
import njust.domain.Department;
import njust.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentJpaDao departmentJpaDao;

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
    public List<Department> findAll() {
        return departmentJpaDao.findAll();
    }
}
