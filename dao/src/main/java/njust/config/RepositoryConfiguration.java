package njust.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5
 * created in  21:02 2018/9/12.
 * @since little_mat
 */

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories("njust.dao")
@EnableTransactionManagement(proxyTargetClass =true)
public class RepositoryConfiguration {
}
