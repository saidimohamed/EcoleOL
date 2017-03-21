package com.ecole.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import com.ecole.utilities.HibernateUtil;
import com.ecole.utilities.Querys;

import org.hibernate.Session;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;


import com.ecole.models.Niveau;
import com.ecole.securite.UidGen;



public class GestionNiveau  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091107778255556369L;
	private Niveau niveau=new Niveau();
     private Niveau selectedNiveau;
     private List<Niveau> niveauList;

     private Querys q;
     private Session session;
     

	private 	MessageView m = new MessageView();
	
    @PostConstruct

    public void init() {
    	q = new Querys();
    	buildSession();
    	
    	niveau=new Niveau();
    	
    	generateListNiveau();
    	

    }

    public void buildSession(){
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	q.setSession(session);
    	
    }

	

	public Niveau getNiveau() {
		return niveau;
	}




	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}




	public void addNiveau(){
		
		UidGen n = new UidGen();
		
				
		
		niveau.setCode_niveau(n.getUid());
		
		 
		try{
		//uh.persist(niveau);
			q.save(niveau);

			reset();
		m.success("Enregistrement terminé avec succès");
			    Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("niveauform:wizard");
	    wizard.setStep("personal");


	    generateListNiveau();
	    
		}
		catch(Exception e){
			
			
		e.printStackTrace();
			
			m.error("Erreur d'enregistrement !! Contactez l'administrateur");
		}
		
		
		
	}

     
    public String onFlowProcess(FlowEvent event) {

            return event.getNewStep();
        
    }

    
    public void generateListNiveau()
    {	

    	
    	//niveauList = q.getAll("Niveau");
    	niveauList = q.find(Niveau.class, "", "", "");
    	
    }


     
    public Niveau getSelectedNiveau() {
		return selectedNiveau;
	}




	public void setSelectedNiveau(Niveau selectedNiveau) {
		this.selectedNiveau = selectedNiveau;
	}




	public void deleteNiveau() {
    	try{
    	
    		
    	q.delete(selectedNiveau);
    	//q.transactionCommit();
		//q.buildSession();
	
    	generateListNiveau();
       	m.success("Suppression terminé avec succès");
    	}
       	catch(Exception e){
			
    		e.printStackTrace();
    		session.close();
    		buildSession();
    			
    			m.error("Erreur de suppression !! Contactez l'administrateur");
    		}
    }
    

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Niveau Selected", ((Niveau) event.getObject()).getCode_niveau());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    



	public List<Niveau> getNiveauList() {
		return niveauList;
	}




	public void setNiveauList(List<Niveau> niveauList) {
		this.niveauList = niveauList;
	}




	public void editNiveau(RowEditEvent event) {
		
	
    	    	    	
    	
    		//uh.update((Niveau)event.getObject());
		Niveau n = (Niveau)event.getObject();
		
	
		q.executeQuery("update Niveau set designation='"+n.getDesignation()+"'");
				
    	
    		m.success("Modification terminé avec succès");
    		
   
    }
     
    public void cancelEditNiveau(RowEditEvent event) {
    	m.info("Modification Annulée");
       /* FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }

    public void reset() {
    	
    	niveau.setDesignation(null);
   
    }
    
}
