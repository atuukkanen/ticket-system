package fi.dalitso.ticketsystem.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.Properties;

import static fi.dalitso.ticketsystem.configuration.EnvironmentTool.getEnvironmentVariable;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(
                environment.getRequiredProperty("database.driver"));
        dataSource.setUrl(getEnvironmentVariable("DATABASE_URL"));
        dataSource.setUsername(getEnvironmentVariable("DATABASE_USERNAME"));
        dataSource.setPassword(getEnvironmentVariable("DATABASE_PASSWORD"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emFactory =
                new LocalContainerEntityManagerFactoryBean();
        emFactory.setJpaDialect(new HibernateJpaDialect());
        emFactory.setDataSource(dataSource());
        emFactory.setPersistenceProviderClass(
                HibernatePersistenceProvider.class);
        emFactory.setPackagesToScan(environment
                .getRequiredProperty("entitymanager.packages.to.scan"));

        emFactory.setJpaProperties(generateJpaProperties());

        return emFactory;
    }

    private Properties generateJpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",
                environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",
                getEnvironmentVariable("JPA_SHOW_SQL", "false"));
        properties.put("hibernate.hbm2ddl.auto",
                environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.enable_lazy_load_no_trans",
                environment.getRequiredProperty(
                        "hibernate.enable_lazy_load_no_trans"));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

}
