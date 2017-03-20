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
		this.selectedClasse = selectedClasse;
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
    	   	
    	listDesClasse=q.find(Classe.class,"", "", false);
    	System.out.println(((Classe)listDesClasse.get(0)).getDesignation());

    	parent=null;
    	
    	generateListClasse();
    	generateListEleve2();
    //	getListParentEleve();
    }


	/*public Eleve getEleve() {
		return eleve;
	}*/


	/*public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
*/
	

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
		if(!q.getSession().isOpen()){
			q.buildSession();
			q.getSession().beginTransaction();
		}
    	eleveList = q.getSession().createCriteria(Eleve.class).add(Restrictions.isNull("deleted")).list();
    
    	q.getSession().close();
    }
    public void generateListClasse()
    {	
		//if(!q.getSession().isOpen())
			//q.buildSession();
		//if(!q.getSession().getTransaction().isActive())
			// q.getSession().beginTransaction();
		
    	 
    	 classeList = new HashMap<String, String>();
    	 for (int i =0; i<listDesClasse.size();i++){
    		 
    		 classeList.put(((Classe)listDesClasse.get(i)).getDesignation(),((Classe)listDesClasse.get(i)).getCode_classe() );
    		 //System.out.println(((Classe)l.get(i)).getDesignation());
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
    	
    	
		/*if(!q.getSession().isOpen())
			q.buildSession();
		if(!q.getSession().getTransaction().isActive())
			 q.getSession().beginTransaction();*/
		for(int i=0; i< eleveList.size();i++){
			
			EleveTransit et = new EleveTransit();

			e = (Eleve) eleveList.get(i);
			
			l = q.find(Eleve_Classe.class, "matricule_eleve", e.getMatricule_eleve(), true);
			if(l.size() != 0 ){
				
				cl =  q.find(Classe.class, "code_classe",((Eleve_Classe)  l.get(0)).getCode_classe(),true);
				et.setClasse((Classe) cl.get(0));
				
				
				}
			l=q.find(Parent.class,"matricule_eleve", e.getMatricule_eleve(),true)	;
    		if(! l.isEmpty()){
    			
    			//l=session.createCriteria(Utilisateur.class).add(Restrictions.eq("id_utilisateur",((Parent)l.get(0)).getId_utilisateur())).list();
    			l=q.find(Utilisateur.class, "id_utilisateur", ((Parent)l.get(0)).getId_utilisateur(),true);
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

    		//if(!session.getTransaction().isActive())
    			//session.beginTransaction();
    	//Parent pr = new Parent();
    	//pr.setMatricule_eleve(selectedEleve.getMatricule_eleve());
    	List prl = q.find(Parent.class,"matricule_eleve", selectedEleveTransit.getEleve().getMatricule_eleve(),true);
    	System.out.println(prl.size()+"  size prl list");
    	if(! prl.isEmpty()){
    		//pr.setId_utilisateur(((Parent)prl.get(0)).getId_utilisateur());
    		//pr = session.load(Parent.class,new PKField(((Parent)prl.get(0)).getId_utilisateur(),((Parent)prl.get(0)).getId_utilisateur()));
    		//System.out.println("Parent id"+pr.getId_utilisateur());
    		q.executeQuery("delete Parent  where matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");
    		//System.out.println("id uti == "+((Parent)prl.get(0)).getId_utilisateur());
        	//q.transactionCommit();
			//q.delete(pr);
    		}
    	
    	//q.executeQuery("delete Eleve where matricule_eleve='"+selectedEleve.getMatricule_eleve()+"'");
    	q.executeQuery("update Eleve set deleted=DATE_FORMAT(NOW(),'%Y-%m-%d')  where"
    			+ " matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");;
    	generateListEleve2();
    	//getListParentEleve();
    	
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
    
    
  
    public List<Eleve> getEleveList() {
    	
    	return eleveList;
	}

	public void setEleveList(List<Eleve> eleveList) {
		this.eleveList = eleveList;
	}

	public void editEleve(RowEditEvent event) {
		
	
    	    	    	
    	if(((EleveTransit)event.getObject()).getEleve().getSexe().equals("Homme") || ((EleveTransit)event.getObject()).getEleve().getSexe().equals("Femme") ){
    		
    		Eleve e=((EleveTransit)event.getObject()).getEleve();
    		
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		String date = formatter.format(e.getDate_naissance());
    		q.executeQuery("update Eleve set nom='"+e.getNom()+"', prenom='"+e.getPrenom()+"',"
    				+ "sexe='"+e.getSexe()+"',date_naissance='"+date+"' where matricule_eleve='"+e.getMatricule_eleve()+"'");
    		q.executeQuery("update Eleve_Classe set code_classe='"+selectedClasse.getCode_classe()+"' where matricule_eleve='"+e.getMatricule_eleve()+"'");
    		

    		m.success("Modification terminée avec succès");

    		
    	}
    	else{
    		m.error("Modification Erronée!! Cause Majuscule (Sexe doit etre \"Homme\" ou \"Femme\")");
    	
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
    		
    		//UtilisateurHome uh = new UtilisateurHome();
    		 selectedparent = q.find(Utilisateur.class,"cin", parent,true);
    		if(selectedparent.size()==0)
    			nomparent="Parent n'existe pas";
    		
    		else if (! selectedparent.get(0).getRole().equals("Parent"))
    			nomparent="L'utilisateur n'est pas un parent";
    		else
    			nomparent=selectedparent.get(0).getNom()+" "+selectedparent.get(0).getPrenom();
    		
    		finalparent=nomparent;
    		
    	}
    	
    	
    	
    	
    }
   /* public  void getListParentEleve(){
    	
    
    	eleveListTransit = new ArrayList<EleveTransit>();
  
    	
  
    	List l = new ArrayList();
    	
    	for (int i=0; i< eleveList.size();i++){
    		
    		///et.setParent(null);
    		//et.setEleve(null);
    		//et.setEleve(eleveList.get(i));
    		EleveTransit et = new EleveTransit();
    		
    		//l= session.createCriteria(Parent.class).add(Restrictions.eq("matricule_eleve", eleveList.get(i).getMatricule_eleve())).list();
    			l=q.find(Parent.class,"matricule_eleve", eleveList.get(i).getMatricule_eleve())	;
    		if(! l.isEmpty()){
    			
    			//l=session.createCriteria(Utilisateur.class).add(Restrictions.eq("id_utilisateur",((Parent)l.get(0)).getId_utilisateur())).list();
    			l=q.find(Utilisateur.class, "id_utilisateur", ((Parent)l.get(0)).getId_utilisateur());
    			et.setEleve(((Eleve)eleveList.get(i)));
    			et.setParent(((Utilisateur)l.get(0)));
    			eleveListTransit.add(et);
    			
    		}
    		
    	
    	}
    
    	if(eleveListTransit.isEmpty())
    		m.info("Liste des Parents est vide ");
    	

    }*/
    public void editParent(){
    	
    	

    	
    	//System.out.println(selectedparent.get(0).getNom());
    	
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
			 System.out.println("------selected eleve  "+selectedEleveTransit.getEleve().getNom());
			
			 q.executeQuery("delete Parent where id_utilisateur='"+selectedEleveTransit.getParent().getId_utilisateur()+
					 "' and matricule_eleve='"+selectedEleveTransit.getEleve().getMatricule_eleve()+"'");

			 //q.executeQuery("insert into Parent(id_utilisateur,matricule_eleve) values('"+selectedparent.get(0).getId_utilisateur()+"','"+selectedEleveTransit.getEleve().getMatricule_eleve()+"')");
			
			 Parent p = new Parent();
			 p.setId_utilisateur(selectedparent.get(0).getId_utilisateur());
			 p.setMatricule_eleve(selectedEleveTransit.getEleve().getMatricule_eleve());
			 q.save(p);
			
			//getListParentEleve();
			 m.info("Modification reuissite");
			 //System.out.println(selectedEleveTransit.getEleve().getNom());
				}
    	
		}
    	//parent=null;
    	
   /* List<Utilisateur> l = uh.findByCriteria("cin", parent);
    System.out.println(l.size());
    if (l.isEmpty()){
    	m.error("CIN parent n'existe pas");
    	System.out.println("eoor");
    }
    else{
    	
    	pt.setId_utilisateur(((EleveTransit)event.getObject()).getParent().getId_utilisateur());
    	pt.setMatricule_eleve(((EleveTransit)event.getObject()).getEleve().getMatricule_eleve());
    	
    	ph.delete(pt);
    	pt.setId_utilisateur(l.get(0).getId_utilisateur());
    	System.out.println("delte yes");
    	//ph.persist(pt);
    }
    	//ph.update(((EleveTransit)event.getObject()).getParent());*/
    	
    }

	/*public List<EleveTransit> getEleveListTransit() {
		//getListParentEleve();
		return eleveListTransit;
	}
*/
	public void setEleveListTransit(List<EleveTransit> eleveListTransit) {
		this.eleveListTransit = eleveListTransit;
	}
	
    public void handleClose() {
        m.info("Suppression Annulée");
    }
}
