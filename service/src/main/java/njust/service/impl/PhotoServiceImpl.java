package njust.service.impl;

import njust.dao.PhotoJpaDao;
import njust.domain.Photo;
import njust.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoJpaDao photoJpaDao;

    @Autowired
    public void setPhotoJpaDao(PhotoJpaDao photoJpaDao) {
        this.photoJpaDao = photoJpaDao;
    }

    @Override
    public Photo save(Photo photo) {
        return photoJpaDao.save(photo);
    }

    @Override
    public Photo deletePhoto(Integer photoId) {
        Photo photo = photoJpaDao.findOne(photoId);
        photoJpaDao.delete(photo);
        return photo;
    }

    @Override
    public Photo findPhotoById(Integer photoId) {
        return photoJpaDao.findOne(photoId);
    }

    @Override
    public Page<Photo> findAll(Pageable pageable) {
        return photoJpaDao.findAll(pageable);
    }
}
