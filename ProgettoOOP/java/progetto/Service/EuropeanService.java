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
	private Statistiche serviceStatistiche;
	private Vector<EuropeanInformationSociety> lista;
	
	/**
	 * Costruttore che effettua al primo avvio dell'applicazione il download e il parsing dei dati 
	 * che restituiscono i valori del file csv
	 */
	public EuropeanService()
	{
		this.tools = new DownloadParsing();
		this.serviceMeta = new Metadata();
		this.serviceStatistiche = new Statistiche();
		
		String urlD="";
		urlD = tools.dowload(url);
		lista=tools.parsing(urlD);
		
	}
	/**
	 * Metodo che restituisce i metadati del file CSV
	 * @return la lista contenente i metadati
	 */
	public List<Map> getMetadata()
	{
		return serviceMeta.getMetadata();
		
	}
	/**
	 * Metodo che restituisce i dati del file csv
	 * @return lista dei dati csv
	 */
	public Vector getData()
	{
		return this.lista;
	}
	
	public Map<String, Object> getStatistiche(String nomeCampo) 
	{
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapError = new HashMap<>();
		mapError.put("ATTEZIONE", "NON VI SONO STATISTICHE SULL'ATTRIBUTO INSERITO");
		Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
		for (Field f : fields) 
		{
			if(nomeCampo.equalsIgnoreCase(f.getName()))
				map = serviceStatistiche.getStatistiche(nomeCampo, fieldValues(nomeCampo, getData()));
			else if(nomeCampo.equalsIgnoreCase(f.getName()))
				map = serviceStatistiche.getStatistiche(nomeCampo, fieldValues(nomeCampo, lista));
			
		}
		if(map.isEmpty()) return mapError;
		else 
		return map;
	}
	/**
	 * Metodo che estrae i valori di un determinato campo, passato tramite fieldName
	 * @param fieldName nome del campo del file CSV
	 * @param list lista che si ottiene dopo aver effettuato il parsing, array di oggetti "EuropeanInformationSociety"
	 * @return la lista che contiene i valori di un determinato campo
	 */
	public List fieldValues(String fieldName, List list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
			for(Object e : list) {
				// scorre il vettore di campi e controlla se il nome del campo corrispondente Ã¨ uguale a quello passatogli come parametro 
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equalsIgnoreCase(fields[i].getName())) {
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
	
}
	
	

