package njust.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import njust.domain.Photo;

import java.util.List;


public interface PhotoService
{
    Photo save(Photo photo);

    Photo deletePhoto(Integer photoId);

    Photo findPhotoById(Integer photoId);

    Page<Photo> findAll(Pageable page);

    List<String> findPhotoPaths(Integer amsgId);
}
