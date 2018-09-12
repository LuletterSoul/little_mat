package njust.service.impl;

import njust.dao.AccountJpaDao;
import njust.dao.AdministratorJpaDao;
import njust.domain.Account;
import njust.domain.Administrator;
import njust.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private AdministratorJpaDao administratorJpaDao;
    private AccountJpaDao accountJpaDao;

    @Autowired
    public void setAccountJpaDao(AccountJpaDao accountJpaDao) {
        this.accountJpaDao = accountJpaDao;
    }

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

    @Override
    public Administrator loginAdministrator(String username, String password) {
        Account account = accountJpaDao.login(username,password);
        if(account==null)return null;
        else return account.getAdministrator();
    }
}
