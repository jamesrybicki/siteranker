package com.jamesrybicki.siteranker.persist.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile({"dev", "integration-test"})
@PropertySources(value = {@PropertySource("classpath:siteranker-config.properties")})
public class StandaloneDataSourceConfig {

	@Value("${siteranker.datasource.driverClassName}")
	private String dataSourceDriverClassName;

	@Value("${siteranker.datasource.dialect}")
	private String dataSourceDialect;

	@Value("${siteranker.datasource.username}")
	private String dataSourceUsername;

	@Value("${siteranker.datasource.password}")
	private String dataSourcePassword;

	@Value("${siteranker.datasource.url}")
	private String dataSourceUrl;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dataSourceDriverClassName);
		dataSource.setUrl(dataSourceUrl);
		dataSource.setUsername(dataSourceUsername);
		dataSource.setPassword(dataSourcePassword);
		return dataSource; 
	}           

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", dataSourceDialect);
		// properties.put("hibernate.hbm2ddl.auto", "create-drop");
		return properties;
	}

}
