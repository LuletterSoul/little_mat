package njust.service.impl;

import njust.dao.AccountJpaDao;
import njust.domain.Account;
import njust.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountJpaDao accountJpaDao;

    @Autowired
    public void setAccountJpaDao(AccountJpaDao accountJpaDao) {
        this.accountJpaDao = accountJpaDao;
    }

    @Override
    public Account save(Account account) {
        return accountJpaDao.save(account);
    }

    @Override
    public Account deleteAccount(Integer accountId) {
        Account account = accountJpaDao.findOne(accountId);
        accountJpaDao.delete(account);
        return account;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountJpaDao.findOne(accountId);
    }

    @Override
    public List<Account> findAll() {
        return accountJpaDao.findAll();
    }
}
