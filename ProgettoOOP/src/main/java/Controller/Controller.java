package Controller;

import java.io.*; 
import java.lang.reflect.*;
import java.util.*;
import GestioneDati.*;
import Tool.*;


import org.springframework.web.bind.annotation.*;

/*La classe controller gestisce le varie funzionalità API REST fornite da Spring boot
 * questa classe implementa i metodi di richiesta dei metadati, dei dati e delle statistiche*/
 
 @RestController

public class Controller 
{
	 /**questo metodo serve per la richiesta tramite Get dei metadati.
	  * @return metadati in formato JSON
	  */
	@RequestMapping(value="/metadata", method=RequestMethod.GET)
	public Vector<Metadata> MetadataRequest()
	{
		return Lettura.LetturaMetadati(new File("metadata file.dat"));
	}
	/**questo metodo serve per la richiesta tramite Get dei dati secondo  l'eventuale filtro specificato.
	  * @param filter Filtro specificato nella richiesta, se presente
	  * @param attribute Attributo su cui viene applicato il filtro
	  * @param value1 valore primario utilizzato nel filtraggio
	  * @param value2 Valore secondario utilizzato nel filtraggio(only $or e $bt)
	  * @return dati in formato JSON (o errori)
	  */
	@RequestMapping(value="/data", method = RequestMethod.GET)
	public Object DataRequest(@RequestParam(value="filter", defaultValue="vuoto")String filter,
			@RequestParam(value="attribute", defaultValue = "vuoto")String attribute, String value1,
			@RequestParam(value="value2", defaultValue="0")String value2)
	{
		Vector<EuropeanInformationSociety> v = Lettura.LetturaDati(new File ("data file.dat"));
		if(attribute.equals("vuoto"))
		{
			return v;
		}
		else
		{
			Vector<Metadata> metadata = Lettura.LetturaMetadati(new File("metadata file.dat"));
			boolean[] checkAttribute = new boolean[metadata.size()];
			for(int i=0; i<metadata.size(); i++)
			{
				if(!attribute.contentEquals(metadata.get(i).getName()))
				{
					checkAttribute[i] = true;
				}
				else
				{
					checkAttribute[i]= false;
				}
			}
			boolean isNotAttribute = true;
			for(int i=0; i<metadata.size();i++)
			{
				isNotAttribute &= checkAttribute[i];
			}
			if(isNotAttribute)
			{
				return "L'attributo inserito non e' valido";
			}
			if(filter.equals("vuoto"))
				return v;
			else
			{
				Filtro filtro = new Filtro(filter);
				if(!filtro.isExist())
					return "Il filtro usato non esiste!";
				Vector<EuropeanInformationSociety> output= (Vector<EuropeanInformationSociety>)filtro.Research(attribute, value1, value2, v);
				
				if(output.size()==0)
					return "Nessun elemento corrisponde a questa richiesta!";
				else 
					return output;
			}
		}
		
	}
	/**Metodo per la richiesta tramite GET delle statistiche relativeall'attributo, sia di tipo numerico che di tipo
	 * String secondo gli eventuali filtri
	 * @param filter filtro specificato nella richiesta(se presente)
	 * @param attribute attributo del quale si vuole conoscere le statistiche
	 * @param value1 valore primario utilizzato nel filtraggio
	 * @param value2 valore secondario """""(only  $or o $bt)
	 * @return statistiche sui dati in formatp JSON( o errori)
	 * 
	 */
	@RequestMapping(value="/statistiche", method = RequestMethod.GET)
	public Object StatisticsRequest(@RequestParam(value="filter",defaultValue="vuoto")String filter,
			String attribute, String value1,@RequestParam(value="value2", defaultValue="0")String value2)
	{
		Vector<Object> attributes = new Vector<Object>();
		try
		{
			Method metodo= EuropeanInformationSociety.class.getMethod("get"+attribute.substring(0,1).toUpperCase()+attribute.substring(1));
			Vector<EuropeanInformationSociety> v = Lettura.LetturaDati(new File("data file.dat"));
					if(filter.equals("vuoto"))
					{
						for(EuropeanInformationSociety obj: v)
						{
							attributes.add(metodo.invoke(obj));
						}
					}
					else
					{
						Filtro filtro = new Filtro(filter);
						if(!filtro.isExist())
							return "Il filtro usato non esiste!";
						Vector<EuropeanInformationSociety> vFiltered = (Vector<EuropeanInformationSociety>)filtro.Research(attribute, value1, value2, v);
						
						for(EuropeanInformationSociety obj : vFiltered)
							
						{
							attributes.add(metodo.invoke(obj));
						}
					}
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			return "L'attributo inserito non e' valido";
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
		}
		if(attributes.size()==0)
			return "Nessun elemento corrispondente a questa richiesta";
		if(attributes.get(0) instanceof String)
		{
			Vector<String> vString = new Vector<String>();
			for(Object obj : attributes)
			{
				String string = (String) obj;
				vString.add(string);
			}
			return Statistiche.getStringStatistiche(vString);
			}
		else
		{
			Vector<Double> vNumber = new Vector<Double>();
			for(Object number : attributes)
				vNumber.add((Double)number);
			return new NumStatistiche(Statistiche.getAvg(vNumber), 
					Statistiche.getMin(vNumber), Statistiche.getMax(vNumber),
					Statistiche.getDevStd(vNumber), Statistiche.getSum(vNumber),
					vNumber.size());
		}
	}
}
