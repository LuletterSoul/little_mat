package njust.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;


public interface UserService
{
    User save(User user);

    User deleteUser(Integer userId);

    User findUserById(Integer userId);

    Page<User> findAll(Pageable pageable);

/*     User modifyUser(Integer userId,User u);
     List<Resource> getWaitCheckResources( Integer userId);
     List<Resource> getCheckedResources(Integer userId);*/

    Page<Resource> getUploadedResources(Integer userId, Pageable pageable);


    Page<Resource> getDownloadedResources(Integer userId, Pageable pageable);

    Page<AuctionMsg> getAuctionMsgs(Integer userId, Pageable pageable);

    User updateUserPassword(Integer userId, String password);

    User loginUser(String username, String password);

    User updateUser(User user, Integer depId);

    User createUser(User user);

    Page<User> findAllUser(Pageable pageable);
}
