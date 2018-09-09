package njust.service;

import njust.domain.Resource;

import java.util.List;

public interface ResourceService {
    Resource save(Resource resource);
    Resource deleteResource(Integer resourceId);
    Resource findResourceById(Integer resourceId);
    List<Resource> findAll();
}
