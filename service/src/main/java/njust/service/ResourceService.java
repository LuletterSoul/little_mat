package njust.service;


import njust.domain.DownloadRecord;
import njust.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface ResourceService
{
    Resource save(Resource resource);

    Resource deleteResource(Integer resourceId);

    Resource findResourceById(Integer resId);

    Page<Resource> findResources(Integer checkId, Integer status, Integer comId, Integer courseId, Integer type,Pageable pageable);

    Page<Resource> findAll(Pageable pageable);

    Resource checkResource(Integer resId,Integer adminId);

    Page<Resource> findResourceByStatus(Integer status,Pageable pageable);

    Resource updateResource(Resource resource);

    Resource uploadResource(Integer resId, MultipartFile multipartFile);

    Resource createResource(Integer userId, Integer comId, Integer courseId, Integer type);

    DownloadRecord downloadResource(Integer resId, Integer userId, HttpServletResponse response);

    Page<Resource> findResourceByNameContainsAndStatus(String name, Integer status,Pageable pageable);

}
