package com.ecole.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.ecole.models.*;
import com.ecole.utilities.SessionUtils;
import com.ecole.utilities.Util;

@Named("Login")
@RequestScoped

public class LoginBean implements Serializable{
	  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1094801825228346363L;
	private String password;
	private String login;
	private String message;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	public String checkLogin() {
      
			/*Compte n = new Compte();
			n.setId_utilisateur(login);
			n.setLogin("msaidi");
			n.setPassword(password);
			n.setType_compte("admin");
			CompteHome nh = new CompteHome();
			nh.persist(n);*/
	/*	
	MessageView m = new MessageView();
		//CompteHome ch = new CompteHome();
		//List<Compte> lc = ch.findByCriteria("login", login);
		System.out.println(login+"--"+password);
		if (lc.isEmpty()||!(lc.get(0).getPassword().equals(password))){
			m.fatal("  Login ou mot de passe incorrecte");
			
			return "loginfailed";
			
		}else{
            HttpSession session = Util.getSession();
            session.setAttribute("uid",lc.get(0).getId_utilisateur() );
            if(lc.get(0).getType_compte().equals("admin"))
			return "adminpage";
            return "sucess";
		}
		*/
		return null;
}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}


}
