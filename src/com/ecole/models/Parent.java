package com.ecole.models;
// Generated 13 juil. 2016 14:42:34 by Hibernate Tools 4.0.0.Final

/**
 * Parent generated by hbm2java
 */
public class Parent implements java.io.Serializable {

	private String id_utilisateur;
	private String matricule_eleve;

	public Parent() {
	}

	public Parent(String id_utilisateur, String matricule_eleve) {
		this.id_utilisateur = id_utilisateur;
		this.matricule_eleve = matricule_eleve;
	}

	public String getId_utilisateur() {
		return this.id_utilisateur;
	}

	public void setId_utilisateur(String id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getMatricule_eleve() {
		return this.matricule_eleve;
	}

	public void setMatricule_eleve(String matricule_eleve) {
		this.matricule_eleve = matricule_eleve;
	}

}
