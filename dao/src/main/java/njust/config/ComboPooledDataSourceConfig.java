package njust.config;


import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Component
public class ComboPooledDataSourceConfig

{
    @Autowired
    @Bean(name = "dataSource")
    public DataSource loadDataSource(DataSourceConfig config)
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setJdbcUrl(config.getUrl());
        try
        {
            dataSource.setDriverClass(config.getDriverClassName());
        }
        catch (PropertyVetoException e)
        {
            e.printStackTrace();
        }
        dataSource.setInitialPoolSize(config.getInitialPoolSize());
        dataSource.setMaxPoolSize(config.getMaxPoolSize());
        dataSource.setMinPoolSize(config.getMinPoolSize());
        dataSource.setMaxStatements(config.getMaxStatements());
        dataSource.setMaxStatementsPerConnection(
                config.getMaxStatementsPerConnection());
        dataSource.setAcquireIncrement(
                config.getAcquireIncrement());
        dataSource.setAcquireRetryAttempts(
            config.getAcquireRetryAttempts());
        dataSource.setAutoCommitOnClose(
            config.isAutoCommitOnClose());
        dataSource.setAcquireRetryDelay(
            config.getAcquireRetryDelay());
        dataSource.setMaxIdleTime(config.getMaxIdleTime());
        dataSource.setBreakAfterAcquireFailure(config.isBreakAfterAcquireFailure());
        dataSource.setIdleConnectionTestPeriod(config.getIdleConnectionTestPeriod());
        return dataSource;
    }
}
