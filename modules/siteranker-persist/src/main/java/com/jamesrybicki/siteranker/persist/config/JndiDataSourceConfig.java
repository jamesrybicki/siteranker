package com.jamesrybicki.siteranker.persist.config;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"production"})
public class JndiDataSourceConfig {

	@Value("${siteranker.datasource.jndiName}")
	private String dataSourceJndiName;

	@Value("${siteranker.datasource.dialect}")
	private String dataSourceDialect;

	@Bean
	public DataSource dataSource() throws NamingException {
		Context ctx = new InitialContext();
		return (DataSource) ctx.lookup(dataSourceJndiName);
	}

	/**
	 * Configure Hibernate programmatically (not using hibernate.cfg.xml).
	 *
	 * @return
	 * @see {@link http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch03.html#configuration-hibernatejdbc}
	 */
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", dataSourceDialect);
		properties.put("hibernate.connection.datasource", dataSourceJndiName);
		// properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

}
