package com.ecole.utilities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.ecole.models.Eleve;
import com.ecole.models.Parent;

public class Querys {
	
	private Session session;

	
	public void save(Object o){
		
		buildSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
		//buildSession();
		
	}
	
/*	public List find(Class c, String column, String value, Boolean restriction){
		buildSession();
		session.beginTransaction();
		Criteria criteria;
		if(restriction)
			criteria = session.createCriteria(c).add(Restrictions.eq(column,value));
		else
			 criteria = session.createCriteria(c);
		
		List l = criteria.list();
		session.getTransaction().commit();
		session.close();
		 return l;
	}*/
	public List find(Class c,String column, String value, String restType){
		buildSession();
		session.beginTransaction();
		Criteria criteria;
		if(restType.equals("isnull"))
			criteria = session.createCriteria(c).add(Restrictions.isNull(column));
		else if (restType.equals("equal"))
			criteria = session.createCriteria(c).add(Restrictions.eq(column,value));
		else if (restType.equals("isnotnull"))
			criteria = session.createCriteria(c).add(Restrictions.isNotNull(column));
		else
			criteria = session.createCriteria(c);
		
		List l = criteria.list();
		session.getTransaction().commit();
		session.close();
		 return l;
	}


		public void delete(Object o){
			buildSession();
			session.beginTransaction();
			session.delete(o);
			session.getTransaction().commit();
			session.close();

			
		}
		public void executeQuery(String query){
			buildSession();
			session.beginTransaction();
			Query q = session.createQuery(query);
			q.executeUpdate();
			session.getTransaction().commit();
			session.close();
			
			
		}
		
		public List getAll(String table){

			buildSession();
			session.beginTransaction();
			
			Query q = session.createQuery("From "+table);
			List l = q.list();
			session.getTransaction().commit();
			session.close();
			
			return l;
			
			
		}
		

//	public Session getSession() {
	//	return session;
//	}


	public void setSession(Session session) {
		this.session = session;
	}
	
    public void buildSession(){

    	session = HibernateUtil.getSessionFactory().getCurrentSession();

    	    	
    }
	
}
