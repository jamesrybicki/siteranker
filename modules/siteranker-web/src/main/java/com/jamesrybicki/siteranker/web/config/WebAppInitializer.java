package com.jamesrybicki.siteranker.web.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.jamesrybicki.siteranker.core.config.CoreConfig;
import com.jamesrybicki.siteranker.model.config.ModelConfig;
import com.jamesrybicki.siteranker.persist.config.PersistenceConfig;
import com.jamesrybicki.siteranker.persist.config.JndiDataSourceConfig;
import com.jamesrybicki.siteranker.persist.config.StandaloneDataSourceConfig;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//{!begin addToRootContext}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
			AppConfig.class,
			ModelConfig.class,
			StandaloneDataSourceConfig.class,
			JndiDataSourceConfig.class,
			PersistenceConfig.class,
			CoreConfig.class
		};
	}
	//{!end addToRootContext}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter};
	}
}

