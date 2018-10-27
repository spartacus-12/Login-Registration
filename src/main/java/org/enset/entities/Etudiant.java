package org.enset.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Etudiant implements Serializable {
@Id
@GeneratedValue
private Long idEtudiant ;
private String nom ;
private String prenom ;
private Date datenaissance ;
public Etudiant(String nom, String prenom, Date datenaissance) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.datenaissance = datenaissance;
}
public Etudiant() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getIdEtudiant() {
	return idEtudiant;
}
public void setIdEtudiant(Long idEtudiant) {
	this.idEtudiant = idEtudiant;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public Date getDatenaissance() {
	return datenaissance;
}
public void setDatenaissance(Date datenaissance) {
	this.datenaissance = datenaissance;
}

}
