package GestioneDati;

import java.io.Serializable;


public class Metadata implements Serializable
{
	public static final long serialVersionUID = 1L;
	private String name;
	private String type;
	
	public Metadata (String name, String Type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getType()
	{
		return type = type;
	}
	public void setType(String type)
	{
		this.type = type;
	}

@Override
public String toString()
{
	return "Metadata [name="+name+", type="+type+"]";
}

}

