package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户业务")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value="用户注册")
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改用户信息(待完成)")
    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> modifyUser(@PathVariable Integer userId,
                                           @RequestBody User user){
        return null;
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "获取用户个人信息")
    @GetMapping(value = "/{userId}/info")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.findUserById(userId),HttpStatus.OK);
    }

    @ApiOperation(value = "上传资料")
    @PostMapping(value = "/{userId}/resource")
    public ResponseEntity<Resource> uploadResource(@PathVariable("userId") Integer userId,
                                                   @RequestBody Resource resource){
        return null;
    }

    @ApiOperation(value = "下载资料")
    @PostMapping(value = "/{userId}/resource/{resId}")
    public ResponseEntity<Resource> downloadResource(@PathVariable("userId") Integer userId,
                                                     @PathVariable("resId")Integer resId){
        return null;
    }

    @ApiOperation(value = "获取个人的全部上传资料")
    @GetMapping(value ="/{userId}/uploads")
    public ResponseEntity<List<Resource>> getUploadedResources(@PathVariable("userId") Integer userId){
        return null;
    }

    @ApiOperation(value = "获取个人的全部待审核资料")
    @GetMapping(value ="/{userId}/waitCheck")
    public ResponseEntity<List<Resource>> getWaitCheckResources(@PathVariable("userId") Integer userId){
        return null;
    }

    @ApiOperation(value = "获取个人的全部已审核资料")
    @GetMapping(value ="/{userId}/checked")
    public ResponseEntity<List<Resource>> getCheckedResources(@PathVariable("userId") Integer userId){
        return null;
    }

    @ApiOperation(value = "获取个人的全部下载资料")
    @GetMapping(value = "/{userId}/downloads")
    public ResponseEntity<List<Resource>> getDownloadedResources(@PathVariable("userId") Integer userId){
        return null;
    }

    @ApiOperation(value = "获取个人的全部拍卖纪录")
    @GetMapping(value = "/{userId}/auction")
    public ResponseEntity<AuctionMsg> getAuctionMsgs(@PathVariable("userId") Integer userId){
        return null;
    }

    @ApiOperation(value = "发布拍卖信息")
    @PostMapping(value = "/{userId}/auction")
    public ResponseEntity<AuctionMsg> getAuctionMsgs(@PathVariable("userId") Integer userId,
                                                     @RequestBody AuctionMsg auctionMsg){
        return null;
    }

    @ApiOperation(value = "用户修改密码")
    @PatchMapping(value = "/{userId}/password")
    public ResponseEntity<User> updatePassword(@PathVariable("userId") Integer userId,
                                           @RequestBody User user){
        return null;
    }

    @ApiOperation(value = "用户登陆")
    @GetMapping
    public ResponseEntity<User> loginUser(@RequestBody User user){
        return null;
    }






}
