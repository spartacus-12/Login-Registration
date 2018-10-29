package org.enset;



import org.enset.dao.TaskRepository;

import org.enset.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EtudiantApplication extends SpringBootServletInitializer{
	@Autowired
private TaskRepository taskRepository ;
	@Autowired
	private AccountService accountService ;

	public static void main(String[] args) {
	SpringApplication.run(EtudiantApplication.class, args);

	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
/*	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new User("user","123",true,null));
		accountService.saveRole(new Role("USER"));
		accountService.addRoleToUser("user", "USER");
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(null,t));
		});
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
		});
		
	*/}


