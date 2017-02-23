package com.jamesrybicki.siteranker.persist.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jamesrybicki.siteranker.model.domain.Site;
import com.jamesrybicki.siteranker.persist.repo.SiteRepository;

@Component
public class SiteLoader implements ApplicationListener<ContextRefreshedEvent> {
	
    private static Logger LOG = LoggerFactory.getLogger(SiteLoader.class);
    
    private static final DateFormat DATE_FORMAT_PERIOD_END_DATE = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private EventHolderBean eventHolderBean;

	@Autowired
    private SiteRepository siteRepository;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	
    	LOG.info("########################### Bootstrapping ######################");
 
        InputStream in = null;
        
        BufferedReader br = null;
        String fieldDelim = "\\|";
        try {
        	in = this.getClass().getResourceAsStream("/site.csv");
        	if (in == null) {
        		throw new Exception("Failed to locate site.csv");
        		
        	}
            br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                String[] siteData = line.split(fieldDelim);
                if (siteData.length != 3) {
                	LOG.error("Invalid input data:");
                	for (int i = 0; i < siteData.length; i++) {
                		LOG.error("siteData["+ i + "]: " + siteData[i]);
                	}
                	break;
                }
                Date periodEndDate = DATE_FORMAT_PERIOD_END_DATE.parse(siteData[0]);
                String url = siteData[1];
                long totalVisits = Long.parseLong(siteData[2]); 
                Site site = siteRepository.findByUrlAndPeriodEndDate(url, periodEndDate);
                if (site == null) {
                	site = new Site();
        			site.setPeriodEndDate(periodEndDate);
        			site.setUrl(url);
        			site.setTotalVisits(totalVisits);
                    siteRepository.save(site);
                    LOG.info("Loaded site: " + site.getId());
                } else {
                	LOG.info("Site data already exists, url: " + url + ", period end date: " + periodEndDate);
                }
            }
        } catch (Exception e) {
        	LOG.error(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignore) {}
            }
            if (in != null) {
            	try {
            		in.close();
            	} catch (Exception ignore) {}
            }
        }
        
        eventHolderBean.setEventFired(true);
 
    }
}
