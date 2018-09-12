package njust.service;

import njust.domain.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator save(Administrator administrator);
    Administrator deleteAdministrator(Integer administratorId);
    Administrator findAdministratorById(Integer administratorId);
    List<Administrator> findAll();
    Administrator loginAdministrator(String username,String password);

}
