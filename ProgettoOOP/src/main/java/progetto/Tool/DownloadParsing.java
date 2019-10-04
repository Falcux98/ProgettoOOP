package progetto.Tool;

/**
 * Classe che racchiude i metodi per il download e il parsing del file csv
 */
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.nio.file.FileAlreadyExistsException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import progetto.GestioneDati.EuropeanInformationSociety;

public class DownloadParsing 
{
	private Vector<EuropeanInformationSociety> europeanList;
	private String urlD="";
	
	public DownloadParsing() {
		this.europeanList = new Vector<EuropeanInformationSociety>();
		
	}
	/**
	 * Questo metodo che effettua il download del dataset estraendo il link del csv 
	 * attraverso il canale di connesione
	 * 
	 * @param url stringa che contiene l'url del dataset assegnatoci
	 * @return ritorna una stringa contenuta il link urlD del csv
	 */
	
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
			   }
			 } finally {
			   in.close();
			 }
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject)obj.get("result");
			JSONArray objA = (JSONArray) (objI.get("resources"));
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o; 
			        String format = (String)o1.get("format");			        
					if(format !=null && format.equals("http://publications.europa.eu/resource/authority/file-type/CSV"))
					{	
						urlD = (String)o1.get("url");					
						break;				
					}
				}
			}						
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*Download Eseguito!*\n");
		return urlD;
	}

/**
 * Questo metodo che effettua il parsing del file csv estratto precedentemente con il  metodo download
 * usando un delimitatore per separare i valori presenti nel csv
 * 
 * @param urlD contiene l'url del csv
 * @return lista di oggetti EuropeanInformationSociety con i relativi attributi contenenti i valori del dataset
 */

public  Vector<EuropeanInformationSociety> parsing (String urlD)
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
			try{
				System.out.println("###" + linea);
				if(iterazione==0)
				{
					iterazione++;
					continue;		
				}
				linea = linea.replace("\"","");
				String[] values = linea.split(",");
				 
				if(values.length == 6){
					for (int i =0 ; i<values.length; i++)
					{					
						values[i] = values[i].replace(',','.').replace("n.d.", "0");	
					}
				}
				europeanList.add(new EuropeanInformationSociety(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], Double.parseDouble(values[5])));
			}catch(Exception e){
				System.out.println(e);
			}			
		}
	
		br.close();
		}
	
	catch (IOException i)
	{
		i.printStackTrace();
	}
	System.out.println("*Parsing eseguito!*\n");
	for(EuropeanInformationSociety item: europeanList)
	{
		System.out.println(item);
	}
	return europeanList;
	
}

/**
 * metodo che restituisce la lista 
 * @return europeanList
 */

public Vector<EuropeanInformationSociety> getData()
{
	return europeanList;
}
}
