package com.st.signservice.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

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

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="country")
	@JsonIgnoreProperties("country")
	private List<State> states;

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<State> getStates() {
		return this.states;
	}
}