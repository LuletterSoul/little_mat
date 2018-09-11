package njust.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njust.domain.Resource;
import njust.service.ResourceService;


@Api(description = "资源管理业务")
@RestController
@RequestMapping(value = "/resource")
public class ResourceController
{

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService)
    {
        this.resourceService = resourceService;
    }

    @ApiOperation(value = "获取资源信息")
    @GetMapping(value = "/{resId}")
    public ResponseEntity<Resource> findResourceById(@PathVariable("resId") Integer resId)
    {
        return new ResponseEntity<>(resourceService.findResourceById(resId), HttpStatus.OK);
    }

    @ApiOperation(value = "获取资料")
    @GetMapping
    public ResponseEntity<Page<Resource>> findResourses(@PageableDefault(size = 20, sort = {
        "resId"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                        @ApiParam("资源所属的院系Id") @RequestParam(value = "depId", required = false, defaultValue = "") Integer depId,
                                                        @ApiParam("资源的课程Id") @RequestParam(value = "courseId", required = false, defaultValue = "") Integer courseId,
                                                        @ApiParam("资源所属的类型") @RequestParam(value = "type", required = false, defaultValue = "") Integer type)
    {
        return new ResponseEntity<>(resourceService.findResources(pageable, depId, courseId, type),
            HttpStatus.OK);
    }

    @ApiOperation(value = "资源审核通过")
    @PatchMapping(value = "/{resId}/check")
    public ResponseEntity<Resource> checkResource(@PathVariable("resId") Integer resId)
    {
        return new ResponseEntity<>(resourceService.checkResource(resId), HttpStatus.OK);
    }

    @ApiOperation(value = "修改资源")
    @PatchMapping(value = "/{resId}/update")
    public ResponseEntity<Resource> updateResource(@PathVariable("resId") Integer resId,
                                                   @RequestBody Resource resource)
    {
        return null;
    }

    @ApiOperation(value = "删除上传资料")
    @DeleteMapping(value = "/{resId}")
    public ResponseEntity<Resource> deleteResource(@PathVariable("resId") Integer resId)
    {
        return new ResponseEntity<>(resourceService.deleteResource(resId), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "获取所有待审核资料")
    @GetMapping(value = "/waitCheck")
    public ResponseEntity<List<Resource>> waitCheckResource()
    {
        return new ResponseEntity<>(resourceService.waitCheckResource(), HttpStatus.OK);
    }
}
