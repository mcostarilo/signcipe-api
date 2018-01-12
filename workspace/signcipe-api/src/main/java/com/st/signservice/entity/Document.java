package com.st.signservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.st.signservice.security.jwt.entity.Operator;

@Table(name="document",  uniqueConstraints={ @UniqueConstraint(columnNames = {"request_id", "name"}) })
@Entity
@NamedQuery(name="Document.findAllDocument", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private String pathName;

	private String status;
	
	private int fileTypeId;
	
	@Transient
	private String code;

	@Column(name="mime_type")
	private String mimeType;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "book_publisher", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "request_id")
	private Request request;

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
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public int getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}


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

	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getCode() {
		if (fileTypeId == 1) {
			code = "DIAG";
		} else if (fileTypeId == 2) {
			code = "ADJ";
		} else {
			code = "ORDER";
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
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
}