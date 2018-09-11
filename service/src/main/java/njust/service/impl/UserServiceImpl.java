package njust.service.impl;

import njust.dao.AccountJpaDao;
import njust.dao.AuctionMsgJpaDao;
import njust.dao.UserJpaDao;
import njust.domain.Account;
import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserJpaDao userJpaDao;
    private AuctionMsgJpaDao auctionMsgJpaDao;
    private AccountJpaDao accountJpaDao;

    @Autowired
    public void setAuctionMsgJpaDao(AuctionMsgJpaDao auctionMsgJpaDao) {
        this.auctionMsgJpaDao = auctionMsgJpaDao;
    }

    @Autowired
    public void setAccountJpaDao(AccountJpaDao accountJpaDao) {
        this.accountJpaDao = accountJpaDao;
    }

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

    @Override
    public User modifyUser(Integer userId,User u)
    {
        User user=new User();
        user=userJpaDao.findOne(userId);
        user.setEmail(u.getEmail());
        user.setMajor(u.getMajor());
        user.setGrade(u.getGrade());
        user.setGender(u.getGender());
        user.setName(u.getName());
        user.setPhone(u.getPhone());
        userJpaDao.save(user);
        return user;
    }

    @Override
    public List<Resource> getUploadedResources(Integer userId) {
        User u=userJpaDao.findOne(userId);

        return  new ArrayList<>(u.getUploadRes()) ;
    }

    @Override
    public List<Resource> getWaitCheckResources(Integer userId) {
        return null;
    }

    @Override
    public List<Resource> getCheckedResources(Integer userId) {
        return null;
    }

    @Override
    public List<Resource> getDownloadedResources(Integer userId) {
        User u=userJpaDao.findOne(userId);

        return  new ArrayList<>(u.getDownloadRes()) ;
    }

    @Override
    public List<AuctionMsg> getAuctionMsgs(Integer userId) {
        User u=userJpaDao.findOne(userId);

        return  new ArrayList<>(u.getAuctions()) ;
    }

    @Override
    public AuctionMsg createAuctionMsg(Integer userId, AuctionMsg auctionMsg) {
        User u=userJpaDao.findOne(userId);
        auctionMsgJpaDao.save(auctionMsg);
       u.getAuctions().add(auctionMsg);
        return auctionMsg;
    }

    @Override
    public User updateUserPassword(Integer userId, String password) {
        User user = userJpaDao.findOne(userId);
        Account account = user.getAccount();
        account.setPassword(password);
        accountJpaDao.save(account);
        user.setAccount(account);
        return user;
    }

    @Override
    public User loginUser(String username, String password) {
        return null;
    }


}
