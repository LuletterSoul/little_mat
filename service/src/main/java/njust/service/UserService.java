package njust.service;

import njust.domain.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User deleteUser(Integer userId);
    User findUserById(Integer userId);
    List<User>  findAll();
}
