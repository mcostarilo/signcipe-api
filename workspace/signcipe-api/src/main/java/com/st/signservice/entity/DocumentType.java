package com.st.signservice.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="document_type",  uniqueConstraints={ @UniqueConstraint(columnNames = {"name"}) })
@Entity
@NamedQuery(name="DocumentType.findAll", query="SELECT d FROM DocumentType d")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocumentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;
	
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}