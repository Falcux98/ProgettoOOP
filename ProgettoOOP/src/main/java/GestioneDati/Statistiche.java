package GestioneDati;

import java.util.*;

public class Statistiche
{
	public static double getAvg(Vector<Double> lista)
	{
		double avg;
		avg=(getSum(lista)/lista.size());
		return avg;
	}
	public static double getMin(Vector<Double> lista)
	{
		double min = lista.get(0);
		for(int i=1; i<lista.size(); i++)
		{
			if(min >lista.get(i))
			{
				min = lista.get(i);
			}
		}
		return min;
	}
public static double getMax(Vector<Double> lista)
{
	double max = lista.get(0);
	for(int i=1; i<lista.size(); i++)
	{
		if(max <lista.get(i))
		{
			max=lista.get(i);
		}
	}
	return max;
}
public static double getDevStd(Vector<Double> lista)
{
	double avg = getAvg(lista);
	double sommaScarti = 0;
	for(double elemento: lista)
	{
		sommaScarti += Math.pow(elemento-avg, 2);
	}
	double devStd = Math.sqrt(sommaScarti/lista.size());
	return devStd;
}

public static double getSum(Vector<Double> lista)
{
	double sum=0;
	for(double elemento: lista)
		sum+=elemento;
	return sum;
}
public static Vector<StrStatistiche> getStringStatistiche(Vector<String> str)
{
	Vector<StrStatistiche> stringStat = new Vector<StrStatistiche>();
	String stringa = null;
	int ripSupporto = 0;
	StrStatistiche supporto;
	Boolean flagPresenza = null;
	for(int i=0; i <str.size(); i++)
	{
		flagPresenza = false;
		stringa = str.get(i);
		ripSupporto = 1;
		
		for(int j=0; j<stringStat.size() && !flagPresenza;j++)
			if(stringa.equals(stringStat.get(j).getStringa()))
			{
				flagPresenza = true;
				ripSupporto= stringStat.get(j).getRip()+1;
				supporto = new StrStatistiche(stringa,ripSupporto);
				stringStat.set(j, supporto);
			}
	}
if(!flagPresenza)
{
	supporto = new StrStatistiche(stringa, ripSupporto);
	stringStat.add(supporto);
}


return stringStat;
}
}

