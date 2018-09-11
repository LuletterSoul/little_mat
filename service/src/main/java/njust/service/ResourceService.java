package njust.service;


import java.util.List;

import io.swagger.models.auth.In;
import njust.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;


public interface ResourceService
{
    Resource save(Resource resource);

    Resource deleteResource(Integer resourceId);

    Resource findResourceById(Integer resId);

    Page<Resource> findResources(Pageable pageable, Integer depId, Integer courseId, Integer type);

    List<Resource> findAll();

    Resource checkResource(Integer resId);

    List<Resource> waitCheckResource();
}
