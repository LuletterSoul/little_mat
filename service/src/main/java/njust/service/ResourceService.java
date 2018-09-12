package njust.service;


import njust.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ResourceService
{
    Resource save(Resource resource);

    Resource deleteResource(Integer resourceId);

    Resource findResourceById(Integer resId);

    Page<Resource> findResources(Pageable pageable, Integer status, Integer depId, Integer courseId, Integer type);

    Page<Resource> findAll(Pageable pageable);

    Resource checkResource(Integer resId);

    Page<Resource> findResourceByStatus(Integer status,Pageable pageable);

    Resource updateResource(Resource resource);

    Resource uploadResource(Integer userId, Resource resource, MultipartFile multipartFile, HttpServletRequest request);

    Resource downloadResource(Integer resId,Integer userId,HttpServletResponse response);
}
