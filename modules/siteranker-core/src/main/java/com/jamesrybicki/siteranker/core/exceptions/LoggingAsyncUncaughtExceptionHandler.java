package com.jamesrybicki.siteranker.core.exceptions;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class LoggingAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(LoggingAsyncUncaughtExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		LOG.error(
				"Uncaught exception while performing method " + method.getName()
				+ " of class " + method.getClass().getName()
				+ " with params " + params,
				ex
				);
	}

}

