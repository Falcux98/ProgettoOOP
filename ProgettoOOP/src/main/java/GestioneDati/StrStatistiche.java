package GestioneDati;

public class StrStatistiche 
{
	private String stringa;
	private int rip;
	
	public StrStatistiche(String stringa,int rip)
	{
		this.stringa= stringa;
		this.rip = rip;
	}
	
	public String getStringa()
	{
		return stringa;
	}
	public void setStringa(String stringa )
	{
		this.stringa= stringa;
	}
	public int getRip()
	{
		return rip;
	}
	public void setRip(int rip)
	{
		this.rip= rip;
	}
	
	@Override
	public String toString()
	{
		return "Statistiche stringhe [Stringa= "+stringa+", rip="+rip+"]";
	}
}
