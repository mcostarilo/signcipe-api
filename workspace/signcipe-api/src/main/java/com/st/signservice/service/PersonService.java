package com.st.signservice.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.st.signservice.entity.Person;
import com.st.signservice.security.jwt.entity.Operator;

public interface PersonService {
	
	Person getPersonById(Integer personId) throws IOException;

	Person getPersonByEmail(String email) throws IOException;
	
	Person getPersonByDocNumberAndSex(String docNumber, String sex) throws IOException, ParseException;
	
	Person updatePerson(Person person, Operator updatedBy);
	
	Person createPerson(Person person, Operator createdBy) throws IOException, ParseException;

	Person createPersonWithoutDocument(Person person);

	String getBase64(String file) throws IOException;

	Page<Person> getFilteredPersons(String search, Pageable pageable);

	String getBase64B(String file) throws IOException;
	
}
