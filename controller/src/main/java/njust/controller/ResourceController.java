package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.Resource;
import njust.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "资源管理业务")
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation(value = "获取资源信息")
    @GetMapping(value = "/{resId}/info")
    public ResponseEntity<Resource> findResourceById(@PathVariable("resId") Integer resId){
        return new ResponseEntity<>(resourceService.findResourceById(resId),HttpStatus.OK);
    }

    @ApiOperation(value = "资源审核通过")
    @PatchMapping(value = "/{resId}/check")
    public ResponseEntity<Resource> checkResource(@PathVariable("resId") Integer resId){
        return null;
    }

    @ApiOperation(value = "修改资源")
    @PatchMapping(value = "/{resId}/update")
    public ResponseEntity<Resource> updateResource(@PathVariable("resId") Integer resId,
                                                   @RequestBody Resource resource){
        return null;
    }
    @ApiOperation(value = "删除上传资料")
    @DeleteMapping(value = "/{resId}")
    public ResponseEntity<Resource> updateResource(@PathVariable("resId") Integer resId){
        return null;
    }

    @ApiOperation(value = "获取所有待审核资料")
    @GetMapping(value = "/waitCheck")
    public ResponseEntity<List<Resource>> waitCheckResource(){
        return null;
    }
}
