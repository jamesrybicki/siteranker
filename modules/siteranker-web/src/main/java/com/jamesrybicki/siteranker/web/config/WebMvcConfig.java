package com.jamesrybicki.siteranker.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.jamesrybicki.siteranker.web"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/content/**").addResourceLocations("/content/");
            registry.addResourceHandler("/site-ranker-app/**").addResourceLocations("/site-ranker-app/");
            registry.addResourceHandler("/vendor/**").addResourceLocations("/vendor/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
            localeChangeInterceptor.setParamName("lang");
            registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public LocaleResolver localeResolver() {
            CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
            cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
            return cookieLocaleResolver;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setPrefix("/WEB-INF/templates/");
            resolver.setSuffix(".html");
            // NB, selecting HTML5 as the template mode.
            resolver.setTemplateMode("HTML5");
            resolver.setCacheable(false);
            return resolver;
    }

    public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine engine = new org.thymeleaf.spring4.SpringTemplateEngine();
            engine.setTemplateResolver(templateResolver());
            return engine;
    }

    @Bean
    public ViewResolver viewResolver() {
            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
            viewResolver.setTemplateEngine(templateEngine());
            viewResolver.setOrder(1);
            viewResolver.setViewNames(new String[] { "*" });
            viewResolver.setCache(false);
            return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
            // if true, the key of the message will be displayed if the key is not
            // found, instead of throwing a NoSuchMessageException
            messageSource.setUseCodeAsDefaultMessage(true);
            messageSource.setDefaultEncoding("UTF-8");
            // # -1 : never reload, 0 always reload
            messageSource.setCacheSeconds(0);
            return messageSource;
    }


}
