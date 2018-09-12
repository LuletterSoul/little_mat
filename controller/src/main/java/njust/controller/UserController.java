package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njust.domain.AuctionMsg;
import njust.domain.Resource;
import njust.domain.User;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
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

    @ApiOperation(value = "修改用户信息")
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @ApiParam("学院id") @RequestParam(value = "depId", required = false, defaultValue = "") Integer depId){

        return  new ResponseEntity<>(userService.updateUser(user,depId),HttpStatus.OK);
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



    @ApiOperation(value = "获取个人的全部上传资料")
    @GetMapping(value ="/{userId}/uploads")
    public ResponseEntity<Page<Resource>> getUploadedResources(@PageableDefault(size = 20, sort = {"amsgId"}, direction = Sort.Direction.DESC)Pageable pageable,
                                                               @PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getUploadedResources(userId,pageable),HttpStatus.OK);
    }

/*    @ApiOperation(value = "获取个人的全部待审核资料")
    @GetMapping(value ="/{userId}/waitCheck")
    public ResponseEntity<List<Resource>> getWaitCheckResources(@PathVariable("userId") Integer userId){
        return null;
    }*/

//    @ApiOperation(value = "获取个人的全部已审核资料")
//    @GetMapping
//    public ResponseEntity<Page<Resource>> getResources(@PageableDefault(size = 20, sort = {"amsgId"}, direction = Sort.Direction.DESC)Pageable pageable,
//                                                       @Param("userId") Integer userId,@Param("status")Integer status){
//        return null;
//    }

    @ApiOperation(value = "获取个人的全部下载资料")
    @GetMapping(value = "/{userId}/downloads")
    public ResponseEntity<Page<Resource>> getDownloadedResources(@PageableDefault(size = 20, sort = {"amsgId"}, direction = Sort.Direction.DESC)Pageable pageable,
                                                                 @PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getDownloadedResources(userId,pageable),HttpStatus.OK);
    }

    @ApiOperation(value = "获取个人的全部拍卖纪录")
    @GetMapping(value = "/{userId}/auctions")
    public ResponseEntity<Page<AuctionMsg>> getAuctionMsgs(@PageableDefault(size = 20, sort = {"amsgId"}, direction = Sort.Direction.DESC)Pageable pageable,
                                                           @PathVariable("userId") Integer userId)
    {
        return new ResponseEntity<>(userService.getAuctionMsgs(userId,pageable),HttpStatus.OK);
    }


    @ApiOperation(value = "用户修改密码")
    @PatchMapping(value = "/password")
    public ResponseEntity<User> updateUserPassword(@RequestParam(value = "userId") Integer userId,
                                                   @RequestParam(value = "password") String password){
        return new ResponseEntity<>(userService.updateUserPassword(userId,password),HttpStatus.OK);
    }

    @ApiOperation(value = "用户登陆")
    @GetMapping(value = "/login")
    public ResponseEntity<User> loginUser(@RequestParam(value = "username")String username,
                                          @RequestParam(value = "password")String password)
    {
        return new ResponseEntity<>(userService.loginUser(username,password),HttpStatus.OK);
    }
}
