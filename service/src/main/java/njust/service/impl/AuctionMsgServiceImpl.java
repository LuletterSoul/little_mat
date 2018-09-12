package njust.service.impl;

import njust.dao.AuctionMsgJpaDao;
import njust.dao.PhotoJpaDao;
import njust.dao.UserJpaDao;
import njust.domain.AuctionMsg;
import njust.domain.Photo;
import njust.domain.User;
import njust.service.AuctionMsgService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class AuctionMsgServiceImpl implements AuctionMsgService {

    private AuctionMsgJpaDao auctionMsgJpaDao;
    private UserJpaDao userJpaDao;
    private PhotoJpaDao photoJpaDao;

    @Autowired
    public void setPhotoJpaDao(PhotoJpaDao photoJpaDao) {
        this.photoJpaDao = photoJpaDao;
    }

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Autowired
    public void setAuctionMsgJpaDao(AuctionMsgJpaDao auctionMsgJpaDao) {
        this.auctionMsgJpaDao = auctionMsgJpaDao;
    }

    @Override
    public AuctionMsg save(AuctionMsg auctionMsg) {
        return auctionMsgJpaDao.save(auctionMsg);
    }

    @Override
    public AuctionMsg deleteAuctionMsg(Integer auctionMsgId) {
        AuctionMsg auctionMsg = auctionMsgJpaDao.findOne(auctionMsgId);
        auctionMsgJpaDao.delete(auctionMsg);
        return auctionMsg;
    }

    @Override
    public AuctionMsg findAuctionMsgById(Integer auctionMsgId) {
        return auctionMsgJpaDao.findOne(auctionMsgId);
    }

    @Override
    public Page<AuctionMsg> findAll(Pageable pageable, Integer publisherId, Integer status) {
        if(publisherId == null){
            if(status == null)status = new Integer(0);
            return auctionMsgJpaDao.findAuctionMsgByStatus(status,pageable);
        }else{
            User publisher = userJpaDao.findOne(publisherId);
            if(status == null)return auctionMsgJpaDao.findAuctionMsgByPublisher(publisher,pageable);
            return auctionMsgJpaDao.findAuctionMsgByStatusAndPublisher(status,publisher,pageable);
        }
    }

    @Override
    public AuctionMsg updateAuctionMsg(AuctionMsg auctionMsg) {
        AuctionMsg auctionMsg1 = auctionMsgJpaDao.findOne(auctionMsg.getAmsgId());
        auctionMsg1.setPrice(auctionMsg.getPrice());
        auctionMsg1.setContent(auctionMsg.getContent());
        auctionMsg1.setTitle(auctionMsg.getTitle());
        auctionMsgJpaDao.save(auctionMsg1);
        return auctionMsg1;
    }

    @Override
    public AuctionMsg createAuctionMsg(Integer userId, AuctionMsg auctionMsg, MultipartFile[] photos, HttpServletRequest request) {
        User user = userJpaDao.findOne(userId);
        auctionMsg.setPublisher(user);
        AuctionMsg auctionMsg1 = auctionMsgJpaDao.save(auctionMsg);
        String fileName ;
        ServletContext context = request.getServletContext();
        String relativePath ;
        String realPath = null;
        for(MultipartFile photo:photos){
            fileName = photo.getOriginalFilename();
            relativePath = "\\user\\"+userId+"\\photos\\"+fileName;
            System.out.println(relativePath);
            realPath = context.getRealPath(realPath);
            System.out.println(realPath);
            try
            {
                FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Photo photo1 = new Photo();
            photo1.setAuctionMsg(auctionMsg1);
            photo1.setPhotoPath(realPath);
            photoJpaDao.save(photo1);
        }
        return null;
    }
}
