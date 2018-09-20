package njust.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import njust.service.util.DownloadUtils;
import njust.service.util.PathUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import njust.dao.*;
import njust.domain.Administrator;
import njust.domain.DownloadRecord;
import njust.domain.Resource;
import njust.domain.User;
import njust.service.ResourceService;
import njust.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

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
            if(fileTemp.delete()){
                System.out.println("Deleted Successfully!");
            }
        }
        Set<DownloadRecord> records = resource.getDownloadRecords();
        downloadRecordJpaDao.delete(records);
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
            if(status!=null){
                return resourceJpaDao.findResourcesByCompetitionAndStatus(competitionJpaDao.findOne(comId),status,pageable);
            }
            return resourceJpaDao.findResourcesByCompetition(competitionJpaDao.findOne(comId),pageable);
        }
        else if(courseId!=null){
            if(type!=null){
                if(status!=null){
                    return resourceJpaDao.findResourcesByTypeAndCourseAndStatus(type,courseJpaDao.findOne(courseId),status,pageable);
                }
                return  resourceJpaDao.findResourcesByTypeAndCourse(type,courseJpaDao.findOne(courseId),pageable);
            }else{
                if(status!=null){
                    resourceJpaDao.findResourcesByCourseAndStatus(courseJpaDao.findOne(courseId),status,pageable);
                }
            }
            return resourceJpaDao.findResourcesByCourse(courseJpaDao.findOne(courseId),pageable);
        }
        else if(status!=null){
            return resourceJpaDao.findResourceByStatus(status,pageable);
        }
        return resourceJpaDao.findAll(pageable);
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
        resource1.setType(resource.getType());
        resourceJpaDao.save(resource1);
        return resource1;
    }

    @Override
    public Resource createResource(Integer userId, Integer comId, Integer courseId, Integer type) {
        Resource resource = new Resource();
        User user = userJpaDao.findOne(userId);
        resource.setUploader(user);
        if(comId!=null){
            resource.setCompetition(competitionJpaDao.findOne(comId));
        }
        else if(courseId!=null){
            resource.setCourse(courseJpaDao.findOne(courseId));
        }
        if(type!=null){
            resource.setType(type);
        }
        resource.setStatus(0);
        return resourceJpaDao.save(resource);
    }

    @Override
    public Resource uploadResource(Integer resId, MultipartFile multipartFile) {
        Resource resource = resourceJpaDao.findOne(resId);
        String relativePath = "\\user\\"+resource.getUploader().getUserId();
        String fileName = multipartFile.getOriginalFilename();
        String timeableFilename = fileName.substring(0, fileName.lastIndexOf(".")) + "-" + DateUtil.DateToString(new Date(), "yyyy-MM-dd-HH-mm-ss") + fileName.substring(fileName.lastIndexOf("."));
        System.out.println(relativePath);
//        String realPath = context.getRealPath(relativePath);
        String realPath = PathUtils.getAbsolutePath(relativePath,timeableFilename);
        System.out.println(realPath);
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
    public DownloadRecord downloadResource(Integer resId, Integer userId,HttpServletResponse response) {
        Resource resource = resourceJpaDao.findOne(resId);
        String realPath = resource.getPath();
        String fileName = resource.getName();
//
//        System.out.println("fileName is "+fileName);
//        String encoder = EncoderUtil.getEncoding(fileName);
//        System.out.println("fileName's encode is "+encoder);
//
//        FileInputStream in;
//        ServletOutputStream out;
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type","application/octet-stream");
//        try {
////            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
//            fileName = new String(fileName.getBytes(encoder),"UTF-8");
//            System.out.println("fileName is "+fileName);
////            response.setHeader("Content-Disposition","attachment;filename="+ fileName);
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
////        response.setHeader("Content-Disposition","attachment;filename="+ fileName);
//        try {
//            in = new FileInputStream(realPath);
//
//            out = response.getOutputStream();
//            int len ;
//            byte b[] = new byte[1024];
//            while((len = in.read(b))!=-1){
//                out.write(b,0,len);
//            }
//            in.close();
//            out.close();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
        DownloadUtils.bigFileDownload(response, realPath, fileName);
        DownloadRecord downloadRecord = new DownloadRecord();
        downloadRecord.setDownloadDate(new Date());
        downloadRecord.setDownloader(userJpaDao.findOne(userId));
        downloadRecord.setResource(resource);
        return downloadRecordJpaDao.save(downloadRecord);
    }

    @Override
    public Page<Resource> findResourceByNameContainsAndStatus(String name,Integer status,Pageable pageable) {
        if(status ==null){
            return resourceJpaDao.findResourcesByNameContains(name,pageable);
        }
        return resourceJpaDao.findResourcesByNameContainsAndStatus(name,status,pageable);
    }
}
