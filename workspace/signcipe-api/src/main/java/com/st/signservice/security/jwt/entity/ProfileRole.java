package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Table(name = "profile_role",  uniqueConstraints={ @UniqueConstraint(columnNames = {"profile_id", "rol_id"})})
@Entity
@NamedQuery(name="ProfileRole.findAll", query="SELECT p FROM ProfileRole p")
public class ProfileRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@JsonIgnore
	private Integer createdBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;

	@JsonIgnore
	private Integer modifiedBy;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "profile_id")
	@JsonIgnoreProperties("profileRoles")
	private Profile profile;

	@ManyToOne
	@JoinColumn(name = "rol_id")
	@JsonIgnoreProperties("profileRoles")
	private Rol rol;
	
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

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}