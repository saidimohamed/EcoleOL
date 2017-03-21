package com.ecole.beans;

import java.io.Serializable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.ecole.models.Classe;
import com.ecole.models.Eleve;
import com.ecole.models.EleveTransit;
import com.ecole.models.Eleve_Classe;
import com.ecole.models.Parent;
import com.ecole.models.Utilisateur;

import com.ecole.securite.UidGen;
import com.ecole.utilities.HibernateUtil;
import com.ecole.utilities.Querys;

import javassist.bytecode.analysis.Analyzer;


@ManagedBean
//@ViewScoped
public class GestionEleve  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8094807778228346369L;
	//private EleveTransit eleve;
	
     private Eleve selectedEleve;
     private List<Eleve> eleveList;
     private List<EleveTransit> eleveList2;
     private String newCinParent;
     private Session session;
     private Querys q;
     private List listDesClasse = new ArrayList<Classe>();;
     private Map<String,String> classeList = new HashMap<String, String>();
     private Classe selectedClasse;
     private EleveTransit selectedEleveTransit, eleveTransit; 
	//private Parent p;
     private String parent,nomparent,finalparent;
     private List <Utilisateur> selectedparent;
     private List<EleveTransit> eleveListTransit;
	public String getFinalparent() {
		return finalparent;
	}

	public void setFinalparent(String finalparent) {
		this.finalparent = finalparent;
	}

	public String getNomparent() {
		String value=nomparent; 
		nomparent=null;
		return value;
	}


	public Classe getSelectedClasse() {
		return selectedClasse;
	}

	public void setSelectedClasse(Classe selectedClasse) {

			this.selectedClasse=selectedClasse;

	}

	public Map<String, String> getClasseList() {
		return classeList;
	}

	public void setClasseList(Map<String, String> classeList) {
		this.classeList = classeList;
	}

	public EleveTransit getSelectedEleveTransit() {
		return selectedEleveTransit;
	}

	public void setSelectedEleveTransit(EleveTransit selectedEleveTransit) {
		
		this.selectedEleveTransit = selectedEleveTransit;
	}

	public void setNomparent(String nomparent) {
		this.nomparent = nomparent;
	}

	public String getParent() {
		return parent;
	}
    public String getNewCinParent() {
		return newCinParent;
	}

	public void setNewCinParent(String newCinParent) {
		this.newCinParent = newCinParent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	private 	MessageView m = new MessageView();
	
    @PostConstruct
    public void init() {
    	q = new Querys();
    	
    	q.buildSession();
    
    	
    	eleveTransit=new EleveTransit();
    	selectedClasse = new Classe();
    	   	
    	listDesClasse=q.find(Classe.class,"", "", "");
    	System.out.println(((Classe)listDesClasse.get(0)).getDesignation());

    	parent=null;
    	
    	generateListClasse();
    	generateListEleve2();
    }


	public EleveTransit getEleveTransit() {
		return eleveTransit;
	}

	public void setEleveTransit(EleveTransit eleveTransit) {
		this.eleveTransit = eleveTransit;
	}

	public void addEleve(){
		
		UidGen n = new UidGen();
		
		//if(!session.getTransaction().isActive())
		// session.beginTransaction();
		eleveTransit.getEleve().setMatricule_eleve(n.getUid());
		
		 
		try{
			if(selectedparent.size()==0)
				m.error("Verifier le parent");
			else if(! selectedparent.get(0).getRole().equals("Parent"))
				m.error("L'utilisateur choisie n'est pas un parent");
			else{
				Parent p = new Parent();
				Eleve_Classe ec = new Eleve_Classe();
				p.setId_utilisateur(selectedparent.get(0).getId_utilisateur());
				p.setMatricule_eleve(eleveTransit.getEleve().getMatricule_eleve());
				/*session.save(eleve);
				session.save(p);*/
				ec.setCode_annee("1");
				ec.setCode_classe(selectedClasse.getCode_classe());
				
				ec.setMatricule_eleve(eleveTransit.getEleve().getMatricule_eleve());
				q.save(eleveTransit.getEleve());
				q.save(p);
				q.save(ec);

				
				
				
				m.success("Enregistrement terminé avec succès");
				}
				reset();
				
		
	    Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("eleveform:wizard");
	    wizard.setStep("personal");


	  
	 
	  generateListEleve();
	  generateListEleve2();
	 // getListParentEleve();
	    
		}
		catch(Exception e){
			
		e.printStackTrace();
			
			m.error("Erreur d'enregistrement !! Contactez l'administrateur");
			//q.getSession().close();
			//q.buildSession();
		}
		 
		
		
	}

     
    public String onFlowProcess(FlowEvent event) {

            return event.getNewStep();
        
    }

    
    public void generateListEleve()
    {	
		
    	eleveList = q.find(Eleve.class, "deleted", "", "isnull");
    
    }
    public void generateListClasse()
    {	
    	 classeList = new HashMap<String, String>();
    	 for (int i =0; i<listDesClasse.size();i++){
    		 
    		 classeList.put(((Classe)listDesClasse.get(i)).getDesignation(),((Classe)listDesClasse.get(i)).getCode_classe() );
    	 }
    
    	
    }
    
    //////////////////////////////////////////::
    public void generateListEleve2()
    {	
    	
    	generateListEleve();
    	
    	eleveList2 = new ArrayList<EleveTransit>();
    	Eleve e = new Eleve();
    	List<Classe> cl = new ArrayList<Classe>();
    	List l = new ArrayList<>();
    	
    	
		for(int i=0; i< eleveList.size();i++){
			
			EleveTransit et = new EleveTransit();

			e = (Eleve) eleveList.get(i);
			
			l = q.find(Eleve_Classe.class, "matricule_eleve", e.getMatricule_eleve(), "equal");
			if(l.size() != 0 ){
				
				cl =  q.find(Classe.class, "code_classe",((Eleve_Classe)  l.get(0)).getCode_classe(),"equal");
				et.setClasse((Classe) cl.get(0));
				
				
				}
			l=q.find(Parent.class,"matricule_eleve", e.getMatricule_eleve(),"equal")	;
    		if(! l.isEmpty()){
    			
    			l=q.find(Utilisateur.class, "id_utilisateur", ((Parent)l.get(0)).getId_utilisateur(),"equal");
    			et.setParent(((Utilisateur)l.get(0)));
    			
    		}
			    et.setEleve(e);
			    
			    eleveList2.add(et);
			    

		    	
			    
		}
		
    
    	
    }


 
    public List<EleveTransit> getEleveList2() {
		return eleveList2;
	}

	public void setEleveList2(List<EleveTransit> eleveList2) {
		this.eleveList2 = eleveList2;
	}

	public Eleve getSelectedEleve() {

        return selectedEleve;
    }
 
    public void setSelectedEleve(Eleve selectedEleve) {
    
        this.selectedEleve = selectedEleve;
    }
     
    public void deleteEleve() {
    	try{

    	List prl = q.find(Parent.class,"matricule_eleve", selectedEleveTransit.getEleve().getMatricule_eleve(),"equal");

    	if(! prl.isEmpty()){
    		q.executeQuery("delete Parent  where matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");
    		}
    	
    	q.executeQuery("update Eleve set deleted=DATE_FORMAT(NOW(),'%Y-%m-%d')  where"
    			+ " matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");;
    	generateListEleve2();
    	
       	m.success("Suppression terminée avec succès");
    	}
       	catch(Exception e){
			
    		e.printStackTrace();
    			
    			m.error("Erreur de suppression !! Contactez l'administrateur");
    		}
    }
    

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Eleve Selected", ((Eleve) event.getObject()).getMatricule_eleve());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
  
  
	public void editEleve(RowEditEvent event) {
		
	
    	  try{  	    	
    		Eleve e=((EleveTransit)event.getObject()).getEleve();
    		
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		String date = formatter.format(e.getDate_naissance());
    		q.executeQuery("update Eleve set nom='"+e.getNom()+"', prenom='"+e.getPrenom()+"',"
    				+ "sexe='"+e.getSexe()+"',date_naissance='"+date+"' where matricule_eleve='"+e.getMatricule_eleve()+"'");
    		q.executeQuery("update Eleve_Classe set code_classe='"+selectedClasse.getCode_classe()+"' where matricule_eleve='"+e.getMatricule_eleve()+"'");
    		

    		m.success("Modification terminée avec succès");
    	  }
    	  catch (Exception e) {
			// TODO: handle exception
		    		m.error("Modification Erronée!! Contactez l'administrateur");
    	
    	}
    	
    }
     
    public void cancelEditEleve(RowEditEvent event) {
    	m.info("Modification Annulée");

    }

    public void reset() {
    	
    	eleveTransit.getEleve().setMatricule_eleve(null);
    	eleveTransit.getEleve().setSexe(null);
    	eleveTransit.getEleve().setNom(null);
    	eleveTransit.getEleve().setPrenom(null);
    	eleveTransit.getEleve().setDate_naissance(null);
    	parent=null;
    	
    }
    
    public void parentPrint(){
    	

    	
    	
    	if(parent== null || parent.equals(""))
    		nomparent=null;
    	else {
    		
    		 selectedparent = q.find(Utilisateur.class,"cin", parent,"equal");
    		if(selectedparent.size()==0)
    			nomparent="Parent n'existe pas";
    		
    		else if (! selectedparent.get(0).getRole().equals("Parent"))
    			nomparent="L'utilisateur n'est pas un parent";
    		else
    			nomparent=selectedparent.get(0).getNom()+" "+selectedparent.get(0).getPrenom();
    		
    		finalparent=nomparent;
    		
    	}
    	
    	
    	
    	
    }

    
    public void editParent(){
    	
    	if(selectedparent.size()==0)
			m.error("Parent n'existe pas");
		
		else if (! selectedparent.get(0).getRole().equals("Parent"))
			m.error("L'utilisateur n'est pas un parent");
		else{
			if (selectedEleveTransit==null) {
				System.out.println("eleve trasite null");
				q.buildSession();
			m.error("Veillez resseayer");;
			
			}
				
				else{
			
			 q.executeQuery("delete Parent where id_utilisateur='"+selectedEleveTransit.getParent().getId_utilisateur()+
					 "' and matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");

			
			 Parent p = new Parent();
			 p.setId_utilisateur(selectedparent.get(0).getId_utilisateur());
			 p.setMatricule_eleve(selectedEleveTransit.getEleve().getMatricule_eleve());
			 q.save(p);
			
			 m.info("Modification reuissite");
				}
    	
		}
    	
    }

	public void setEleveListTransit(List<EleveTransit> eleveListTransit) {
		this.eleveListTransit = eleveListTransit;
	}
	
    public void handleClose() {
        m.info("Suppression Annulée");
    }
}
