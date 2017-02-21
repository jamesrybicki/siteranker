package com.jamesrybicki.siteranker.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
        name="site",
        catalog="siteranker_db"
)
public class Site extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -4050453908905018098L;
	
	private String url;
	private Date periodEndDate;
	private long totalVisits;

	public Site() {
		this(null);
	}
	
	public Site(UUID id) {
		super(id);
	}
	
    @Column(name="url", nullable=false)
    public String getUrl() {
            return this.url;
    }

    public void setUrl(String url) {
            this.url = url;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="period_end_date", nullable=false)
	public Date getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	@Column(name="total_visits", nullable=false)
	public long getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(long totalVisits) {
		this.totalVisits = totalVisits;
	}

}
