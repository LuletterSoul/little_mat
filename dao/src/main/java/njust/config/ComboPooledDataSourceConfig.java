package njust.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Configuration
@PropertySource(value = "classpath:datasource.properties")
public class ComboPooledDataSourceConfig implements EnvironmentAware

{
    private Environment environment;

    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    @Bean(name = "dataSource")
    public DataSource loadDataSource()
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(environment.getProperty("ec.db.username"));
        dataSource.setPassword(environment.getProperty("ec.db.password"));
        dataSource.setJdbcUrl(environment.getProperty("ec.db.url"));
        try
        {
            dataSource.setDriverClass(environment.getProperty("ec.db.driver"));
        }
        catch (PropertyVetoException e)
        {
            e.printStackTrace();
        }
        dataSource.setInitialPoolSize(
            Integer.valueOf(environment.getProperty("ec.db.initialPoolSize")));
        dataSource.setMaxPoolSize(Integer.valueOf(environment.getProperty("ec.db.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.valueOf(environment.getProperty("ec.db.minPoolSize")));
        dataSource.setMaxStatements(
            Integer.valueOf(environment.getProperty("ec.db.maxStatements")));
        dataSource.setMaxStatementsPerConnection(
            Integer.valueOf(environment.getProperty("ec.db.maxStatementsPerConnection")));
        dataSource.setAcquireIncrement(
            Integer.valueOf(environment.getProperty("ec.db.acquireIncrement")));
        dataSource.setAcquireRetryAttempts(
            Integer.valueOf(environment.getProperty("ec.db.acquireRetryAttempts")));
        dataSource.setAutoCommitOnClose(
            Boolean.valueOf(environment.getProperty("ec.db.autoCommitOnClose")));
        dataSource.setAcquireRetryDelay(
            Integer.valueOf(environment.getProperty("ec.db.acquireRetryDelay")));
        dataSource.setMaxIdleTime(Integer.valueOf(environment.getProperty("ec.db.maxIdleTime")));
        dataSource.setIdleConnectionTestPeriod(
            Integer.valueOf(environment.getProperty("ec.db.idleConnectionTestPeriod")));
        dataSource.setBreakAfterAcquireFailure(
            Boolean.valueOf(environment.getProperty("ec.db.breakAfterAcquireFailure")));
        return dataSource;
    }
}
