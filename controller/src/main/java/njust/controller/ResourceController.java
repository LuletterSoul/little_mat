package njust.controller;


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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
                                                        @ApiParam("资源的审核者Id") @RequestParam(value = "checkerId",required = false,defaultValue = "") Integer checkerId,
                                                        @ApiParam("资源当前的审核状态") @RequestParam(value = "status",required = false,defaultValue = "") Integer status,
                                                        @ApiParam("资源所属的院系Id") @RequestParam(value = "depId", required = false, defaultValue = "") Integer depId,
                                                        @ApiParam("资源的课程Id") @RequestParam(value = "courseId", required = false, defaultValue = "") Integer courseId,
                                                        @ApiParam("资源所属的类型") @RequestParam(value = "type", required = false, defaultValue = "") Integer type)
    {
        return new ResponseEntity<>(resourceService.findResources(pageable,status , depId, courseId, type),
            HttpStatus.OK);
    }

    @ApiOperation(value = "资源审核通过")
    @PatchMapping(value = "/{resId}/check")
    public ResponseEntity<Resource> checkResource(@PathVariable("resId") Integer resId)
    {
        return new ResponseEntity<>(resourceService.checkResource(resId), HttpStatus.OK);
    }

    @ApiOperation(value = "修改资源")
    @PatchMapping
    public ResponseEntity<Resource> updateResource(@RequestBody Resource resource)
    {
        return new ResponseEntity<>(resourceService.updateResource(resource),HttpStatus.OK);
    }

    @ApiOperation(value = "删除上传资料")
    @DeleteMapping(value = "/{resId}")
    public ResponseEntity<Resource> deleteResource(@PathVariable("resId") Integer resId)
    {
        return new ResponseEntity<>(resourceService.deleteResource(resId), HttpStatus.NO_CONTENT);
    }

//    @ApiOperation(value = "获取所有待审核资料")
//    @GetMapping(value = "/status")
//    public ResponseEntity<Page<Resource>> findResourceByStatus(@PageableDefault(size = 20, sort = {
//            "resId"}, direction = Sort.Direction.DESC) Pageable pageable,
//                                                               @ApiParam("审核状态") @RequestParam(value = "status", required = false, defaultValue = "") Integer status)
//    {
//        return new ResponseEntity<>(resourceService.findResourceByStatus(status,pageable), HttpStatus.OK);
//    }

    @ApiOperation(value = "上传资料")
    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<Resource> uploadResource(@PathVariable("userId") Integer userId,
                                                   @RequestBody Resource resource,
                                                   @RequestParam("file")MultipartFile file, HttpServletRequest request){
        return new ResponseEntity<>(resourceService.uploadResource(userId,resource,file,request),HttpStatus.OK);
    }

    @ApiOperation(value = "下载资料")
    @PostMapping(value = "/{resId}/user/{userId}")
    public ResponseEntity<Resource> downloadResource(@PathVariable("resId")Integer resId,
                                                     @PathVariable("userId") Integer userId,HttpServletResponse response){
        return new ResponseEntity<>(resourceService.downloadResource(resId,userId,response),HttpStatus.OK);
    }
}
