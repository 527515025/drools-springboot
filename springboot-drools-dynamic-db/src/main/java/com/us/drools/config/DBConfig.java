package com.us.drools.config;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DBConfig {
	@Autowired
	private Environment env;

//	@Bean(name = "dataSource")
//	public DruidDataSource dataSource() {
//		final String url = Preconditions.checkNotNull(env.getProperty("ms.db.url"));
//		final String username = Preconditions.checkNotNull(env.getProperty("ms.db.username"));
//		final String password = env.getProperty("ms.db.password");
//		final int maxActive = Integer.parseInt(env.getProperty("ms.db.maxActive", "200"));
//		final String driver =env.getProperty("ms.db.driverClassName");
//		DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setMaxActive(maxActive);
//		return dataSource;
//
//	}

    @Bean(name="dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("ms.db.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("ms.db.url"));
        dataSource.setUser(env.getProperty("ms.db.username"));
        dataSource.setPassword(env.getProperty("ms.db.password"));
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxIdleTime(300);
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);

        return dataSource;
    }

}
