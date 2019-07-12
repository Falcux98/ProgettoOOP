package progetto;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import GestioneDati.*;

import org.springframework.web.bind.annotation.*;


public class Controller 
{
	@RequestMapping(value="/metadata", method=RequestMethod.GET)
	public Vector<Metadata> MetadataRequest()
	{
		return Lettura.LetturaMetadati(new File("metadata file.dat"));
	}
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
	
	@RequestMapping(value="/statistics", method = RequestMethod.GET)
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
