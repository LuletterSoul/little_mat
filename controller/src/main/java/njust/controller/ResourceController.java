package njust.controller;

import io.swagger.annotations.Api;
import njust.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "资源管理业务")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
