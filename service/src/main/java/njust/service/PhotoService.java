package njust.service;

import njust.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PhotoService {
    Photo save(Photo photo);
    Photo deletePhoto(Integer photoId);
    Photo findPhotoById(Integer photoId);
    Page<Photo> findAll(Pageable page);
}
