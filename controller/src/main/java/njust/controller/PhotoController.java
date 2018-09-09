package njust.controller;

import io.swagger.annotations.Api;
import njust.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "图片业务")
public class PhotoController {
    private PhotoService photoService;

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }
}
