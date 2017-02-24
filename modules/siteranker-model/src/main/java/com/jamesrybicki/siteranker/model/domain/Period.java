package com.jamesrybicki.siteranker.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable {
	
	private static final long serialVersionUID = -4459400124672156810L;
	
	private Date endDate;
	
	public Period() {
		this(null);
	}
	
	public Period(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
