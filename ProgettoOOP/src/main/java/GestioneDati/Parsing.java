package GestioneDati;

import java.io.*;
import java.net.URL;
import java.util.*;
import Tool.*;

//Classe che gestisce il parsing e la serializzazione dei dati

public class Parsing 
{
	public  List<EuropeanInformationSociety> europeanList;
	/** Metodo static che si occupa del Parsing dei dati a partire dal file letto.
	 * @return List di oggetti di tipo EuropeanInformationSociety*/

	public  List <EuropeanInformationSociety> parsing (String urlD)
	{
		/* 
		 * Il delimitatore usato è rappresentato da una virgola racchiusa tra virgolette
		 * così da non provocare errori nel parsing dei dati e metadati
		 */
	
		boolean flag1= false, flag2 =false;
		String linea;
		int iterazione=0 ;
		try		
		{
			URL urlCsv = new URL (urlD);
			BufferedReader br = new BufferedReader(new InputStreamReader(urlCsv.openStream()));
			
			
			
			while (((linea =br.readLine()) != null) && !flag2)
			{
				if(iterazione==0)
				{
					iterazione++;
					continue;		
				}
				
				String[] values = linea.split(","); //"\"\",\""
				 
				//System.out.println("La lunghezza dell'array e' "+values.length);
				
				for (int i =0 ; i<6; i++)
				{
					System.out.println("entra nel ciclo");
					values[i] = values[i].replace(',','.').replace("n.d.", "0").replace("\"","");
					
				}
				
			europeanList.add(new EuropeanInformationSociety(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], Double.parseDouble(values[5])));
				

			}
		
			br.close();
			}
		
	
		catch (IOException i)
		{
			i.printStackTrace();
		}
		System.out.println("Parsing eseguito!\n");
		for(EuropeanInformationSociety item: europeanList)
		{
			System.out.println(item);
		}
		
	}
	
	/**
	 * Metodo static che si occupa della serializzazione dei dati dal Vector di oggetti di tipo EuropeanInformationSociety
	 * @param file	File su cui vengono serializzati gli oggetti
	 * @param data	Vector di EuropeanInformationSociety da dove vengono presi i dati
	 */
	
	public List<EuropeanInformationSociety> getData()
	{
		return europeanList;
	}
}