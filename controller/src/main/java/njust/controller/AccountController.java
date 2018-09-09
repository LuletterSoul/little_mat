package njust.controller;

import io.swagger.annotations.Api;
import njust.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "账号管理业务")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
