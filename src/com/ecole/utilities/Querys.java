package com.ecole.utilities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.ecole.models.Parent;

public class Querys {
	
	private Session session;

	
	public void save(Object o){
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		
		session.save(o);
		session.getTransaction().commit();
		session.close();
		buildSession();
		
	}
	
	public List find(Class c, String column, String value){
		session.close();
		buildSession();

		if(!session.getTransaction().isActive())
			session.beginTransaction();
		

		
		 return session.createCriteria(c).add(Restrictions.eq(column,value)).list();
	}
	
	

	
	


		public void delete(Object o){
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			session.delete(o);

			
		}
		public void executeQuery(String query){
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
		
			Query q = session.createQuery(query);

		 q.executeUpdate();
		transactionCommit();
			session.close();
			buildSession();
			
			
		}
		
		public List getAll(String table){
			session.close();
			buildSession();
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query q = session.createQuery("From "+table);
			return q.list();
			
			
		}
		
		public void executeQuery(int nbr, String params, String query){
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query q = session.createSQLQuery(query);
			
			
			String[] l;
			l=params.split(",");
			
			for(int i=0;i<nbr;i++)
				q.setParameter(i, l[i]);
				
				int result = q.executeUpdate();
			
			
			
			
			
				session.getTransaction().commit();
				session.close();
				buildSession();
			
			
		}
		
		public void transactionCommit(){
			session.getTransaction().commit();
			
		}

	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}
	
    public void buildSession(){
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	    	
    }
	
}