package njust.service;

import njust.domain.Photo;

import java.util.List;

public interface PhotoService {
    Photo save(Photo photo);
    Photo deletePhoto(Integer photoId);
    Photo findPhotoById(Integer photoId);
    List<Photo> findAll();
}
