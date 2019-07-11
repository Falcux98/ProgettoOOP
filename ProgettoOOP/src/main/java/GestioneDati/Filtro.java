package GestioneDati;

import java.util.*;
import java.lang.reflect.*;

public class Filtro 
{
	private String modelloFiltro;
	public Filtro(String modelloFiltro)
	{
		this.modelloFiltro = modelloFiltro;
	}
	
	public Object Research (String attributo, String dato1, String dato2, Vector <EuropeanInformationSociety> listaInput)
	{
		boolean isNumber = true;
		Vector <EuropeanInformationSociety> listaOutput = new Vector <EuropeanInformationSociety>();
		Method metodo;
		Double datoDouble1=0.0;
		Double datoDouble2=0.0;
	
	try
		{
			metodo = EuropeanInformationSociety.class.getMethod("get"+attributo.substring(0,1).toUpperCase()+ attributo.substring(1));
			
		}
	catch(NoSuchMethodException e)
	{
		e.printStackTrace();
		return "Errore, l'attributo inserito non esiste";
	}
	
	try
		{
			datoDouble1 = Double.parseDouble(dato1);
			datoDouble2 = Double.parseDouble(dato2);
		}
	catch(NumberFormatException e)
		{
			isNumber = false;
		}
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
	
public boolean isExist() {
		
		if(modelloFiltro.equals("$eq") ||modelloFiltro.equals("$or") || modelloFiltro.equals("$not") || modelloFiltro.equals("$gt") || modelloFiltro.equals("$lt") || modelloFiltro.equals("$bt"))
			return true;
		else
			return false;
		
	}
	
	
	
}