package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.st.signservice.entity.Organization;
import java.util.List;

@Table(name = "profiles",  uniqueConstraints={ @UniqueConstraint(columnNames = {"healthcenter_id", "code"})})
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String description;

	private String name;
	
	private String status;

	//bi-directional many-to-one association to Healthcenter 
	@ManyToOne
	@JoinColumn(name = "healthcenter_id")
	@JsonIgnoreProperties("profile")
	private Organization organization;
	
	//bi-directional many-to-one association to Profileoperator
	@JsonIgnore
	@OneToMany(mappedBy="profile")
	@JsonIgnoreProperties("profile")
	private List<ProfileOperator> profileOperators;

	//bi-directional many-to-one association to Profilerol
	@OneToMany(mappedBy = "profile")
	@JsonIgnoreProperties("profile")
	private List<ProfileRole> profileRoles;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProfileOperator> getProfileOperators() {
		return profileOperators;
	}

	public void setProfileOperators(List<ProfileOperator> profileOperators) {
		this.profileOperators = profileOperators;
	}

	public List<ProfileRole> getProfileRoles() {
		return profileRoles;
	}

	public void setProfileRoles(List<ProfileRole> profileRoles) {
		this.profileRoles = profileRoles;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}