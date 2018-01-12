package com.st.signservice.security.jwt.service;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.security.jwt.entity.OperatorRole;
import com.st.signservice.security.jwt.entity.Rol;
import com.st.signservice.security.jwt.repository.OperatorRoleRepository;

@Service
public class OperatorRoleServiceImpl implements OperatorRoleService {

	@Resource 
	OperatorRoleRepository operatorRoleRepository;
	
	@Resource
	OperatorService operatorService;
	
	@Resource
	RolService rolService;
	
	@Resource
	private ProfileOperatorService profileOperatorService;
	
	@Override
	public List<OperatorRole> getOperatorRoles() {
		return operatorRoleRepository.findAll();
	}

	@Override
	public List<OperatorRole> findOperatorRoleByOperatorId(Integer operatorId) {
		return operatorRoleRepository.findOperatorRoleByOperatorId(operatorId);
	}

	@Override
	public OperatorRole createOperatorRol(OperatorRole operatorRole) {
		return operatorRoleRepository.save(operatorRole);
	}

	@Override
	public OperatorRole findOperatorRoleByOperator(String email, String rolName) {
		return operatorRoleRepository.findOperatorRoleByOperator(email, rolName);
	}
	
	public boolean hasRol(HttpServletRequest request, String rolName, Integer areaId) {
		boolean hasRole = false;
		Operator operator = operatorService.getOperatorByToken(request);
		
		if (operator.isSystemOwner()) {
			hasRole = true;
		} else {
			if (operator.getStatus() != null && !operator.getStatus().equalsIgnoreCase(ConstantConfig.OPERATOR_STATUS_BAJ)) {
				List<Rol> operatorRoles = null; //rolService.findByOperatorAndHealthCenterProfile(operator.getId(), areaId);
				Iterator<Rol> it = operatorRoles.iterator();
				while (it.hasNext() && !hasRole) {
					Rol rol = (Rol) it.next();
					if (rol.getName().equalsIgnoreCase(rolName)) {
						hasRole = true;
					}
				}
			}
		}
		
		return hasRole;
	}

}