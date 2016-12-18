package com.ecole.models;

public class EleveTransit {
	
	
	private Eleve eleve;
	private Utilisateur parent;
	private Classe classe;
	
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Eleve getEleve() {
		return eleve;
	}
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	public Utilisateur getParent() {
		return parent;
	}
	public void setParent(Utilisateur parent) {
		this.parent = parent;
	}
	
	

}
