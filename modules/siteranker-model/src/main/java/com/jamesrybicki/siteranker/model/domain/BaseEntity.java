package com.jamesrybicki.siteranker.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable {

	private UUID id;
	private long version;
	private Date dateCreated;
	private Date lastUpdated;

	public BaseEntity() {
		this.dateCreated = new Date();
	}

	public BaseEntity(UUID id) {
		this.id = id;
	}

	@Id
	@Column(name="id", columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Version
	@Column(name="version")
	public long getVersion() {
		return this.version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created", length=19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated", length=19)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@PreUpdate
	private void onUpdate() {
		this.lastUpdated = new Date();
	}

	@Transient
	public boolean isNew() {
		return (this.id == null);
	}

}