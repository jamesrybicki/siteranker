package com.jamesrybicki.siteranker.model.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class ModelConfig {
	
    @Autowired
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
            LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
            factory.setDataSource(dataSource);
            factory.setPackagesToScan("com.jamesrybicki.siteranker.model.domain");
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            // vendorAdapter.setGenerateDdl(true);
            vendorAdapter.setShowSql(true);
            factory.setJpaVendorAdapter(vendorAdapter);
            factory.setJpaProperties(hibernateProperties);
            factory.afterPropertiesSet();
            return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
            return entityManagerFactory.createEntityManager();
    }
	
}
