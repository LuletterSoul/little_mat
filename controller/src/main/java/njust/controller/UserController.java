package njust.controller;

import io.swagger.annotations.Api;
import njust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "用户业务")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
