package progetto.Controller;

import java.io.*;    
import java.lang.reflect.*;
import java.util.*;
import progetto.GestioneDati.*;
import progetto.Tool.*;
import progetto.GestioneDati.EuropeanInformationSociety;
import progetto.GestioneDati.Filtro;
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
     * Metodo che gestisce la richiesta GET alla rotta "/data", restituisce l'intero dataset
     *
     * @return lista di tutti gli oggetti del dataset
     */
	 @GetMapping("/data")
	    public Vector getAllData() {
	        return service.getData();
	    }
	 /**
	     * Metodo che gestisce la richiesta GET alla rotta "/metadata", restituisce i metadata
	     *
	     * @return lista dei metadata
	     */
	    @GetMapping("/metadata")
	    public Vector<Map> getMetadata() {
	        return (Vector<Map>) service.getMetadata();
	    }
	    /**
	     * Metodo che gestisce la richiesta GET alla rotta "/statistiche" e che restituisce le statistiche
	     * 
	     * @param fieldName nome del campo del quale si vogliono calcolare le statistiche
	     * @return lista delle statistiche
	     */
	    @GetMapping("/statistiche")
	    public Vector<Map> getStatistiche(@RequestParam(value = "field", defaultValue = "") String fieldName) {
	    	Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
	    	Vector<Map> lista = new Vector<>();
	    	if(fieldName.equals(""))
	    	{  // se non viene specificato il campo calcola le statistiche di ogni attributo
	    		for(int i=0; i < fields.length; i++) {
	    			lista.add(service.getStatistiche(fields[i].getName()));		
	    		}
	    		return lista;
	    	}
	    	else {  // altrimenti calcola le statistiche del solo campo specificato
	    		lista.add(service.getStatistiche(fieldName));
	    		return lista;
	    	}
		}

	}