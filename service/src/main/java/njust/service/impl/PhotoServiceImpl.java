package njust.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import njust.dao.AuctionMsgJpaDao;
import njust.dao.PhotoJpaDao;
import njust.domain.Photo;
import njust.service.PhotoService;


@Service
public class PhotoServiceImpl implements PhotoService
{

    private PhotoJpaDao photoJpaDao;

    private AuctionMsgJpaDao auctionMsgJpaDao;

    @Autowired
    public void setAuctionMsgJpaDao(AuctionMsgJpaDao auctionMsgJpaDao)
    {
        this.auctionMsgJpaDao = auctionMsgJpaDao;
    }

    @Autowired
    public void setPhotoJpaDao(PhotoJpaDao photoJpaDao)
    {
        this.photoJpaDao = photoJpaDao;
    }

    @Override
    public Photo save(Photo photo)
    {
        return photoJpaDao.save(photo);
    }

    @Override
    public Photo deletePhoto(Integer photoId)
    {
        Photo photo = photoJpaDao.findOne(photoId);
        photoJpaDao.delete(photo);
        return photo;
    }

    @Override
    public Photo findPhotoById(Integer photoId)
    {
        return photoJpaDao.findOne(photoId);
    }

    @Override
    public Page<Photo> findAll(Pageable pageable)
    {
        return photoJpaDao.findAll(pageable);
    }

    @Override
    public List<String> findPhotoPaths(Integer amsgId)
    {
        return photoJpaDao.findPhotoByAuctionMsg(auctionMsgJpaDao.findOne(amsgId)).stream().map(
            Photo::getRelativePath).collect(Collectors.toList());
    }
}
