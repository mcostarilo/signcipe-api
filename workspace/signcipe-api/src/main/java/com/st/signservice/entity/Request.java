package com.st.signservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.st.signservice.security.jwt.entity.Operator;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Person requestPerson;
	
	private Area sourceArea;

	private Area destArea;

	private String status; //VARCHAR(3) NOT NULL DEFAULT 'REQ' COMMENT 'REQ: Solicitado\nSIG:  Firmado\nREJ: Rechazado\n',

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Operator getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Operator createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Operator getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Operator modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "created_by")
	@CreatedBy
	private Operator createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@CreatedDate
	private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@LastModifiedDate
	private Date dateModified;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "modified_by")
	@LastModifiedBy
	private Operator modifiedBy;

	public Person getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(Person requestPerson) {
		this.requestPerson = requestPerson;
	}

	public Area getSourceArea() {
		return sourceArea;
	}

	public void setSourceArea(Area sourceArea) {
		this.sourceArea = sourceArea;
	}

	public Area getDestArea() {
		return destArea;
	}

	public void setDestArea(Area destArea) {
		this.destArea = destArea;
	}
}