package njust.service.impl;

import njust.dao.ResourceJpaDao;
import njust.domain.Resource;
import njust.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceJpaDao resourceJpaDao;

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
    public Resource findResourceById(Integer resourceId) {
        return resourceJpaDao.findOne(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        return resourceJpaDao.findAll();
    }
}
