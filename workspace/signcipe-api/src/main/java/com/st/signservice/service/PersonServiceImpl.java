package com.st.signservice.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.st.signservice.controller.DocumentControllerAux;
import com.st.signservice.entity.Config;
import com.st.signservice.entity.Person;
import com.st.signservice.remote.dto.PersonRemoteDto;
import com.st.signservice.remote.service.PersonRemoteService;
import com.st.signservice.repository.PersonRepository;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.service.exception.BadRequestException;
import com.st.signservice.storage.FileSystemStorageService;
import java.io.IOException;

@Service
public class PersonServiceImpl implements PersonService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
    DocumentControllerAux documentControllerAux;
	
	@Resource
	FileSystemStorageService fileSystemStorageService;
	
	@Resource 
	PersonRepository personRepository;
	
	@Resource
	PersonRemoteService personRemoteService;
	
	@Resource
	ConfigService configService;
	
	@Override
	public Person getPersonByEmail(String email) throws IOException {
		
		Person person = personRepository.findPersonByEmail(email);
		
		if (person != null) {
			String pathFile = documentControllerAux.getPathFile("pathImage");
			String pathName = person.getPhoto(pathFile);
			String photoBase64 = getBase64(pathFile + pathName); 
			person.setPhotoBase64(photoBase64);
		}
		
		return person;
	}

	@Override
	public Person getPersonById(Integer personId) throws IOException {
		
		Person person = personRepository.findOne(personId);

//		List<Professional> professionals = professionalService.getProfessionalsByPersonId(person.getId());
//		person.setProfessionals(professionals);

		if (person != null) {
			String pathFile = documentControllerAux.getPathFile("pathImage");
			String pathName = person.getPhoto(pathFile);
			String photoBase64 = getBase64(pathFile + pathName); 
			person.setPhotoBase64(photoBase64);
		}
		
		return person;
	}
	
	@Override
	public Person getPersonByDocNumberAndSex(String docNumber, String sex) throws IOException, ParseException {
		Person person = personRepository.findPersonByDocNumberAndSex(docNumber, sex);
		
		if (person != null) {
			String pathFile = documentControllerAux.getPathFile("pathImage");
			String pathName = person.getPhoto(pathFile);
			String photoBase64 = getBase64(pathFile + pathName); 
			person.setPhotoBase64(photoBase64);
		} else {
			PersonRemoteDto personRemoteDto = personRemoteService.getPersonRemoteByDocNumberAndSex(docNumber, sex);
			if (personRemoteDto != null) {
				Person newPerson = new Person();
				newPerson.setFirstName(personRemoteDto.getName());
				newPerson.setLastName(personRemoteDto.getLastName());
				newPerson.setSex(personRemoteDto.getSex());
//				DocumentType documentType = documentTypeService.findByCode(personRemoteDto.getDocType());
//				newPerson.setDocumentType(documentType);
				String remoteDocNumber = personRemoteDto.getDocNumber();
				newPerson.setDocNumber(remoteDocNumber);
				
				newPerson.setStreet(personRemoteDto.getStreet());

				if(personRemoteDto.getHouseNumber() != null) {
					newPerson.setAddressNumber(personRemoteDto.getHouseNumber());
				}
				
				if(newPerson.getAddressNumber()== null){
					newPerson.setAddressNumber("");
				}
				
			    if(personRemoteDto.getFloor() != null && !"{}".equals(personRemoteDto.getFloor())) {
			    	newPerson.setAddressNumber( newPerson.getAddressNumber() + " - Piso: " + personRemoteDto.getFloor());
			    }

			    if(personRemoteDto.getNumber() != null) {
			    	newPerson.setAddressNumber( newPerson.getAddressNumber() + " " + personRemoteDto.getNumber() );
			    }

			    if(personRemoteDto.getBlock() != null) {
			    	newPerson.setAddressNumber( newPerson.getAddressNumber() + " " + personRemoteDto.getBlock());
			    }
			    
				newPerson.setPostalCode(personRemoteDto.getPostalCode());
				
				String remoteBirthDay = personRemoteDto.getBirthDate();
				DateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
				Date birthDay = dF.parse(remoteBirthDay);
				newPerson.setBirthDay(birthDay);
				if("0".equals(personRemoteDto.getNeighborhood())) {
					personRemoteDto.setNeighborhood("");
				}
				if("".equals(personRemoteDto.getNeighborhood()) && personRemoteDto.getCity() != null) {
					newPerson.setNeighborhood(personRemoteDto.getNeighborhood() + "Ciudad de: " + personRemoteDto.getCity());
				}
				if(!"".equals(personRemoteDto.getNeighborhood()) && personRemoteDto.getCity() != null) {
					newPerson.setNeighborhood(personRemoteDto.getNeighborhood() + " - Ciudad de: " + personRemoteDto.getCity());
				}
				
				newPerson.setEmailCipe(personRemoteDto.getEmail());
				newPerson.setCsn(personRemoteDto.getCsn());

				String pathFile = documentControllerAux.getPathFile("pathImage");
				UUID uuid = UUID.randomUUID();
				String uuidFileName = uuid.toString();

				String base64Picture = null;
				if (personRemoteDto.getBase64Picture() != null) {
					
//					String pathFile = documentControllerAux.getPathFile();
//					UUID uuid = UUID.randomUUID();
//					String uuidFileName = uuid.toString();
					
					if (personRemoteDto.getBase64Picture().toLowerCase().contains("data:image/jpg;base64,".toLowerCase())){
						base64Picture = personRemoteDto.getBase64Picture().split(",")[1];	
					}
					else {
						base64Picture = personRemoteDto.getBase64Picture();
					}
					newPerson.setPhoto(uuidFileName);
					newPerson.setPhotoBase64(base64Picture);
					// guardar foto a disco
					Files.write(Paths.get(pathFile+uuidFileName), base64Picture.getBytes());
				} else {
					newPerson.getPhoto();
				}
				
//				newPerson.setPhoto(uuidFileName);
//				newPerson.setPhotoBase64(base64Picture);
				personRepository.save(newPerson);
				person = newPerson;
				
				// guardar foto a disco
//				Files.write(Paths.get(pathFile+uuidFileName), base64Picture.getBytes());
			}
		}

		if (person == null) {
			throw new BadRequestException("Persona No Encontrada");
		}

		return person;
	}

	@Override
	public Person updatePerson(Person person, Operator updatedBy) {

			logger.info( this.getClass().getName() + " Update person:" + person.getFirstName() + " " + person.getLastName());
		
			Person oldPerson = personRepository.findOne(person.getId());
			
		if (oldPerson != null && oldPerson.getDocNumber() != null
				&& oldPerson.getDocNumber().equalsIgnoreCase(person.getDocNumber()) && oldPerson.getSex() != null
				&& oldPerson.getSex().equalsIgnoreCase(person.getSex())) {
				oldPerson = personRepository.save(person);
			}
			else {
				throw new BadRequestException("No existe la persona");
			}
			return oldPerson;
	}

	@Override
	public Person createPerson(Person person, Operator createdBy) throws IOException, ParseException {

			logger.info( this.getClass().getName() + " Create person:" + person.getFirstName() + " " + person.getLastName());
		
			Person oldPerson = null;
			if (person != null && person.getSex() != null && person.getDocNumber() != null) {
				//
				// Buscamos en Base Local con DocNumber y Sex
				//
				oldPerson = personRepository.findPersonByDocNumberAndSex(person.getSex(), person.getDocNumber());
			}
			
			if(oldPerson == null) {
				//
				// Buscamos en Cipe-Renaper con DocNumber y Sex
				//
				oldPerson = getPersonByDocNumberAndSex(person.getDocNumber(), person.getSex());
				if(oldPerson != null) {
					oldPerson = personRepository.save(person);
				}
				else {
					oldPerson = personRepository.save(person);
				}
			}
			else {
				throw new BadRequestException("La Persona ya Existe");
			}
			return oldPerson;
	}

	public Person createPersonWithoutDocument(Person person) {
		return person;
	}

	@Override
	public String getBase64(String file) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(file));
		return new String(encoded, StandardCharsets.US_ASCII);
	}
	
	@Override
	public Page<Person> getFilteredPersons(String search, Pageable pageable) {
		search = search.toUpperCase();
		return personRepository.findFilteredPersons(search, pageable);
	}
	
	@Override
	public String getBase64B(String file) throws IOException {
		Config config = configService.findByCode("pathImage");
		file = config.getValue() + file;
		byte[] encoded = Files.readAllBytes(Paths.get(file));
		
		return new String(encoded, StandardCharsets.US_ASCII);
	}	
}