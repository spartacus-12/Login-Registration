package org.enset;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.enset.dao.EtudiantRepository;
import org.enset.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EtudiantApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(EtudiantApplication.class, args);
		EtudiantRepository etudiantrepository= ctx.getBean(EtudiantRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		etudiantrepository.save(new Etudiant("zmerli","ghassen",df.parse("1992-02-12")));
		etudiantrepository.save(new Etudiant("ahmed","khlil",df.parse("1992-02-12")));
		etudiantrepository.save(new Etudiant("mohamed","ahmed",df.parse("1992-02-12")));
		etudiantrepository.save(new Etudiant("sara","sara",df.parse("1992-02-12")));
		etudiantrepository.save(new Etudiant("hamza","hamza",df.parse("1992-02-12")));
		List<Etudiant> etd = etudiantrepository.findAll();
		etd.forEach(e->System.out.println(e.getNom()));

	}
}
