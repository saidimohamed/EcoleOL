package com.ecole.models;
// Generated 22 ao�t 2016 14:46:36 by Hibernate Tools 3.4.0.CR1

/**
 * Matiere generated by hbm2java
 */
public class Matiere implements java.io.Serializable {

	private String code_matiere;
	private String designation;

	public Matiere() {
	}

	public Matiere(String code_matiere) {
		this.code_matiere = code_matiere;
	}

	public Matiere(String code_matiere, String designation) {
		this.code_matiere = code_matiere;
		this.designation = designation;
	}

	public String getCode_matiere() {
		return this.code_matiere;
	}

	public void setCode_matiere(String code_matiere) {
		this.code_matiere = code_matiere;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
