package progetto.GestioneDati;

import java.io.Serializable; 
import java.lang.reflect.*;
import java.util.*;
/*classe che serve per definire un oggetto Metadata per ottenere informazioni sugli attributi
 * della classe EuropeanInformationSociety, ovvero il nome e il tipo di attributo
 */

public class Metadata implements Serializable
{	
	private List<Map> metadata = new Vector<>();
	
	public Metadata()
	{
		Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();//estrae gli attributi della classe
		
		for(Field f: fields) {
			Map<String, String> map = new HashMap<>(); //viene inserita la coppia chiave/valore
			map.put("name",f.getName());
			map.put("sourceFiled", f.getName().toUpperCase());
			map.put("type",f.getType().getSimpleName());
			metadata.add(map);
		}
	
	
	}
/**Metodo che ritorna la lista di mappe contenente i metadati
 * @return lista dei metadati*/

public List<Map> getMetadata()
{
	return metadata;
}

}

