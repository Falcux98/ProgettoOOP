package Tool;

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
import java.nio.file.FileAlreadyExistsException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


/*Classe che effettua il download del data-set che contiene dati in formato CSV partendo dall'indirizzo fornito
 * dopo, ovviamente, decodifica del JSON che contiene contiene l'URL per scaricare il file
 */
public class Download
{
	private String urlD="";
	/*Metodo  che scansione il contenuto dell'URL e decodifica il JSON al suo interno, per acquisire l'URL 
	 * che serve per scaricare il dataset
	 */
	public ScanURL (String url) {
		
	
		//String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ";
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
	}
	
}