package com.ecole.beans;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ecole.models.Compte;

import com.ecole.models.Eleve;

import com.ecole.securite.UidGen;


public class TestMain {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		//TestMain obj = new TestMain();
	/*	AnneeScolaire n = new AnneeScolaire();
		*/
		UidGen n = new UidGen();
		System.out.println("uuid: "+n.getUid());
		Eleve e = new Eleve();
		//e.setMatricule_eleve("13119ee91ab549feb1a8de6ad8c4e154");
		e.setNom("Saidi");
		e.setPrenom("mohamed");
		e.setMatricule_eleve(n.getUid());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		
		e.setDate_naissance(dateFormat.parse("1988-05-15"));
		e.setSexe("homme");
		//EleveHome eh = new EleveHome();
		//eh.persist(e);
		//eh.delete(e);*/
		/*Compte n = new Compte();
			n.setId_utilisateur("2");
			n.setLogin("ksaidi");
			n.setPassword("dali");
			n.setType_compte("admin");
			CompteHome nh = new CompteHome();
			nh.persist(n);*/
			/*
		n.setCode("2018-2016");
		n.setDesignation("hoho");
		AnneeScolaireHome nh = new AnneeScolaireHome();*/
		//obj.saveRecord();
		//obj.updateUser("2014-2015");
		//obj.deleteUser("2014-2015");
		//obj.getList();
	}
/*
	public void saveRecord()
	{
		AnneeScolaire  u = new AnneeScolaire("2014-2015","2014-2015");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try
		{
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
			System.out.println("Data Saved");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{session.close();}

	}
	public void deleteUser(String code)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try
		{
			transaction = session.beginTransaction();
			AnneeScolaire u = (AnneeScolaire)session.get(AnneeScolaire.class,code);
			session.delete(u);
			transaction.commit();
			System.out.println("Data Deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	public void updateUser(String code)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try
		{
			transaction = session.beginTransaction();
			AnneeScolaire u = (AnneeScolaire)session.get(AnneeScolaire.class,code);
			u.setCode("ShivaSoft");
			transaction.commit();
			System.out.println("Data Updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}

	public void getList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try
		{
			transaction = session.beginTransaction();
			List<AnneeScolaire> uList = session.createQuery("from annee_scolairre").list();
			for(AnneeScolaire u : uList)
			{
				System.out.println("Code - "+u.getCode());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}*/
}