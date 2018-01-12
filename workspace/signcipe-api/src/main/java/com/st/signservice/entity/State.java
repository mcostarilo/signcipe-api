package com.st.signservice.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Table(name = "states",  uniqueConstraints={ @UniqueConstraint(columnNames = {"country_id", "name"}) })
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	@JsonIgnoreProperties("states")
	private Country country;

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "person_id")
//	@JsonIgnoreProperties("professionals")
//	private Person person;
	
	
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

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="state")
	@JsonIgnoreProperties("state")
	private List<City> cities;

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<City> getCities() {
		return this.cities;
	}
}