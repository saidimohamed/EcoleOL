package com.ecole.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ecole.models.Utilisateur;


 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class UtilisateurLazyModel extends LazyDataModel<Utilisateur> {
     
    private List<Utilisateur> datasource;
     
    public UtilisateurLazyModel(List<Utilisateur> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Utilisateur getRowData(String rowKey) {
        for(Utilisateur utilisateur : datasource) {
            if(utilisateur.getId_utilisateur().equals(rowKey))
                return utilisateur;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Utilisateur utilisateur) {
        return utilisateur.getId_utilisateur();
    }
 
    @Override
    public List<Utilisateur> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Utilisateur> data = new ArrayList<Utilisateur>();
 
        //filter
        for(Utilisateur utilisateur : datasource) {
            boolean match = true;
           
 
            if (filters != null) {
            	 
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                	
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        
                        Field field= utilisateur.getClass().getDeclaredField(filterProperty);
                        field.setAccessible(true);
                       String fieldValue = (String) field.get(utilisateur);
                       
                     
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                    	
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
            	
                data.add(utilisateur);
            }
        }
 
        //sort
        if(sortField != null) {
           // Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
