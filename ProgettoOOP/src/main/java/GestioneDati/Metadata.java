package GestioneDati;

import java.io.Serializable;
import java.lang.reflect.*;
/*classe che serve per definire un oggetto Metadata per ottenere informazioni sugli attributi
 * della classe EuropeanInformationSociety, ovvero il nome e il tipo di attributo
 */

public class Metadata implements Serializable
{	
	
	
	public static final long serialVersionUID = 1L;
	private String name;
	private String type;
	
	/**@param name
	 * @param type
	 *
	 */
	public Metadata (String name, String Type)
	{
		this.name = name;
		this.type = type;
	}
	/**@return name*/
	public String getName()
	{
		return name;
	}
	/**@param name*/
	public void setName(String name)
	{
		this.name = name;
	}
	/**@return type*/
	public String getType()
	{
		return type = type;
	}
	/**@param type*/
	public void setType(String type)
	{
		this.type = type;
	}
/**@return una stringa che rappresenta l'oggetto*/
@Override
public String toString()
{
	return "Metadata [name="+name+", type="+type+"]";
}

}

