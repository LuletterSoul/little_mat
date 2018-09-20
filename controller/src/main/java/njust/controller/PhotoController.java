package njust.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.service.PhotoService;


@Api(description = "图片业务")
@RestController
@RequestMapping(value = "/photos")
public class PhotoController
{
    private PhotoService photoService;

    @Autowired
    public void setPhotoService(PhotoService photoService)
    {
        this.photoService = photoService;
    }

    @ApiOperation(value = "获取拍卖的图片的链接")
    @GetMapping
    public ResponseEntity<List<String>> getPhotos(@RequestParam("amsgId") Integer amsgId)
    {
        return new ResponseEntity<>(photoService.findPhotoPaths(amsgId), HttpStatus.OK);
    }
}
