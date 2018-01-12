package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "rol",  uniqueConstraints={ @UniqueConstraint(columnNames = {"name"})})
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private String status;

	//bi-directional many-to-one association to OperatorRole
	@OneToMany(mappedBy="rol")
	@JsonIgnoreProperties("rol")
	@JsonIgnore
	private List<OperatorRole> operatorRoles;

	//bi-directional many-to-one association to ProfileRol
	@OneToMany(mappedBy="rol")
	@JsonIgnoreProperties("rol")
	private List<ProfileRole> profileRoles;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OperatorRole> getOperatorRoles() {
		return operatorRoles;
	}

	public void setOperatorRoles(List<OperatorRole> operatorRoles) {
		this.operatorRoles = operatorRoles;
	}

	public List<ProfileRole> getProfileRoles() {
		return profileRoles;
	}

	public void setProfileRoles(List<ProfileRole> profileRoles) {
		this.profileRoles = profileRoles;
	}

}