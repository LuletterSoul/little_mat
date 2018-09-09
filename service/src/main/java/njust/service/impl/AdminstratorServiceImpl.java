package njust.service.impl;

import njust.dao.AdministratorJpaDao;
import njust.domain.Administrator;
import njust.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminstratorServiceImpl implements AdminstratorService {

    private AdministratorJpaDao administratorJpaDao;

    @Autowired
    public void setAdministratorJpaDao(AdministratorJpaDao administratorJpaDao) {
        this.administratorJpaDao = administratorJpaDao;
    }

    @Override
    public Administrator save(Administrator administrator) {
        return administratorJpaDao.save(administrator);
    }

    @Override
    public Administrator deleteAdministrator(Integer administratorId) {
        Administrator administrator = administratorJpaDao.findOne(administratorId);
        administratorJpaDao.delete(administrator);
        return administrator;
    }

    @Override
    public Administrator findAdministratorById(Integer administratorId) {
        return administratorJpaDao.findOne(administratorId);
    }

    @Override
    public List<Administrator> findAll() {
        return administratorJpaDao.findAll();
    }
}
