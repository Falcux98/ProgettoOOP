package progetto.Controller;

import java.io.*;    
import java.lang.reflect.*;
import java.util.*;
import progetto.GestioneDati.*;
import progetto.Tool.*;
import progetto.GestioneDati.EuropeanInformationSociety;
import progetto.Service.EuropeanService;
import progetto.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**La classe controller gestisce le varie funzionalità API REST fornite da Spring boot
 * questa classe implementa i metodi di richiesta dei metadati, dei dati e delle statistiche*/
 
@RestController
public class Controller 
{
	@Autowired
	private EuropeanService service;
	
	
	public Controller(EuropeanService service)
	{
		this.service = service;
	}
	/**
     * Questo metodo gestisce la richiesta GET relativa alla richiesta dei dati, "/data", 
     * si occupa di restituire l'intero dataset
     *
     * @return la lista degli oggetti presenti nel dataset
     */
	 @GetMapping("/data")
	    public Vector getAllData() {
	        return service.getData();
	    }
	 /**
	     * Questo metodo gestisce la richiesta GET relativa alla richiesta dei metadati, "/metadata", 
	     * si occupa di restituire i metadati presenti nel dataset
	     *
	     * @return la lista dei metadati presenti nel dataset
	     */
	    @GetMapping("/metadata")
	    public Vector<Map> getMetadata() {
	        return (Vector<Map>) service.getMetadata();
	    }
	    /**
	     * Questo metodo gestisce la richiesta GET relativa alla richiesta delle statistiche,"/statistiche"
	     * è possibile ottnere le statistiche di un singolo attributo, sia quelle generali del dataset
	     * @param fieldName attributo del quale si vogliono calcolare le statistiche
	     * @return lista delle statistiche
	     */
	    @GetMapping("/statistiche")
	    public Vector<Map> getStatistiche(@RequestParam(value = "field", defaultValue = "") String fieldName) {
	    	Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
	    	Vector<Map> lista = new Vector<>();
	    	if(fieldName.equals("")) // se non viene specificato nulla, calcolerà le statistiche di ogni attributo
	    	{  
	    		for(int i=0; i < fields.length; i++) {
	    			lista.add(service.getStatistiche(fields[i].getName()));		
	    		}
	    		return lista;
	    	}
	    	else {  											// altrimenti calcolerà le statistiche del solo attributo richiesto
	    		lista.add(service.getStatistiche(fieldName));
	    		return lista;
	    	}
		}

	}