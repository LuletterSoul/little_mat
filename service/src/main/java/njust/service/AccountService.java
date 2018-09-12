package njust.service;

import njust.domain.Account;
import njust.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Account save(Account account);
    Account deleteAccount(Integer accountId);
    Account findAccountById(Integer accountId);
    Page<Account> findAll(Pageable pageable);
}
