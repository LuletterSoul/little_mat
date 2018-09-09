package njust.service.impl;

import njust.dao.UserJpaDao;
import njust.domain.User;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserJpaDao userJpaDao;

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public User save(User user) {
        return  userJpaDao.save(user);
    }

    @Override
    public User deleteUser(Integer userId) {
        User user = userJpaDao.findOne(userId);
        userJpaDao.delete(user);
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        return userJpaDao.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userJpaDao.findAll();
    }
}
