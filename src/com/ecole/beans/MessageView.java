package com.ecole.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageView {
	

	public void message() {
       
    }

    public void success(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succes",message));
    }
     
    public void info(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,""));
    }
     
    public void warn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message,""));
    }
     
    public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message,""));
    }
     
    public void fatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message,""));
    }
}
