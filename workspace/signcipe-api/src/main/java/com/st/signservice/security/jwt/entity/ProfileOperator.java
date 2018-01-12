package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@Table(name = "profile_operator",  uniqueConstraints={ @UniqueConstraint(columnNames = {"profile_id", "operator_id"})})
@Entity
@NamedQuery(name="ProfileOperator.findAll", query="SELECT p FROM ProfileOperator p")
public class ProfileOperator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@JsonIgnore
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateModified;

	@JsonIgnore
	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date validUntil;

	//bi-directional many-to-one association to Operator
	@ManyToOne
	@JoinColumn(name = "operator_id")
	@JsonIgnoreProperties("profileOperators")
	private Operator operator;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "profile_id")
	@JsonIgnoreProperties("profileOperators")
	private Profile profile;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}