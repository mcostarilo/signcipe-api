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
import com.st.signservice.entity.City;
import com.st.signservice.security.jwt.entity.Operator;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AreaHasPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "head_person_id")
	private Person headPerson;
	
	private Boolean isChief;
	
	private Date validFrom;
	
	private Date validTo;
	
	private String status;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getHeadPerson() {
		return headPerson;
	}

	public void setHeadPerson(Person headPerson) {
		this.headPerson = headPerson;
	}

	public Boolean getIsChief() {
		return isChief;
	}

	public void setIsChief(Boolean isChief) {
		this.isChief = isChief;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}