package progetto.Service;

import java.lang.reflect.Field; 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.springframework.stereotype.Service;
import progetto.GestioneDati.*;
import progetto.Tool.*;
import progetto.GestioneDati.EuropeanInformationSociety;
import progetto.GestioneDati.Filtro;
import progetto.GestioneDati.Metadata;
import progetto.Tool.DownloadParsing;
import progetto.Tool.Statistiche;

/** Classe ch gestisce le operazioni di download e di carica del dataset e che crea un collegamento tra tutte
 * le classi con il controller attraverso i metodi costruiti
 * @author Diego
 *
 */

@Service
public class EuropeanService 
{
	private String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ";
	private DownloadParsing tools;
	private Metadata serviceMeta;
	private Statistiche serviceStat;
	private Filtro serviceFiltro;
	private ArrayList<EuropeanInformationSociety> lista;
	
	/**
	 * Costruttore che effettua al primo avvio dell'applicazione il download e il parsing dei dati 
	 * che restituiscono i valori del file csv
	 */
	public EuropeanService()
	{
		this.tools = new DownloadParsing();
		this.serviceMeta = new Metadata();
		this.serviceStat = new Statistiche();
		this.serviceFiltro = new Filtro();
		
		String urlD="";
		urlD = tools.dowload(url);
		lista=tools.parsing(urlD);
		
	}
	/**
	 * Metodo che restituisce i metadati del file CSV
	 * @return la lista contenente i metadati
	 */
	public List<Map>getMetadata()
	{
		return serviceMeta.getMetadata();
		
	}
	/**
	 * Metodo che restituisce i dati del file csv
	 * @return lista dei dati csv
	 */
	public ArrayList<EuropeanInformationSociety> getData()
	{
		return this.lista;
	}
	/**
	 * Metodo che restituisce le statistiche di un dato attributo
	 * @param nomeCampo contiene il valore dell'attributo del quale si vogliono calcolare le statistiche
	 * @return map delle statistiche desiderate
	 */
	public Map<String, Object> getStat(String nomeCampo)
	{
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapError = new HashMap<>();
		mapError.put("Errore", "Campo inesistente");
		Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
		for (Field f : fields) {
			if(nomeCampo.equals(f.getName()))
				map = serviceStat.getStats(nomeCampo, fieldValues(nomeCampo, getData()));
		}
		if(map.isEmpty()) return mapError;
		else return map;
	}
	/**
	 * Metodo che restituisce le statistiche di un dato campo di una lista passatagli come parametro (si utilizza per filtrare le statistiche)
	 * @param nomeCampo contiene il valore del campo del quale si vogliono calcolare le statistiche
	 * @param lista contiene la lista dalla quale estrarre poi le statistiche di quel campo
	 * @return map delle statistiche desiderate (quelle filtrate)
	 */
	public Map<String, Object> getStats(String nomeCampo, List lista) 
	{
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapError = new HashMap<>();
		mapError.put("Errore", "Campo inesistente");
		Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
		for (Field f : fields) {
			if(nomeCampo.equals(f.getName()))
				map = serviceStat.getStats(nomeCampo, fieldValues(nomeCampo, lista));
		}
		if(map.isEmpty()) return mapError;
		else return map;
	}
	/**
	 * Metodo che estrae i valori di un determinato campo, passato tramite fieldName
	 * @param fieldName nome del campo del file CSV
	 * @param list lista che si ottiene dopo aver effettuato il parsing, array di oggetti "EuropeanInformationSociety"
	 * @return la lista che contiene i valori di un determinato campo
	 */
	public ArrayList fieldValues(String fieldName, List list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
			for(Object e : list) {
				// scorre il vettore di campi e controlla se il nome del campo corrispondente Ã¨ uguale a quello passatogli come parametro 
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equals(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object val = m.invoke(e);
						values.add(val); // se il controllo restituisce vero, aggiunge alla lista il valore dell'ogetto della lista passatagli come parametro ottenuto con il metodo getMethod
					}
				}
			}
		} catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch(SecurityException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return values;
	}
	
	/**
	 * Metodo che filtra i dati del csv
	 * 
	 * @param fieldName contiene il nome del campo richiesto
	 * @param op contiene l'operatore che si vuole utilizzare
	 * @param rif valore di riferimento
	 * @return lista filtrata
	 */
	public ArrayList<EuropeanInformationSociety> getFilterData(String fieldName, String op, Object rif) {
		return this.serviceFiltro.select(getData(), fieldName, op, rif);
	}
}
	
	

