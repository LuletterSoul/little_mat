package njust.service;

import njust.domain.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);
    Account deleteAccount(Integer accountId);
    Account findAccountById(Integer accountId);
    List<Account> findAll();
}
