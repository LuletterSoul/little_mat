package njust.controller;



import io.swagger.annotations.ApiOperation;
import njust.domain.Account;
import njust.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import njust.service.AccountService;

import java.util.List;


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

    @ApiOperation(value = "找到全部的账户（测试通过）")
    @GetMapping
    public ResponseEntity<Page<Account>> findAllAccount(@PageableDefault(size = 20, sort = {
            "accountId"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        return new ResponseEntity<>(accountService.findAll(pageable),HttpStatus.OK);
    }
}
