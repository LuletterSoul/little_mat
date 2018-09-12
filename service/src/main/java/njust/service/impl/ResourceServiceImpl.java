package njust.service.impl;

import njust.dao.ResourceJpaDao;
import njust.dao.UserJpaDao;
import njust.domain.Resource;
import njust.domain.User;
import njust.service.ResourceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceJpaDao resourceJpaDao;
    private UserJpaDao userJpaDao;

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Autowired
    public void setResourceJpaDao(ResourceJpaDao resourceJpaDao) {
        this.resourceJpaDao = resourceJpaDao;
    }

    @Override
    public Resource save(Resource resource) {
        return resourceJpaDao.save(resource);
    }

    @Override
    public Resource deleteResource(Integer resourceId) {
        Resource resource = resourceJpaDao.findOne(resourceId);
        resourceJpaDao.delete(resource);
        return resource;
    }

    @Override
    public Resource findResourceById(Integer resId) {
        return resourceJpaDao.findOne(resId);
    }

    @Override
    public Page<Resource> findResources(Pageable pageable, Integer status, Integer depId, Integer courseId, Integer type) {
        return null;
    }

    @Override
    public Page<Resource> findAll(Pageable pageable) {
        return resourceJpaDao.findAll(pageable);
    }

    @Override
    public Resource checkResource(Integer resId) {
        Resource resource = resourceJpaDao.findOne(resId);
        resource.setStatus(1);
        resourceJpaDao.save(resource);
        return resource;
    }

    @Override
    public Page<Resource> findResourceByStatus(Integer status,Pageable pageable) {
        return resourceJpaDao.findResourceByStatus(status,pageable);
    }

    @Override
    public Resource updateResource(Resource resource) {
        Resource resource1 = resourceJpaDao.findOne(resource.getResId());
        resource1.setType(resource.getType());
        resourceJpaDao.save(resource1);
        return resource1;
    }

    @Override
    public Resource uploadResource(Integer userId, Resource resource, MultipartFile multipartFile, HttpServletRequest request) {
        User user = userJpaDao.findOne(userId);
        String fileName = multipartFile.getOriginalFilename();
        ServletContext context = request.getServletContext();
        String relativePath = "\\user\\"+userId+"\\"+fileName;
        System.out.println(relativePath);
        String realPath = context.getRealPath(relativePath);
        System.out.println(realPath);
        try
        {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        resource.setStatus(0);
        resource.setName(fileName);
        resource.setPath(realPath);
        resource.setSize(multipartFile.getSize());
        return resourceJpaDao.save(resource);
    }

    @Override
    public Resource downloadResource(Integer resId, Integer userId,HttpServletResponse response) {
        Resource resource = resourceJpaDao.findOne(resId);
        String realPath = resource.getPath();
        String fileName = resource.getName();
        FileInputStream in;
        ServletOutputStream out;
        response.setHeader("Content-Type","application/x-msdownload");
        try {
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            in = new FileInputStream(realPath);
            out = response.getOutputStream();
            int len ;
            byte b[] = new byte[1024];
            while((len = in.read(b))!=-1 && in!=null){
                out.write(b,0,len);
            }
            in.close();
            out.close();
        }catch (Throwable e){
            e.printStackTrace();
        }
        System.out.println(realPath+""+fileName+" "+"下载成功");
        return resource;
    }


}
