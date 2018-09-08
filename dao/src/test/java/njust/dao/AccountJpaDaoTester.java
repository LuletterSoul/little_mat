package njust.dao;

import njust.config.HibernateConfiguration;
import njust.domain.Account;
import njust.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class AccountJpaDaoTester {

    private AccountJpaDao accountJpaDao;
    private UserJpaDao userJpaDao;

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Autowired
    public void setAccountJpaDao(AccountJpaDao accountJpaDao) {
        this.accountJpaDao = accountJpaDao;
    }

    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setUsername("jason");
        accountJpaDao.save(account);

        User user = new User();
        user.setEmail("1726251215@qq.com");
        userJpaDao.save(user);

    }

}
