package njust.service.impl;

import njust.dao.ResourceJpaDao;
import njust.domain.Resource;
import njust.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Resource findResourceById(Integer resId) {
        return resourceJpaDao.findOne(resId);
    }

    @Override
    public Page<Resource> findResources(Pageable pageable, Integer depId, Integer courseId, Integer type) {
        return null;
    }

    @Override
    public List<Resource> findAll() {
        return resourceJpaDao.findAll();
    }

    @Override
    public Resource checkResource(Integer resId) {
        Resource resource = resourceJpaDao.findOne(resId);
        resource.setStatus(1);
        resourceJpaDao.save(resource);
        return resource;
    }

    @Override
    public List<Resource> waitCheckResource() {
        List<Resource> list = new ArrayList<>();
        List<Resource> resources = resourceJpaDao.findAll();
        for(Resource resource:resources){
            if(resource.getStatus()==0){
                list.add(resource);
            }
        }
        return list;
    }
}
