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




/*La classe controller gestisce le varie funzionalità API REST fornite da Spring boot
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
	    public List getAllData() {
	        return service.getData();
	    }
	 /**
	     * Metodo che gestisce la richiesta GET alla rotta "/metadata", restituisce i metadata
	     *
	     * @return lista dei metadata
	     */
	    @GetMapping("/metadata")
	    public List<Map> getMetadata() {
	        return service.getMetadata();
	    }
	    /**
	     * Metodo che gestisce la richiesta GET alla rotta "/statistiche" e che restituisce le statistiche
	     * 
	     * @param fieldName nome del campo del quale si vogliono calcolare le statistiche
	     * @return lista delle statistiche
	     */
	    @GetMapping("/statische")
	    public List<Map> getStats(@RequestParam(value = "field", defaultValue = "") String fieldName) {
	    	Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
	    	List<Map> list = new ArrayList<>();
	    	if(fieldName.equals("")) {  // se non viene specificato il campo calcola le statistiche di ogni attributo
	    		for(int i=0; i < fields.length; i++) {
	    			list.add(service.getStat(fields[i].getName()));		
	    		}
	    		return list;
	    	}
	    	else {  // altrimenti calcola le statistiche del solo campo specificato
	    		list.add(service.getStat(fieldName));
	    		return list;
	    	}
		}
	    /**
	     * Metodo che gestisce la richiesta POST alla rotta "/filtro" e che restituisce i dati filtrati 
	     * 
	     * @param req oggetto di tipo Filtro al quale vengono passati i valori del body tramite una chiamata POST
	     * @return lista dei dati opportunamente filtrati
	     */
	    @PostMapping("/data")
	    public List getFilterData(@RequestBody Filtro req) {
	    	return service.getFilterData(req.getFieldName(), req.getOp(), req.getRif());
	    }
	    
	    /**
	     * Metodo che restituisce la richiesta POST alla rotta "/statistiche" e che restituisce le statistiche dei dati filtrati se non si specifica
	     * il nome del campo, oppure, se lo si specifica, restituisce le statistiche dei dati filtrati solo del campo desiderato
	     * 
	     * @param fieldName nome del campo del quale si vogliono calcolare le statistiche
	     * @param req oggetto di tipo Filtro al quale vengono passati i valori del body tramite una chiamata POST
	     * @return lista delle statistiche dei dati filtrati
	     */
	    @PostMapping("/stats")
	    public List<Map> getFilterStat(@RequestParam(value = "field", defaultValue = "") String fieldName, @RequestBody Filtro req) {
	    	List<Map> listaStats = new ArrayList<>();
	    	List listaFiltrata = service.getFilterData(req.getFieldName(), req.getOp(), req.getRif());
	    	Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
	    	// se non specifico il nome del campo, mi restituisce le statistiche di tutti gli attributi Erasmus dei dati filtrati secondo i parametri passati nel body
	    	if(fieldName.equals("")) {
	    		for(int i=0; i < fields.length; i++) {
	    			listaStats.add(service.getStats(fields[i].getName(), listaFiltrata));		
	    		}
	    		return listaStats;
	    	}
	    	else {  // altrimenti restituisce solo quelli del parametro specificato
	    		listaStats.add(service.getStats(fieldName, listaFiltrata));
	    	}
			return listaStats;
	    }
	}