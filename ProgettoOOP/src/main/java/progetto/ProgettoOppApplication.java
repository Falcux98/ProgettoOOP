package progetto;

import java.io.*;
import java.util.*;
import org.springframework.boot.SpringApplication;
import GestioneDati.Download;
import GestioneDati.EuropeanInformationSociety;
import GestioneDati.Metadata;
import GestioneDati.Parsing_Serializzazione_Dati;
import GestioneDati.Parsing_Serializzazione_Metadati;



public class ProgettoOppApplication
{
	public static void main(String[] args)
	{
		Download.ScanURL();
		
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
	
	
	SpringApplication.run(ProgettoOppApplication.class, args);
	}
	
	
}