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
import progetto.Tool.Download_Parsing;
import progetto.Tool.Statistiche;

@Service
public class EuropeanService 
{
	private String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ";
	private Download_Parsing tools;
	private Metadata serviceMeta;
	private Statistiche serviceStat;
	private Filtro serviceFiltro;
	private List<EuropeanInformationSociety> lista;
	
	public EuropeanService()
	{
		this.tools = new Download_Parsing();
		this.serviceMeta = new Metadata();
		this.serviceStat = new Statistiche();
		this.serviceFiltro = new Filtro();
		
		String urlD="";
		urlD = tools.dowload(url);
		lista=tools.parsing(urlD);
		
	}
	public List<Map>getMetadata()
	{
		return serviceMeta.getMetadata();
		
	}
	
	public List<EuropeanInformationSociety> getData()
	{
		return this.lista;
	}
	
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
	
	public List fieldValues(String fieldName, List list) {
		List<Object> values = new ArrayList<>();
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
	public List<EuropeanInformationSociety> getFilterData(String fieldName, String op, Object rif) {
		return this.serviceFiltro.select(getData(), fieldName, op, rif);
	}
}
	
	

