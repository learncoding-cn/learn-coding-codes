package cn.learncoding.java.ee.spring.jpa.auditing.config;

import cn.learncoding.java.ee.spring.jpa.auditing.AuditorAwareImpl;
import cn.learncoding.java.ee.spring.jpa.auditing.dao.CustomerDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config.properties")
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = CustomerDao.class)
public class Config {

    @Value("${jpa.url}")
    private String url;
    @Value("${jpa.username}")
    private String username;
    @Value("${jpa.password}")
    private String password;
    @Value("${jpa.driverClass}")
    private String driverClass;

    @Bean
    AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("cn.learncoding.java.ee.spring.jpa.auditing.model");
        return factoryBean;
    }


    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

}
