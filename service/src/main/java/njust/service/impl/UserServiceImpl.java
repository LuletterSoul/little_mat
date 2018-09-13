package njust.service.impl;

import njust.dao.*;
import njust.domain.*;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserJpaDao userJpaDao;
    private AuctionMsgJpaDao auctionMsgJpaDao;
    private AccountJpaDao accountJpaDao;
    private ResourceJpaDao resourceJpaDao;
    private DepartmentJpaDao departmentJpaDao;
    private DownloadRecordJpaDao downloadRecordJpaDao;

    @Autowired
    public void setDownloadRecordJpaDao(DownloadRecordJpaDao downloadRecordJpaDao) {
        this.downloadRecordJpaDao = downloadRecordJpaDao;
    }

    @Autowired
    public void setDepartmentJpaDao(DepartmentJpaDao departmentJpaDao) {
        this.departmentJpaDao = departmentJpaDao;
    }

    @Autowired
    public void setResourceJpaDao(ResourceJpaDao resourceJpaDao) {
        this.resourceJpaDao = resourceJpaDao;
    }

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
        accountJpaDao.delete(user.getAccount());
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        return userJpaDao.findOne(userId);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userJpaDao.findAll(pageable);
    }

//    @Override
//    public User modifyUser(Integer userId,User u)
//    {
//        User user=new User();
//        user=userJpaDao.findOne(userId);
//        user.setEmail(u.getEmail());
//        user.setMajor(u.getMajor());
//        user.setGrade(u.getGrade());
//        user.setGender(u.getGender());
//        user.setName(u.getName());
//        user.setPhone(u.getPhone());
//        userJpaDao.save(user);
//        return user;
//    }

    @Override
    public Page<Resource> getUploadedResources(Integer userId,Pageable pageable) {
        User user=userJpaDao.findOne(userId);
        return resourceJpaDao.findResourcesByUploader(user,pageable);
    }

//    @Override
//    public Page<Resource> getWaitCheckResources(Integer userId,Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Page<Resource> getCheckedResources(Integer userId,Pageable pageable) {
//        return null;
//    }

    @Override
    public Page<Resource> getDownloadedResources(Integer userId,Pageable pageable) {
        User user=userJpaDao.findOne(userId);
        return null;
    }

    @Override
    public Page<AuctionMsg> getAuctionMsgs(Integer userId,Pageable pageable) {
        User u=userJpaDao.findOne(userId);

        return  null ;
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
        Account account = accountJpaDao.login(username,password);
        if(account==null)return null;
        else return account.getUser();
    }

    @Override
    public User updateUser(User user,Integer depId) {

        User user1 = userJpaDao.findOne(user.getUserId());
        if(depId!=null){
            user1.setDepartment(departmentJpaDao.findOne(depId));
        }
        user1.setName(user.getName());
        user1.setGender(user.getGender());
        user1.setMajor(user.getMajor());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        userJpaDao.save(user1);
        return user1;
    }

    @Override
    public User createUser(User user) {
        Account account = new Account();
        account.setUsername(user.getAccount().getUsername());
        account.setPassword(user.getAccount().getPassword());
        account = accountJpaDao.save(account);
        user.setAccount(account);
        user.setDepartment(departmentJpaDao.findOne(user.getDepartment().getDepId()));
        return userJpaDao.save(user);

    }
}
