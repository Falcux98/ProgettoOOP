package progetto;

import java.io.*; 
import java.util.*;
import org.springframework.boot.SpringApplication;
import GestioneDati.Download;
import GestioneDati.EuropeanInformationSociety;
import GestioneDati.Metadata;
import GestioneDati.Parsing_Serializzazione_Dati;
import GestioneDati.Parsing_Serializzazione_Metadati;


import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication




public class ProgettoOppApplication
{
	/*Metodo static principale dell'applicazione da cui vengono richiamati tutti i metodi necessari.
	 * download del file CSV, parsin e serializzazione dei dati e dei metadati.
	 * inoltre si occupa di lanciare l'applicazione con Spring
	 */
	public static void main(String[] args)
	{
		//download del file CSV
		Download.ScanURL();
		
		//parsing e serializzazione dei dati
		Vector<EuropeanInformationSociety> data = Parsing_Serializzazione_Dati.getData();
		File fileData = new File("data file.dat");
		try
		{
			fileData.createNewFile();
		}
	catch(IOException e)
		{
		e.printStackTrace();
		}
	Parsing_Serializzazione_Dati.SerializzazioneDati(fileData, data);
	
	
	//parsing e serializzazione dei metadati
	
	Vector<Metadata> metadata = Parsing_Serializzazione_Metadati.getMetadata();
	File fileMetadata = new File ("metadata file.dat");
	try
	{
		fileMetadata.createNewFile();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	Parsing_Serializzazione_Metadati.SerializzazioneMetadati(fileMetadata, metadata);
	
	//avvio di Spring
	SpringApplication.run(ProgettoOppApplication.class, args);
	}
	
	
}