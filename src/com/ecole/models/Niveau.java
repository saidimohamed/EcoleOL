package com.ecole.models;
// Generated 22 ao�t 2016 14:28:45 by Hibernate Tools 3.4.0.CR1

/**
 * Niveau generated by hbm2java
 */
public class Niveau implements java.io.Serializable {

	private String code_niveau;
	private String designation;

	public Niveau() {
	}

	public Niveau(String code_niveau) {
		this.code_niveau = code_niveau;
	}

	public Niveau(String code_niveau, String designation) {
		this.code_niveau = code_niveau;
		this.designation = designation;
	}

	public String getCode_niveau() {
		return this.code_niveau;
	}

	public void setCode_niveau(String code_niveau) {
		this.code_niveau = code_niveau;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}