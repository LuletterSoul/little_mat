package njust.service;

import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User deleteUser(Integer userId);
    User findUserById(Integer userId);
    List<User>  findAll();
    User modifyUser(Integer userId,User u);
    List<Resource> getUploadedResources(Integer userId);
    List<Resource> getWaitCheckResources( Integer userId);
    List<Resource> getCheckedResources(Integer userId);
    List<Resource> getDownloadedResources(Integer userId);
    List<AuctionMsg> getAuctionMsgs( Integer userId);
    AuctionMsg createAuctionMsg(Integer userId, AuctionMsg auctionMsg);
    User updateUserPassword(Integer userId,String password);
    User loginUser( String username,String password);

}
