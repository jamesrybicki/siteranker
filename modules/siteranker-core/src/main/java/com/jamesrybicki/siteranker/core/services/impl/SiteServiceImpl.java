package com.jamesrybicki.siteranker.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.jamesrybicki.siteranker.core.services.SiteService;
import com.jamesrybicki.siteranker.model.domain.Period;
import com.jamesrybicki.siteranker.model.domain.Site;
import com.jamesrybicki.siteranker.persist.repo.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteRepository siteRepository;

	@Override
	public List<Site> list() {
		return (List<Site>) siteRepository.findAll();
	}

	@Override
	public Site get(UUID id) {
		return siteRepository.findOne(id);
	}
	
	@Override
	public Slice<Site> listByPeriod(Date periodEndDate, int page, int size) {
		return (Slice<Site>) siteRepository.findByPeriodEndDate(periodEndDate, new PageRequest(page, size));
	}
	
	@Override
	public List<Site> listTopFiveByPeriod(Date periodEndDate) {
		return (List<Site>) siteRepository.findTop5ByPeriodEndDate(periodEndDate);
	}

	@Override
	public List<Period> listAvailablePeriods() {
		List<Period> periodsWithData = new ArrayList<>();
		for (Date periodEndDate : (List<Date>) siteRepository.listDistinctPeriodEndDates()) {
			periodsWithData.add(new Period(periodEndDate));
		}
		return periodsWithData;
	}
	
	@Override
	public List<Site> listByUrl(String url) {
		return (List<Site>) siteRepository.findByUrl(url);
	}

}
