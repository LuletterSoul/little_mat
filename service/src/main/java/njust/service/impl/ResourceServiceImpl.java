package njust.service.impl;

import njust.dao.*;
import njust.domain.*;
import njust.service.ResourceService;
import njust.util.DateUtil;
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
import java.util.Date;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceJpaDao resourceJpaDao;
    private UserJpaDao userJpaDao;
    private CompetitionJpaDao competitionJpaDao;
    private CourseJpaDao courseJpaDao;
    private AdministratorJpaDao administratorJpaDao;
    private DownloadRecordJpaDao downloadRecordJpaDao;

    @Autowired
    public void setDownloadRecordJpaDao(DownloadRecordJpaDao downloadRecordJpaDao) {
        this.downloadRecordJpaDao = downloadRecordJpaDao;
    }

    @Autowired
    public void setAdministratorJpaDao(AdministratorJpaDao administratorJpaDao) {
        this.administratorJpaDao = administratorJpaDao;
    }

    @Autowired
    public void setCourseJpaDao(CourseJpaDao courseJpaDao) {
        this.courseJpaDao = courseJpaDao;
    }

    @Autowired
    public void setCompetitionJpaDao(CompetitionJpaDao competitionJpaDao) {
        this.competitionJpaDao = competitionJpaDao;
    }

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
        File fileTemp = new File(resource.getPath());
        if(fileTemp.exists()){
            fileTemp.delete();
        }
        resourceJpaDao.delete(resource);
        return resource;
    }

    @Override
    public Resource findResourceById(Integer resId) {
        return resourceJpaDao.findOne(resId);
    }

    @Override
    public Page<Resource> findResources(Integer checkId,Integer status, Integer comId, Integer courseId, Integer type, Pageable pageable) {
        if(checkId!=null){
            return resourceJpaDao.findResourcesByChecker(administratorJpaDao.findOne(checkId),pageable);
        }
        else if(comId!=null){
            return resourceJpaDao.findResourcesByCompetition(competitionJpaDao.findOne(comId),pageable);
        }
        else if(courseId!=null){
            return resourceJpaDao.findResourcesByCourse(courseJpaDao.findOne(courseId),pageable);
        }
        else if(status!=null){
            return resourceJpaDao.findResourceByStatus(status,pageable);
        }
        return null;
    }



    @Override
    public Page<Resource> findAll(Pageable pageable) {
        return resourceJpaDao.findAll(pageable);
    }

    @Override
    public Resource checkResource(Integer resId,Integer adminId) {
        Resource resource = resourceJpaDao.findOne(resId);
        Administrator administrator = administratorJpaDao.findOne(adminId);
        resource.setChecker(administrator);
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
        resource1.setName(resource.getName());
        resourceJpaDao.save(resource1);
        return resource1;
    }

    @Override
    public Resource uploadResource(Integer userId,Integer comId,Integer courseId, MultipartFile multipartFile, HttpServletRequest request) {
        Resource resource = new Resource();
        User user = userJpaDao.findOne(userId);
        resource.setUploader(user);
        String fileName = multipartFile.getOriginalFilename();
        ServletContext context = request.getServletContext();
        String relativePath = "\\user\\"+userId+"\\"+fileName.substring(0,fileName.lastIndexOf("."))+"_"+DateUtil.DateToString(new Date(),"yyyy-MM-dd-HH:mm:ss") +fileName.substring(fileName.lastIndexOf("."));
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
        if(comId!=null){
            resource.setCompetition(competitionJpaDao.findOne(comId));
        }
        if(courseId!=null){
            resource.setCourse(courseJpaDao.findOne(courseId));
        }
        resource.setStatus(0);
        resource.setName(fileName);
        resource.setPath(realPath);
        resource.setSize(multipartFile.getSize());
        return resourceJpaDao.save(resource);
    }

    @Override
    public DownloadRecord downloadResource(Integer resId, Integer userId,HttpServletResponse response) {
        Resource resource = resourceJpaDao.findOne(resId);
        String realPath = resource.getPath();
        String fileName = resource.getName();
        FileInputStream in;
        ServletOutputStream out;
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","multipart/form-data");
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
        DownloadRecord downloadRecord = new DownloadRecord();
        downloadRecord.setDownloadDate(new Date());
        downloadRecord.setDownloader(userJpaDao.findOne(userId));
        downloadRecord.setResource(resource);
        return downloadRecordJpaDao.save(downloadRecord);
    }


}
