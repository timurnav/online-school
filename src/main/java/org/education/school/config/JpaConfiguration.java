package org.education.school.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("org.education.school.repository")
public class JpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("org.education.school.repository.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.DRIVER, "org.postgresql.Driver");
        // Generate schema using java classes, don't use in prod!
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");

        // show formatted sql with postgres dialect
        properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(AvailableSettings.SHOW_SQL, "true");
        properties.setProperty(AvailableSettings.FORMAT_SQL, "org.hibernate.dialect.PostgreSQLDialect");

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}
