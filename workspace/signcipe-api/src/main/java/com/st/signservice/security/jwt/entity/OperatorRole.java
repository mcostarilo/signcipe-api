package com.st.signservice.security.jwt.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Table(name="operator_role",  uniqueConstraints={ @UniqueConstraint(columnNames = {"rol_id", "operator_id"})})
@Entity
@NamedQuery(name="OperatorRole.findAll", query="SELECT o FROM OperatorRole o")
public class OperatorRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@JsonIgnore
	private int createdBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;

	@JsonIgnore
	private Integer modifiedBy;

	private String status;

	//bi-directional many-to-one association to Operator
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "operator_id")
	@JsonIgnoreProperties("operatorRoles")
	private Operator operator;
	
	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "rol_id")
	@JsonIgnoreProperties("operatorRoles")
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
