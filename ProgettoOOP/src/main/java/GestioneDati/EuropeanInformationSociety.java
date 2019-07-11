package GestioneDati;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@interface Metadati
{
	String name();
	String type();
}

//classe utile per modellare le righe nel file CSV


public class EuropeanInformationSociety implements Serializable
{
	@Metadati(name="time_period", type="int")
	private int time_period;
	
	@Metadati(name="ref_area", type="String")
	private String ref_area;
	
	@Metadati(name="indicator", type="String")
	private String indicator;
	
	@Metadati(name="breakdown", type="String")
	private String breakdown;
	
	@Metadati(name="unit_measure", type="String")
	private String unit_measure;
	
	@Metadati(name="value", type="Double")
	private Double value;
	
/* Costruttori di oggetti della classe EuropeanInformationSociety
 * 
 * @param time_period
 * @param ref_area
 * @param indicator
 * @param breakdown
 * @param unit_measure
 * @param value
 */

	
public EuropeanInformationSociety(int time_period, String ref_area, String indicator, String breakdown, String unit_measure, Double value)
{
	super();
	this.time_period = time_period;
	this.ref_area = ref_area;
	this.indicator = indicator;
	this.breakdown = breakdown;
	this.unit_measure = unit_measure;
	this.value = value;
}


public int getTime_period()
{
	return time_period;
}
public void setTime_period(int time_period)
{
	this.time_period = time_period;
}

public String getRef_area()
{
	return ref_area;
}

public void setRef_area(String ref_area)
{
	this.ref_area = ref_area;
}

public String getIndicator()
{
	return indicator;
}

public void setIndicator(String indicator)
{
	this.indicator = indicator;
}

public String getBreakdown()
{
	return breakdown;
}

public void setBreakdown(String breakdown)
{
	this.breakdown = breakdown;
}

public String getUnit_measure()
{
	return unit_measure;
}

public void setUnit_measure(String unit_measure)
{
	this.unit_measure = unit_measure;
}

public Double getValue()
{
	return value;
}

public void setValue(Double value)
{
	this.value = value;
}

@Override
public String toString()
{
	return  "EuropeanInformationSociety [time_period="+ time_period +",ref_area="+ ref_area +",indicator="+ indicator +",breakdown="+ breakdown +",unit_measure="+ unit_measure +",value="+ value + " ]";
}


}






	

