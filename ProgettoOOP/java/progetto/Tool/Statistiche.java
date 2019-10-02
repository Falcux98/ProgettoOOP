package progetto.Tool;

import java.util.*;
//Classe che contiene i metodi per calcolare le statistiche sui dati
public class Statistiche
{	
	/**
	 * Metodo che effettua un conteggio sul numero di elementi di una lista
	 * 
	 * @param lista contiene i valori da contare
	 * @return dimensione della lista
	 */
	public static int count(List lista) {
		return lista.size();
	}
	
	/**
	 * Metodo che restituisce la somma dei valori di una lista numerica
	 * 
	 * @param lista contiene i valori che andranno sommati
	 * @return somma dei valori
	 */
	public static double sum(List<Number> lista) {
		double somma=0;
		for(Number numero : lista)
			somma += numero.doubleValue();   //scorre la lista e somma tutti i valori
		return somma;
	}
	/**
	 * Metodo che restituisce la media dei valori di una lista numerica
	 * 
	 * @param lista contiene i valori utilizzati per calcolare la media
	 * @return media dei valori
	 */
	public static double avg(List<Number> lista)
	{
		return sum(lista)/count(lista); //media
	}
	/**
	  * Metodo che trova il valore massimo e lo restituisce
	  * 
     * @param lista
     * 
     * @return max
     */

public static double max(List<Number> lista)
{
	double max = lista.get(0).doubleValue();
	for(Number numero : lista)
	{
		if(numero.doubleValue()> max) max= numero.doubleValue();
		
	}
	return max;
}
/**
 * Metodo che restituisce il minimo tra il valori di una lista numerica
 * 
 * @param lista contiene i valori dai quali si estrarrï¿½ il minimo
 * @return minimo dei valori
 */
public static double min(List<Number> lista) {
	double min = lista.get(0).doubleValue();  //assegna il primo valore della lista a min
	for(Number numero : lista) {
		if(numero.doubleValue() < min) min = numero.doubleValue();  //scorre la lista e verifica se il valore dell'indice corrispondente ï¿½ minore di min
	}
	return min;
}

/**
 * Metodo che calcola la deviazione standard e la restituisce
 * 
 * @param lista  contiene i valori con i quali si calcolera' la deviazione standard
 * 
 * @return devStd
 */

public static double devStd(List<Number> lista)
{
	double somma=0;
	for(Number numero : lista) {
		somma += Math.pow(numero.doubleValue() - avg(lista), 2);
	}
	return Math.sqrt(somma);
}

/**
 * Metodo che conta le occorrenze di un elemento all'interno di una lista
 * 
 * @param lista contiene i valori per i quali si vogliono calcolare le occorrenze
 * @return restituisce una map chiave-valore dove le chiavi sono gli elementi della lista e i valori le corrispondenti occorrenze
 */
public static Map<Object, Integer> ElementiUnici(List lista) {
	Map<Object,Integer> mappa = new HashMap<>();  //creazione della mappa
	for(Object obj : lista) {  //scorre la lista
		if(mappa.containsKey(obj))  //controlla se la chiave esiste giï¿½
			mappa.replace(obj, mappa.get(obj) + 1);  //se esiste aumenta il suo valore di 1
		else
			mappa.put(obj, 1);  //se non esiste la crea e le assegna il valore 1
	}
	return mappa;
}

/**
 * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche numeriche di una lista 
 * 
 * @param lista fornisce i valori con i quali si possono calcolare tutte le statistiche
 * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
 */
public static Map<String, Object> NumStatistiche(String campo, List<Number> lista) {
	Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche numeriche
	maps.put("field", campo);
	maps.put("count", count(lista));
	maps.put("sum", sum(lista));
	maps.put("avg", avg(lista));
    maps.put("max", max(lista));
    maps.put("min", min(lista));
    maps.put("DevStd", devStd(lista));
    return maps;
}

/**
 * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche non numeriche di una lista 
 * 
 * @param lista fornisce i valori con i quali si possono calcolare tutte le statistiche non numeriche
 * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
 */
public static Map<String, Object> StrStatistiche(String campo, List<Object> lista) {
	Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche non numeriche
	maps.put("field", campo);
	maps.put("count", count(lista));
	maps.put("elementi unici", ElementiUnici(lista));
    return maps;
}

/**
 * Metodo che serve a visualizzare il tipo di statistiche in base al campo specificato
 * 
 * @param campo contiene il nome dell'attributo del quale si vogliono si vogliono calcolare le statistiche 
 * @param lista contiene la lista dei valori utili per il calcolo delle statistiche
 * @return
 */

public Map<String, Object> getStatistiche(String campo, List<Object> lista) {
	Map<String, Object> maps = new HashMap<>();
	if(!lista.isEmpty()) {
		 // se il primo valore e' un numero crea una lista di numeri e gli passa i valori della lista castati a Number
		if (lista.get(0) instanceof Number) { 
			List<Number> numLista = new Vector<>();
			for (Object elem : lista) {
				numLista.add(((Number) elem));
			}
			maps = NumStatistiche(campo, numLista); // calcola le statistiche numeriche
		}
		// se il primo valore non e' un numero calcola le statistiche per le stringhe
		else {
			maps = StrStatistiche(campo, lista);
		}
	}
	return maps;
}
}

