package njust.service;

import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

public interface UserService {
    User save(User user);
    User deleteUser(Integer userId);
    User findUserById(Integer userId);
    Page<User> findAll(Pageable pageable);
//    User modifyUser(Integer userId,User u);
    Page<Resource> getUploadedResources(Integer userId,Pageable pageable);
//    List<Resource> getWaitCheckResources( Integer userId);
//    List<Resource> getCheckedResources(Integer userId);
    Page<Resource> getDownloadedResources(Integer userId,Pageable pageable);
    Page<AuctionMsg> getAuctionMsgs( Integer userId,Pageable pageable);

    User updateUserPassword(Integer userId,String password);
    User loginUser( String username,String password);
    User updateUser(User user,Integer depId);

}
