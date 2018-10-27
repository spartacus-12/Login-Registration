package org.enset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.enset.dao.EtudiantRepository;
import org.enset.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantRestService {
	@Autowired 
	private EtudiantRepository etudiantrepository ;
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/saveEtudiants",method= RequestMethod.GET)
public Etudiant saveEtudiant(Etudiant e) {
	return etudiantrepository.save(e);
	
}
	@Secured(value= {"Role_ADMIN","ROLE_ETUDIANT"})
	@RequestMapping(value="/etudiants")
	public Page<Etudiant> listEtudiants(int page,int size){
		return etudiantrepository.findAll( PageRequest.of(page, size));
	}
	@RequestMapping(value="getlogeduser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpservlet){
		HttpSession httpsession = httpservlet.getSession();
		SecurityContext securityContext= (SecurityContext) httpsession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName() ;
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String,Object> params = new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);

		return params;
		
		
	}
}
