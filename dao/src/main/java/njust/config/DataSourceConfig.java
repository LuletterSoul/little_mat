package njust.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in 11:39 2018/8/7.
 * @since data-mining-platform
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig
{
    private String driverClassName;

    private String username;

    private String password;

    private String type;

    private String url;

    private boolean testOnBorrow;

    private boolean testWhileIdle;

    private int timeBetweenEvictionRunsMillis;

    private int initialPoolSize;

    private int maxPoolSize;

    private int minPoolSize;

    private int maxStatements;

    private int maxStatementsPerConnection;

    private int acquireIncrement;

    private int acquireRetryAttempts;

    private boolean autoCommitOnClose;

    private int acquireRetryDelay;

    private int maxIdleTimeExcessConnections;

    private int maxIdleTime;

    private int idleConnectionTestPeriod;

    private boolean breakAfterAcquireFailure;

}
