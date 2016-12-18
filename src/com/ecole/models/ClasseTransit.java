package com.ecole.models;

public class ClasseTransit {


	private Classe classe;
	private Niveau niveau;
	private String code_classe;
	private String designation;
	private String code_niveau;

	
	public String getCode_classe() {
		return this.code_classe;
	}

	public void setCode_classe(String code_classe) {
		this.code_classe = code_classe;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCode_niveau() {
		return this.code_niveau;
	}

	public void setCode_niveau(String code_niveau) {
		this.code_niveau = code_niveau;
	}

	public Classe getClasse() {
		return classe;
	}
	public void setClasse( Classe classe) {
		this.classe = classe;
	}
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	

	

}
