package com.ecole.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import com.ecole.utilities.Querys;

import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import com.ecole.models.Utilisateur;

import com.ecole.securite.UidGen;



public class GestionUtilisateur  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091107778228346369L;
	private Utilisateur utilisateur=new Utilisateur();
	 private LazyDataModel<Utilisateur> lazyModel;
     private Utilisateur selectedUtilisateur;
     private List<Utilisateur> utilisateurList;

     private Querys q;
     

	private 	MessageView m = new MessageView();
	
    @PostConstruct

    public void init() {
    	q = new Querys();
    	utilisateur=new Utilisateur();
    	generateListUtilisateur();
    	
    	

    }

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void addUtilisateur(){
		
		UidGen n = new UidGen();
		
				
		
		utilisateur.setId_utilisateur(n.getUid());
		
		 
		try{
			q.save(utilisateur);
			reset();
			m.success("Enregistrement terminé avec succès");
			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("utilisateurform:wizard");
			wizard.setStep("personal");

			generateListUtilisateur();
	    
		}
		catch(Exception e){
			
		e.printStackTrace();
			
			m.error("Erreur d'enregistrement !! Contactez l'administrateur");
		}
		
		
		
	}

     
    public String onFlowProcess(FlowEvent event) {

            return event.getNewStep();
        
    }

    
    public void generateListUtilisateur()
    {	
    	
    	utilisateurList = q.getAll("Utilisateur");
    	lazyModel = new UtilisateurLazyModel(utilisateurList);
    	
    }
    public LazyDataModel<Utilisateur> getLazyModel() {

        return lazyModel;
    }

     
    public Utilisateur getSelectedUtilisateur() {
		return selectedUtilisateur;
	}




	public void setSelectedUtilisateur(Utilisateur selectedUtilisateur) {
		this.selectedUtilisateur = selectedUtilisateur;
	}




	public void deleteUtilisateur() {
    	try{
    	
    		
    	q.delete(selectedUtilisateur);
    	generateListUtilisateur();
       	m.success("Suppression terminé avec succès");
    	}
	
	catch (PersistenceException e){
		e.printStackTrace();
	//				System.out.prin tln(e.getMessage() + "===="+e.getCause());
	
    			m.error("L'utilisateur est deja lié a un eleve!! Modifiez le parent de l'eleve concerné et reessayez");
    		}
    	catch(Exception e){
    		e.printStackTrace();
    		m.error("Erreur inconnue !! Contactez l'administrateur");
    	}

    }
    

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Utilisateur Selected", ((Utilisateur) event.getObject()).getId_utilisateur());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
	public List<Utilisateur> getUtilisateurList() {
		return utilisateurList;
	}


	public void setUtilisateurList(List<Utilisateur> utilisateurList) {
		this.utilisateurList = utilisateurList;
	}

	public void editUtilisateur(RowEditEvent event) {
		
		Utilisateur u = (Utilisateur)event.getObject();
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(u.getDate_naissance());
		
		
	
		q.executeQuery("update Utilisateur set cin='"+u.getCin()+"',nom='"+u.getNom()+"', prenom='"+u.getPrenom()+"', adresse='"+u.getAdresse()+"', date_naissance='"+date+"', fonction='"+
				u.getFonction()+"', role='"+u.getRole()+"', sexe='"+u.getSexe()+"', tel='"+u.getTel()+"' where id_utilisateur='"+u.getId_utilisateur()+"'");
				
    		m.success("Modification terminé avec succès");
    		
   
    }
     
    public void cancelEditUtilisateur(RowEditEvent event) {
    	m.info("Modification Annulée");
    }

    public void reset() {
    	
    	utilisateur.setCin(null);
    	utilisateur.setSexe(null);
    	utilisateur.setNom(null);
    	utilisateur.setPrenom(null);
    	utilisateur.setDate_naissance(null);
    	utilisateur.setAdresse(null);
    	utilisateur.setTel(null);
    	utilisateur.setRole(null);
    	utilisateur.setFonction(null);
    	
    }
    
}
