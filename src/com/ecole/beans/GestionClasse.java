package com.ecole.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


import com.ecole.models.Classe;
import com.ecole.models.Eleve;
import com.ecole.models.EleveTransit;
import com.ecole.models.Niveau;
import com.ecole.models.Parent;
import com.ecole.models.Utilisateur;
import com.ecole.models.ClasseTransit;
import com.ecole.securite.UidGen;



public class GestionClasse  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8333307778255556369L;
	private Classe classe=new Classe();
     private ClasseTransit selectedClasse;
     private List<ClasseTransit> classeList;
     private List<Classe> classeList2;

     private Querys q;
     private Session session;
     private Map<String,String> listNiveau;
     private Classe classetmp=new Classe();

     private Niveau niveau;

	private 	MessageView m = new MessageView();
	
    @PostConstruct

    public void init() {
    	q = new Querys();
    	q.buildSession();
    	niveau = new Niveau();
    	listNiveau=new HashMap<String, String>();
    	
    	
    	classe=new Classe();
    	
    	
   
    	

    }



	

	public List<Classe> getClasseList2() {
		return classeList2;
	}










	public Classe getClassetmp() {
		return classetmp;
	}





	public void setClassetmp(Classe classetmp) {
		this.classetmp = classetmp;
	}





	public void setClasseList2(List<Classe> classeList2) {
		this.classeList2 = classeList2;
	}





	public Classe getClasse() {
		return classe;
	}



	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public void generateListNiveau(){
		
		//q.getSession().close();
		//q.buildSession();
		
		List l = q.getAll("Niveau");
		
	for(int i=0; i< l.size();i++){
		
		System.out.println();
	listNiveau.put(((Niveau)l.get(i)).getDesignation(), ((Niveau)l.get(i)).getCode_niveau());
		
	}
		
	}
	public Map<String, String> getListNiveau() {
		generateListNiveau();
		return listNiveau;
	}

	public void setListNiveau(Map<String, String> listNiveau) {
		this.listNiveau = listNiveau;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}




	public void addClasse(){
		
		UidGen n = new UidGen();
		
				
		
		classe.setCode_classe(n.getUid());
		if (niveau.getCode_niveau()==null)
			m.error("Code niveau vide");
		else{
		classe.setCode_niveau(niveau.getCode_niveau());
		
		 
		try{
		//uh.persist(classe);
			q.save(classe);

			reset();
		m.success("Enregistrement terminé avec succès");
			   



	   
	    
		}
		catch(Exception e){
		//	q.getSession().close();
			q.buildSession();
			
		e.printStackTrace();
			
			m.error("Erreur d'enregistrement !! Contactez l'administrateur");
		}
		
		}
		
	}

     
    public String onFlowProcess(FlowEvent event) {

            return event.getNewStep();
        
    }

    
    public void generateListClasse()
    {	
		/*if(!q.getSession().isOpen())
			q.buildSession();
		if(!q.getSession().getTransaction().isActive())
			 q.getSession().beginTransaction();
	    */
    	classeList = new ArrayList<ClasseTransit>();
  
    	
  
    	List l = q.getAll("Classe"), l2 = new ArrayList();
    	
    	classeList2 = l;
    	for (int i=0; i< l.size();i++){
    		
    		
    		
    		ClasseTransit ct = new ClasseTransit();
    		
   			l2=q.find(Niveau.class,"code_niveau",((Classe)l.get(i)).getCode_niveau(),true);
    		if(! l2.isEmpty()){
    			
    			ct.setClasse((Classe)l.get(i));
    			ct.setNiveau((Niveau)l2.get(0));
    			classeList.add(ct);
    			
    		}
    		
    	
    	}
    
    	if(classeList.isEmpty())
    		m.info("Liste des Classe est vide ");
    	
    	
    	
    	
    	
    }


     
    public ClasseTransit getSelectedClasse() {
		return selectedClasse;
	}




	public void setSelectedClasse(ClasseTransit selectedClasse) {
		this.selectedClasse = selectedClasse;
	}




	public void deleteClasse() {
    	try{
    	
    	
    	q.delete(selectedClasse.getClasse());
    	//q.transactionCommit();
		//q.buildSession();
	
       	m.success("Suppression terminé avec succès");
    	}
       	catch(Exception e){
			
    		e.printStackTrace();
    		session.close();
    		q.buildSession();
    			
    			m.error("Erreur de suppression !! Contactez l'administrateur");
    		}
    }
    

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Classe Selected", ((Classe) event.getObject()).getCode_classe());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    







	public List<ClasseTransit> getClasseList() {
		generateListClasse();
		return classeList;
	}


	public void setClasseList(List<ClasseTransit> classeList) {
		this.classeList = classeList;
	}


	public void editClasse(RowEditEvent event) {
		
	
    	    	    	
    	
    		//uh.update((Classe)event.getObject());
		
		
		
		/*if(!q.getSession().isOpen())
			q.buildSession();
		if(!q.getSession().getTransaction().isActive())
			 q.getSession().beginTransaction();*/
		ClasseTransit ct = (ClasseTransit)event.getObject();
		System.out.println(ct.getDesignation());
		System.out.println("designation"+classetmp.getDesignation());
		q.executeQuery("update Classe set designation='"+classetmp.getDesignation()+"'where code_classe ='"+
				ct.getClasse().getCode_classe()+"'");
				
    	classetmp=new Classe();
    		m.success("Modification terminé avec succès");
    		
    		
   
    }
     
    public void cancelEditClasse(RowEditEvent event) {
    	m.info("Modification Annulée");
       /* FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }

    public void reset() {
    	
    	classe.setDesignation(null);
   
    }
    
}
