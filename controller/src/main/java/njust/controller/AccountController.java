package njust.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import njust.service.AccountService;


@Api(description = "账号管理业务")
@RestController
@RequestMapping(value = "/accounts")
public class AccountController
{
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }
}
