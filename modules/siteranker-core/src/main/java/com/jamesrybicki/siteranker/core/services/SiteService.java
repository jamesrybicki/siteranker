package com.jamesrybicki.siteranker.core.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Slice;

import com.jamesrybicki.siteranker.model.domain.Site;

public interface SiteService {

	public List<Site> list();

	public Site get(UUID id);
	
	public List<Site> listTopFiveByPeriod(Date periodEndDate);
	
	public Slice<Site> listByPeriod(Date periodEndDate, int page, int size);

	public List<Date> listWeeksWithData();
	
}