package progetto.Service;

import java.lang.reflect.Field; 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.springframework.stereotype.Service;
import progetto.GestioneDati.*;
import progetto.Tool.*;
import progetto.GestioneDati.EuropeanInformationSociety;
import progetto.GestioneDati.Metadata;
import progetto.Tool.DownloadParsing;
import progetto.Tool.Statistiche;

/** La classe Service funge da gestore  ellle operazioni di download e di carica del dataset e mette in collegamento tutte
 * le classi con il controller attraverso diversi metodi costruiti
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
	 * Questo costruttore effettua al primo avvio dell'applicazione, collegandosi in rete, il download e il parsing dei dati 
	 * per l'estrazione dei valori del file csv 
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
	public Vector<Map> getMetadata()
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
	/** questo Metodo gestisce le statistiche, verificando se l'attributo passato è di tipo numerico o string
	 * verifica se l'attributo è presente
	 * grazie all'uso del metodo equalsIgnoreCase è possibile inserire nella richiesta dell'attributo sia lettere maiscole che minuscole
	 * senza creare errori riguardanti Case Sensitive
	 * @see#nomeCampo.equalsIgnoreCase()
	 * @param nomeCampo
	 * @return mappa delle statistiche 
	 */
	public Map<String, Object> getStatistiche(String nomeCampo) 
	{
		Map<String, Object> mappa = new HashMap<>();
		Map<String, Object> Errore = new HashMap<>();
		Errore.put("ATTEZIONE", "NON VI SONO STATISTICHE SULL'ATTRIBUTO INSERITO");
		Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
		for (Field f : fields) 
		{
			if(nomeCampo.equalsIgnoreCase(f.getName())) 
				mappa = serviceStatistiche.getStatistiche(nomeCampo, fieldValues(nomeCampo, getData()));
			else if(nomeCampo.equalsIgnoreCase(f.getName()))
				mappa = serviceStatistiche.getStatistiche(nomeCampo, fieldValues(nomeCampo, lista));
			
		}
		if(mappa.isEmpty()) return Errore;
		else 
		return mappa;
	}
	/**
	 * Questo metodo estrae i valori di un determinato campo, passato tramite fieldName 
	 * verifica se è presente nel vettore dei campi e se risulta TRUE aggiunge il valore dell'oggetto della lista
	 * @param fieldName nome del campo del file CSV
	 * @param list lista che si ottiene dopo aver effettuato il parsing, array di oggetti "EuropeanInformationSociety"
	 * @return la lista che contiene i valori di un determinato campo
	 */
	public List fieldValues(String fieldName, List list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
			for(Object e : list) {
				// controlla se è presente l'attributo  
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equalsIgnoreCase(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object val = m.invoke(e);
						values.add(val); //lo aggiunge alla lista
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
	
	

