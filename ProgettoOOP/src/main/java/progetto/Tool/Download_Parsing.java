package progetto.Tool;


import java.io.BufferedOutputStream; 
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.nio.file.FileAlreadyExistsException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import progetto.GestioneDati.EuropeanInformationSociety;

public class Download_Parsing 
{
	private List<EuropeanInformationSociety> europeanList;
	private String urlD="";
	
public String dowload(String url)
{
		
	
		
		try {
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			 
			String data = "";
			String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );//leggei i byte e li decodifica
			   BufferedReader buf = new BufferedReader(inR);//legge un file di testo
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject)obj.get("result");
			JSONArray objA = (JSONArray) (objI.get("resources"));
		int i=2;
		do {
				JSONObject o = (JSONObject)objA.get(i);
				if ( o instanceof JSONObject )
			    {
			        JSONObject o1 = (JSONObject)o;
			      
			        String format = (String)o1.get("format");
			        
			      
			        if(format !=null)
			        {	System.out.println( "entra nell'if" );
			        	
			        		urlD = (String)o1.get("url");
			        
			        } 
			    }
			}while(objA.size()==i);
			System.out.println( "Download eseguito!" );	
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlD;
	}

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
	return europeanList;
	
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


