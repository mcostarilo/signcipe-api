package com.st.signservice.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state_id")
	@JsonIgnoreProperties("cities")
	private State state;

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return this.state;
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
}