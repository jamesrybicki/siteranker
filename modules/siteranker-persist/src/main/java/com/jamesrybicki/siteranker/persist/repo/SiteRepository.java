package com.jamesrybicki.siteranker.persist.repo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.TemporalType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.jamesrybicki.siteranker.model.domain.Site;

public interface SiteRepository extends CrudRepository<Site, UUID> {

	public List<Site> findTop5ByPeriodEndDate(@Temporal(TemporalType.DATE) Date periodEndDate);

	public Slice<Site> findByPeriodEndDate(@Temporal(TemporalType.DATE) Date periodEndDate, Pageable pageable);

	@Query("SELECT DISTINCT periodEndDate FROM Site")
	public List<Date> findWeeksWithData();
	
	public Site findByUrl(String url);

	public Site findByUrlAndPeriodEndDate(String url, @Temporal(TemporalType.DATE) Date periodEndDate);
	
}
