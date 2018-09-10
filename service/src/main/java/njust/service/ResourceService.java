package njust.service;


import java.util.List;

import njust.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ResourceService
{
    Resource save(Resource resource);

    Resource deleteResource(Integer resourceId);

    Resource findResouceById(Integer resId);

    Page<Resource> findResources(Pageable pageable, Integer depId, Integer courseId, Integer type);

    List<Resource> findAll();
}
