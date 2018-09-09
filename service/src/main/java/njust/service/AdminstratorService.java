package njust.service;

import njust.domain.Administrator;

import java.util.List;

public interface AdminstratorService {
    Administrator save(Administrator administrator);
    Administrator deleteAdministrator(Integer administratorId);
    Administrator findAdministratorById(Integer administratorId);
    List<Administrator> findAll();
}
