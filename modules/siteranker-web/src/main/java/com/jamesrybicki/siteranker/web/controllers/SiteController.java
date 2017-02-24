package com.jamesrybicki.siteranker.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jamesrybicki.siteranker.core.services.SiteService;
import com.jamesrybicki.siteranker.model.domain.Period;
import com.jamesrybicki.siteranker.model.domain.Site;

@Controller
@RequestMapping("/rest/sites")
public class SiteController {
	
	    private static Logger LOG = LoggerFactory.getLogger(SiteController.class);

	    @Autowired
	    private SiteService siteService;

	    @RequestMapping(method = RequestMethod.GET)
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    public List<Site> list() {
	        List<Site> sites = siteService.list();
	        return sites;
	    }

	    @RequestMapping(method = RequestMethod.GET, params = { "periodEndDate", "page", "size" })
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    public List<Site> listByPeriodEndDate(
	    		@RequestParam("periodEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date periodEndDate, 
	    		@RequestParam("page") int page, 
	    		@RequestParam("size") int size) {
	        Slice<Site> sites = siteService.listByPeriod(periodEndDate, page, size);
	        return sites.getContent();
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
	    public ResponseEntity<Site> get(@PathVariable String id) {
	        Site site = siteService.get((UUID.fromString(id)));
	        return new ResponseEntity<Site>(site, HttpStatus.OK);
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/{siteUrl}/history")
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    public List<Site> history(@PathVariable String siteUrl) {
	        List<Site> sites = siteService.listByUrl(siteUrl);
	        return sites;
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/availablePeriods")
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    public List<Period> availablePeriods() {
	    	List<Period> availablePeriods = siteService.listAvailablePeriods();
	    	return availablePeriods;
	    }
	    
}
