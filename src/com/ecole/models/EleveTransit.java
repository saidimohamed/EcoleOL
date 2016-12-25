package com.ecole.models;

public class EleveTransit {
	
	
	private Eleve eleve=new Eleve();
	private Utilisateur parent=new Utilisateur();
	private Classe classe=new Classe();
	private Annee_Scolaire annee=new Annee_Scolaire();
	
	
	public Annee_Scolaire getAnnee() {
		return annee;
	}
	public void setAnnee(Annee_Scolaire annee) {
		this.annee = annee;
	}
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
