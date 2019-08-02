package cn.learncoding.java.ee.spring.jpa.multiple.datasources.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config.properties")
@EnableJpaRepositories(transactionManagerRef = "userTransactionManager", entityManagerFactoryRef = "userEntityManagerFactory", basePackages="cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.dao")
public class UserConfig {

    @Value("${jpa.url}")
    private String url;
    @Value("${jpa.username}")
    private String username;
    @Value("${jpa.password}")
    private String password;
    @Value("${jpa.driverClass}")
    private String driverClass;

    @Bean("userTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean("userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.model");
        return factoryBean;
    }

    @Bean("userDataSource")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

}
