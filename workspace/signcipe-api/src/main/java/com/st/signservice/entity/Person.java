package com.st.signservice.entity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.st.signservice.config.ConstantConfig;
import com.st.signservice.security.jwt.entity.Operator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

//@Table(name="Person",  uniqueConstraints={ @UniqueConstraint(columnNames = {"sex","document_type","docNumber"})})
@Table(name="Person")
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ignoreUnknown"})
public class Person implements Serializable {

	@JsonIgnore
	@Transient
	private Person oldPersona;
	 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String firstName;
	
	private String lastName;
	
	private String sex;

	@Transient
	private String age;
	
	@Transient
	private String photoBase64;

	@Transient
	private Integer yearsOld;

	private String documentType;
	
	private String docNumber;

	private String street;
	
	private String addressNumber;

	private String postalCode;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = ConstantConfig.SYSTEM_TIMEZONE)
	private Date birthDay;

	private String email;

	private String neighborhood;

	private String phone;
	
	private String emailCipe;
	
	private String csn;
	
	//bi-directional many-to-one association to Civilstatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private CivilStatus civilstatus;
	
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

//	@Column(length=4000)
//	@Column(columnDefinition="TEXT")
	
	private String photo;

	//bi-directional many-to-one association to Operator
	@JsonIgnore
	@OneToMany(mappedBy = "person")
	@JsonIgnoreProperties("person")
	private List<Operator> operators;
	
	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "city_id")
	@JsonIgnoreProperties("person")
	//@JsonIgnore
	private City city;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressNumber() {
		return this.addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}


	public Date getBirthDay() {
//		DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
//		return this.birthDay != null?sourceFormat.format(this.birthDay):sourceFormat.format(new Date());
		return this.birthDay;
	}
	
	public Date getBirthDayDate() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setBirthDay(String birthDay) throws ParseException {
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sourceFormat.parse(birthDay);
		this.birthDay = date;
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

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		if( this.photo == null) {
			if("M".equalsIgnoreCase(sex)) {
				this.photo = "silhouette-man-grey.png";
			}
			else {
				this.photo = "silhouette-woman-grey.png";
			}
		}
		
		return this.photo;
	}
	
	public String getPhoto(String path) {
		boolean exists = true;
		if (this.photo != null) {
			File file = new File(path + this.photo);
			if (!file.exists()) {
				exists = false;
			}
		}
		if( this.photo == null || !exists) {
			if("M".equalsIgnoreCase(sex)) {
				this.photo = "silhouette-man-grey.png";
			}
			else {
				this.photo = "silhouette-woman-grey.png";
			}
		}
		
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<Operator> getOperators() {
		return this.operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	public CivilStatus getCivilstatus() {
		return this.civilstatus;
	}

	public void setCivilstatus(CivilStatus civilstatus) {
		this.civilstatus = civilstatus;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setDateModified(String dateModified) throws ParseException {
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sourceFormat.parse(dateModified);
		this.dateModified = date;
	}

	public void setDateCreated(String dateCreated) throws ParseException {
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sourceFormat.parse(dateCreated);
		this.dateCreated = date;
	}

	public String getAge() {
		DateTime dn = new DateTime(this.getBirthDayDate());
		Years years = Years.yearsBetween(dn, DateTime.now());
		if( years.getYears() > 1) {
			//return String.valueOf(years.getYears()) + " a�os";
			return String.valueOf(years.getYears()) + " a" + ((char) 241) +"os";
			
		}
		Months months = Months.monthsBetween(dn, DateTime.now());		
		if( months.getMonths() > 1) {
			return String.valueOf(years.getYears()) + " meses";
		}
		Days days = Days.daysBetween(dn, DateTime.now());
		if( days.getDays() > 1) {
			return String.valueOf(days.getDays()) + " dias";
		}

		return "0 dias";
	}

	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.getDefault());
	    cal.setTime(date);
	    return cal;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public String getPhotoBase64() throws IOException {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public String getEmailCipe() {
		return emailCipe;
	}

	public void setEmailCipe(String emailCipe) {
		this.emailCipe = emailCipe;
	}

	public String getCsn() {
		return csn;
	}

	public void setCsn(String csn) {
		this.csn = csn;
	}

	public Integer getYearsOld() {
		if (this.getBirthDayDate() == null) return 0;
		DateTime dn = new DateTime(this.getBirthDayDate());
		Years years = Years.yearsBetween(dn, DateTime.now());
		setYearsOld(years.getYears());
		return years.getYears();
	}

	public void setYearsOld(Integer yearsOld) {
		this.yearsOld = yearsOld;
	}

	@PostLoad
	private void storeOldPersona(){
		oldPersona = new Person();
	}
     
 	@PreUpdate
    public void onPreUpdate() throws IOException {
    }

	public Person getOldPersona() {
		return oldPersona;
	}

	public void setOldPersona(Person oldPersona) {
		this.oldPersona = oldPersona;
	}

	public Operator getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Operator createdBy) {
		this.createdBy = createdBy;
	}

	public Operator getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Operator modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setAge(String age) {
		this.age = age;
	}
}