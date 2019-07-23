package progetto.GestioneDati;

import java.io.*; 
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;


//classe utile per modellare le righe nel file CSV


public class EuropeanInformationSociety implements Serializable
{
	
	private Integer time_period;
	private String ref_area, indicator, breakdown, unit_measure;
	private Double value;
	
/** Costruttori di oggetti della classe EuropeanInformationSociety
 * 
 * @param time_period
 * @param ref_area
 * @param indicator
 * @param breakdown
 * @param unit_measure
 * @param value
 */

	
public EuropeanInformationSociety(Integer time_period, String ref_area, String indicator, String breakdown, String unit_measure, Double value)
{
	super();
	this.time_period = time_period;
	this.ref_area = ref_area;
	this.indicator = indicator;
	this.breakdown = breakdown;
	this.unit_measure = unit_measure;
	this.value = value;
	
}
//implementazioni getters e setters

/**@return time_period*/
public Integer getTime_period()
{
	return time_period;
}
/**@param time_period*/
public void setTime_period(Integer time_period)
{
	this.time_period = time_period;
}
/**@return ref_area*/
public String getRef_area()
{
	return ref_area;
}
/**@param ref_area*/
public void setRef_area(String ref_area)
{
	this.ref_area = ref_area;
}
/**@return indicator*/
public String getIndicator()
{
	return indicator;
}
/**@param indicator*/
public void setIndicator(String indicator)
{
	this.indicator= indicator;
}
/**@return breakdown*/
public String getBreakdown()
{
	return breakdown;
}
/**@param breakdown*/
public void setBreakdown(String breakdown)
{
	this.breakdown = breakdown;
}
/**@return unit_measure*/
public String getUnit_measure()
{
	return unit_measure;
}
/**@param unit_measure*/
public void setUnit_measure(String unit_measure)
{
	this.unit_measure = unit_measure;
}
/**@return value*/
public Double getValue()
{
	return value;
}
/**@param value*/
public void setValue(Double value)
{
	this.value = value;
}
/**@return una stringa che rappresenta l'oggetto*/
@Override
public String toString()
{
	return  "EuropeanInformationSociety [time_period="+ time_period +",ref_area="+ ref_area +",indicator="+ indicator +",breakdown="+ breakdown +",unit_measure="+ unit_measure +",value="+ value + " ]";
}


}






	

