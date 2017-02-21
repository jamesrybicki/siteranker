package com.jamesrybicki.siteranker.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home page
 */
@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

 	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		LOG.trace("Home page");
		return "index";
	}
}
