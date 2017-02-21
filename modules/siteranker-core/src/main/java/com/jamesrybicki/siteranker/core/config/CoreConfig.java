package com.jamesrybicki.siteranker.core.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.jamesrybicki.siteranker.core.exceptions.LoggingAsyncUncaughtExceptionHandler;

@Configuration
@EnableAsync
@ComponentScan(basePackages = {"com.jamesrybicki.siteranker.core"})
public class CoreConfig extends AsyncConfigurerSupport {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(32);
		executor.setQueueCapacity(Integer.MAX_VALUE);
		executor.setThreadNamePrefix("SiteRankerAsyncExecutor-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new LoggingAsyncUncaughtExceptionHandler();
	}

}
