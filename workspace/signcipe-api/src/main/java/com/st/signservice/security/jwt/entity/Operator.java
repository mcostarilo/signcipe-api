package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.st.signservice.entity.Person;
import com.st.signservice.security.jwt.entity.OperatorRole;
import com.st.signservice.security.jwt.entity.Rol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateModified;

	@JsonIgnore
	private Integer modifiedBy;

	@JsonIgnore
	private Integer createdBy;
	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonIgnore
	private String password;

	private String status;

	private String userName;

	private String email;
	
	private Boolean systemOwner;
	
	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnoreProperties("operators")
	private Person person;
	
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to Operatorrole
	@JsonIgnore
	@OneToMany(mappedBy = "operator")	
	@JsonIgnoreProperties("operator")
	private List<OperatorRole> operatorRoles;

	//bi-directional many-to-one association to Profileoperator
	@JsonIgnore
	@OneToMany(mappedBy = "operator")
	@JsonIgnoreProperties("operator")
	private List<ProfileOperator> profileOperators;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OperatorRole> getOperatorRoles() {
		return this.operatorRoles;
	}

	public void setOperatorRoles(List<OperatorRole> operatorRoles) {
		this.operatorRoles = operatorRoles;
	}

	public List<ProfileOperator> getProfileOperators() {
		return profileOperators;
	}

	public void setProfileOperators(List<ProfileOperator> profileOperators) {
		this.profileOperators = profileOperators;
	}

	public String getEmail() {
		return this.email ;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSystemOwner() {
		return systemOwner;
	}

	public boolean isSystemOwner() {
		return (systemOwner != null)?systemOwner:false;
	}
	
	public void setSystemOwner(Boolean systemOwner) {
		this.systemOwner = systemOwner;
	}

	public List<Rol> getRoles() {
		List<Rol> roles = new ArrayList<Rol>();
		List<Profile> profiles = new ArrayList<Profile>();
		List<ProfileRole> profileRoles = new ArrayList<ProfileRole>();

		/* Buscamos por Roles del Operador */
		if(operatorRoles != null) {
			for (OperatorRole operatorRole :  operatorRoles) {
				roles.add(operatorRole.getRol());
			}
		}

		/* Buscamos por Roles del Perfil del Operador */
		if(profileOperators != null) {
			for (ProfileOperator profileOperator :  profileOperators) {
				profiles.add(profileOperator.getProfile());
			}
		}
		if(profiles != null) {
			for (Profile profile :  profiles) {
				profileRoles = profile.getProfileRoles() ;
			}
		}

		if(profileRoles != null) {
			for (ProfileRole profileRole :  profileRoles) {
				roles.add(profileRole.getRol());
			}
		}
		return roles;
	}
}