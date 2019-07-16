package GestioneDati;

import java.util.*;
import java.lang.reflect.*;

/*Classe che definisce un oggeto filtro con un metodo che implementa sei tipi di filtri e un metodo che verifica
 * se il filtro immesso e' valido o meno
 */

public class Filtro 
{
	private String modelloFiltro; //attributo
	/*costruttore
	 * @param modelloFiltro
	 */
	
	public Filtro(String modelloFiltro)
	{
		this.modelloFiltro = modelloFiltro;
	}

	/**
	 * Metodo per fare una ricerca filtrata nella lista di input e restituire una lista filtrata<br>
	 * Filtro $eq  per trovare gli elementi uguali a quello di riferimento<br>
	 * Filtro $not per ritornare tutti gli elementi a parte quelli uguali a quello di riferimento<br>
	 * Filtro $or  per trovare gli elementi uguali almeno a uno dei due valori di riferimento<br>
	 * Filtro $gt  per ritornare solo i valori maggiori di quello di riferimento<br>
	 * Filtro $lt  per ritornare solo i valori minori di quello di riferimento<br>
	 * Filtro $bt  per ritornare solo i valori compresi tra i due valori di riferimento
	 * 
	 * @param attributo   della classe EuropeanInformationSociety
	 * @param dato1       primo valore di riferimento per il filtraggio
	 * @param dato2       secondo valore di riferimento per il filtraggio con gli operatori $or e $bt
	 * @param listaInput  lista di oggetti EuropeanInformationSociety su cui il metodo farà il filtraggio
	 * 
	 * @return una lista filtrata di oggetti EuropeanInformationSociety
	 */

	public Object Research (String attributo, String dato1, String dato2, Vector <EuropeanInformationSociety> listaInput)
	{
		boolean isNumber = true;
		Vector <EuropeanInformationSociety> listaOutput = new Vector <EuropeanInformationSociety>();
		Method metodo;
		Double datoDouble1=0.0;
		Double datoDouble2=0.0;
	
		//verifica che l'attributo immesso sia presente nella classe EuropeanInformationSociety
	try
		{
			metodo = EuropeanInformationSociety.class.getMethod("get"+attributo.substring(0,1).toUpperCase()+ attributo.substring(1));
			
		}
	catch(NoSuchMethodException e)
	{
		e.printStackTrace();
		return "Errore, l'attributo inserito non esiste";
	}
	//converte i valori di riferimento in Double, se essi sono stringhe letterali si entra nel catch
	try
		{
			datoDouble1 = Double.parseDouble(dato1);
			datoDouble2 = Double.parseDouble(dato2);
		}
	catch(NumberFormatException e)
		{
			isNumber = false;
		}
	//LOgical operator $eq
	//La lista in output contiene tutti ivalori della lista in lista in input che corrispondono al valore di riferimento
	if(modelloFiltro.contentEquals("$eq"))
	{
		if(isNumber)
		{	
			try 
				{
					for(EuropeanInformationSociety obj : listaInput)
					{
						if(metodo.invoke(obj) instanceof Double)
						{
							Double temp = (Double)metodo.invoke(obj);
							if(temp.equals(datoDouble1))
							{
								listaOutput.add(obj);
							}
						}
					}
				}
	catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
	catch(InvocationTargetException e)
		{
			e.printStackTrace();
		}
	
	}
	else
	{
		try
		{
			for(EuropeanInformationSociety obj : listaInput)
			{
				if(metodo.invoke(obj) instanceof String)
				{
					String temp = (String)metodo.invoke(obj);
					if(temp.equals(dato1))
					{
						listaOutput.add(obj);
					}
				}
			}
		}
	
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
	catch(InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
}
/*Logical operator $not
 * la lista in output contiene tutti i valori delle lista in input tranne quelli uguali al valore di riferimento
 */
if(modelloFiltro.equals("$not"))
{
	if(isNumber)
	{
		try
		{
			for(EuropeanInformationSociety obj : listaInput)
			{
				if(metodo.invoke(obj) instanceof Double)
				{
					Double temp = (Double)metodo.invoke(obj);
					if(!temp.equals(datoDouble1))
					{
						listaOutput.add(obj);
					}
				}
			}
		}
	catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
	catch(InvocationTargetException e)
		{
		e.printStackTrace();
		}
	}
	else
	{
		try
		{
			for(EuropeanInformationSociety obj : listaInput)
			{
				if(metodo.invoke(obj) instanceof String)
				{
					String temp = (String)metodo.invoke(obj);
					if(!temp.equals(dato1))
					{
						listaOutput.add(obj);
					}
				}
			}
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
	
}
/*Logica operatorn $or
 * La lista in output contiene i volori uguali a uno o all'altro valore di riferimento
 * 	
 */
if(modelloFiltro.equals("$or")) {
	if(isNumber) {
		try {
			 for(EuropeanInformationSociety obj : listaInput) {
				if(metodo.invoke(obj) instanceof Double) {
					Double temp = (Double)metodo.invoke(obj);
					if(temp.equals(datoDouble1) || temp.equals(datoDouble2)) {
						listaOutput.add(obj);
					}
			     }
			 }
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	else {
		try {
			 for(EuropeanInformationSociety obj : listaInput) {
				if(metodo.invoke(obj) instanceof String) {
				   String temp = (String)metodo.invoke(obj);
				   if(temp.equals(dato1) || temp.equals(dato2)) {
						listaOutput.add(obj);
				   }
				}
			 }
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
/*Conditional operator $gt
 * La lista in output contiene tutti i valori maggiori del valore di riferimento
 */
if(modelloFiltro.equals("$gt")) {
	if(isNumber) {
		try {
			for(EuropeanInformationSociety obj : listaInput) {
				if(metodo.invoke(obj) instanceof Double) {
					Double temp = (Double)metodo.invoke(obj);
					if(temp.compareTo(datoDouble1) > 0) {
						listaOutput.add(obj);
					}
				}
			}
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}


//Conditional operator $lt
//La lista in output contiene tutti i valori minori del valore di riferimento
if(modelloFiltro.equals("$lt")) {
	if(isNumber) {
		try {
			for(EuropeanInformationSociety obj : listaInput) {
				if(metodo.invoke(obj) instanceof Double) {
					Double temp = (Double)metodo.invoke(obj);
					if(temp.compareTo(datoDouble1) < 0) {
						listaOutput.add(obj);
					}
				}
			}
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}


//Conditional operator $bt
//La lista in output contiene tutti i valori compresi tra i due valori di riferimento
if(modelloFiltro.equals("$bt")) {
	if(isNumber) {
		try {
			for(EuropeanInformationSociety obj : listaInput) {
				if(metodo.invoke(obj) instanceof Double) {
					Double temp = (Double)metodo.invoke(obj);
					if(temp.compareTo(datoDouble1) >= 0 && temp.compareTo(datoDouble2) <= 0) {
						listaOutput.add(obj);
					}
				}
			}
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
	return listaOutput;	
}
/*@return <strong>true</strong> se il filtro immesso è valido
 * o <strong>false</strong> se non lo e'
 * 	
 */
	
public boolean isExist() {
		
		if(modelloFiltro.equals("$eq") ||modelloFiltro.equals("$or") || modelloFiltro.equals("$not") || modelloFiltro.equals("$gt") || modelloFiltro.equals("$lt") || modelloFiltro.equals("$bt"))
			return true;
		else
			return false;
		
	}
	
	
	
}