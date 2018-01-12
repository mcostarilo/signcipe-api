package com.st.signservice.security.jwt.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.st.signservice.entity.Person;
import com.st.signservice.push.service.exception.OperatorExistsException;
import com.st.signservice.service.ConfigService;
import com.st.signservice.service.PersonService;
import com.st.signservice.config.ConstantConfig;
import com.st.signservice.controller.DocumentControllerAux;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.security.jwt.entity.Profile;
import com.st.signservice.security.jwt.repository.OperatorRepository;
import com.st.signservice.security.jwt.token.util.TokenExtractor;
import io.jsonwebtoken.Claims;
import com.st.signservice.entity.CipePerson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.RestTemplate;

@Service
public class OperatorServiceImpl implements OperatorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource 
	OperatorRepository operatorRepository;
	
	@Resource 
	PersonService personService;

	@Resource 
	ProfileService profileService;
	
	@Resource
	ConfigService configService;
	
	@Resource
	DocumentControllerAux documentControllerAux;
	
	@Override
	public Page<Operator> getOperators(String search, Pageable pageable, Integer serviceProviderId) throws IOException {
		Page<Operator> operators = operatorRepository.findAll(search, pageable); //, serviceProviderId);
		if (operators != null && operators.hasContent() ) {
			for (Operator oper : operators) {
				if(oper.getPerson().getPhotoBase64() == null) {
					String pathFile = documentControllerAux.getPathFile("pathImage");
					String pathName = oper.getPerson().getPhoto(pathFile);
					String photoBase64 = personService.getBase64(pathFile + pathName); 
					oper.getPerson().setPhotoBase64(photoBase64);
				}
			}
		}
		return operators;
	}

	@Override
	public Operator findOperatorById(Integer operatorId) {
		Operator operator = null;
		operator = operatorRepository.findOne(operatorId);
		if (operator != null) {
//			List<ServiceProviderHasOperator> serviceProviderHasOperator = serviceProviderHasOperatorService.getServiceProviderHasOperatorByOperatorId(operatorId);
//			operator.setServiceProviderHasOperator(serviceProviderHasOperator);
			operator.setOperatorRoles(null);
			operator.setProfileOperators(null);
		}
		return operator;
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Operator createOperator(Operator operator, Operator createdBy) throws OperatorExistsException {
		Operator result = null;
		logger.info( this.getClass().getName() + " Create operator:" + operator.getUserName() );
		
		if (operator.getPerson() != null) {
			Person person;
			try {
				person = personService.getPersonById(operator.getPerson().getId());
				String userName = person.getDocNumber() + person.getSex();
				operator.setUserName(userName);
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
		
		List<Operator> repeated = operatorRepository.findByUserName(operator.getUserName());
		if (repeated != null && !repeated.isEmpty()) {
			throw new OperatorExistsException();
		}
		
		try {
			result = operator;
		} catch(Exception e1) {
			logger.error(e1.toString());
		}
		return result;		
	}

	@Override
	public Operator updateOperator(Operator operator, Operator updatedBy) {
		try {
			logger.info( this.getClass().getName() + " Update operator:" + operator.getUserName() );
		
			Operator operatorToSave = operatorRepository.findOne(operator.getId());
			
			if (operatorToSave == null) {
				operatorToSave = operator;
				operatorToSave.setCreatedBy(updatedBy.getId());
				operatorToSave.setDateCreated(new Date());
			}
			
			operatorToSave.setEmail(operator.getEmail());
			
			operatorToSave.setModifiedBy(updatedBy.getId());
			operatorToSave.setDateModified(new Date());
			if (operatorToSave.getStatus() == null || !operatorToSave.getStatus().equalsIgnoreCase(operator.getStatus())) {
				operatorToSave.setStatus(operator.getStatus());
			}
			

			if (operatorToSave.getStatus() == null || !operatorToSave.getStatus().equalsIgnoreCase(operator.getStatus())) {
				operatorToSave.setStatus(operator.getStatus());
			}
			
			return operatorRepository.save(operatorToSave);			
		} catch(Exception e1) {
			logger.error(e1.toString());
		}
		return null;		
	}

	@Override
	public Operator deleteOperator(Integer operatorId, Operator updatedBy) {
		try {
			logger.info( this.getClass().getName() + " Delete operator:" + operatorId );
		
			Operator operator = operatorRepository.findOne(operatorId);

			return operator;
		} catch(Exception e1) {
			logger.error(e1.toString());
		}
		return null;		
	}
	
	@Override
	public List<Operator> findByUserName(String userName) {
		return operatorRepository.findByUserName(userName);
	}

	
	@Override
	public Operator getOperatorFromCipeWS(String userName, String serialNumber) throws ServletException, IOException, ParseException
	{
		String url = configService.findByCode("cipeWSUrl").getValue();
		CipePerson cipePerson = new RestTemplate().getForObject(url + "/CipeServiceWS/cipe/chip/" + serialNumber, CipePerson.class);
		
		Person person = new Person();

		person.setDateCreated(new Date());
		person.setDocNumber(cipePerson.getDocNumber());
		
		//user.setDocType("1".equals(cipePerson.getDocType()) ? "DNI" : "Otro");
//		person.setEmail(email);
		person.setFirstName(cipePerson.getName());
		person.setLastName(cipePerson.getLastName());
		person.setSex("0".equals(cipePerson.getSex()) ? "F" : "M");

		Operator operator = new Operator();
		operator.setPassword(serialNumber);
		operator.setStatus(ConstantConfig.OPERATOR_STATUS_ACT);
		operator.setUserName(userName);
//		operator.setEmail(email);
		String pictureName = UUID.randomUUID().toString();
		
		try	{
			persistFile(cipePerson.getBase64Picture(), pictureName, "pathImage");
		}
		catch (Exception e)	{
			throw new ServletException(e);
		}
		person.setPhoto(pictureName);
		
//		person = personService.updatePerson(person, operator);
		person = personService.createPerson(person, operator);
		
		operator.setPerson(person);
		
		operatorRepository.save(operator);

		return operator;
	}
	
	private File persistFile(String fileData, String originalName, String path) throws FileNotFoundException, IOException
	{
		byte[] data = Base64.getDecoder().decode(fileData);
		String pathFile = documentControllerAux.getPathFile(path);
		OutputStream stream = new FileOutputStream(pathFile + originalName);
		try
		{
			stream.write(data);
		}
		finally
		{
			stream.close();
		}
		return new File(pathFile + originalName);
	}

	@Override
	public Operator getOperatorByToken(HttpServletRequest request) {
		Operator result = null;
		String header = request.getHeader("X-Authorization");
		String token = header.substring(7); 
		Claims claims = TokenExtractor.extractClaims(token);
		if (claims != null) {
			Map<String, Object> scopes = (Map<String, Object>) claims.get("scopes");
			if (scopes != null) {
				Integer operatorId = (Integer) scopes.get("id");
				if (operatorId != null) {
					result = findOperatorById(operatorId);
				}
			}
		}
		return result;
	}

	@Override
	public List<Operator> findSystemOwners() {
		return operatorRepository.findSystemOwners();
	}

	@Override
	public boolean validateCsnFromCipeWS(String userName, String serialNumber) throws ServletException {
		boolean result = false;
		String url = configService.findByCode("cipeWSUrl").getValue();
		CipePerson cipePerson = new RestTemplate().getForObject(url + "/CipeServiceWS/cipe/chip/" + serialNumber, CipePerson.class);
		if (cipePerson != null) {
			String sex = "0".equals(cipePerson.getSex()) ? "F" : "M";
			String cipeUserName = cipePerson.getDocNumber() + sex;
			if (userName.equalsIgnoreCase(cipeUserName)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public List<Operator> getOperatorByPersonId(Integer personId) {
		return operatorRepository.findByPersonId(personId);
	}
}