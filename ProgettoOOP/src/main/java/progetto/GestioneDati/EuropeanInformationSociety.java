package progetto.GestioneDati;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;

//classe utile per modellare le righe nel file CSV

public class EuropeanInformationSociety implements Serializable {

	private Integer Time_period;
	private String Ref_area, Indicator, Breakdown, Unit_measure;
	private Double Value;

	/**
	 * Costruttori di oggetti della classe EuropeanInformationSociety
	 * 
	 * @param time_period
	 * @param ref_area
	 * @param indicator
	 * @param breakdown
	 * @param unit_measure
	 * @param value
	 */

	public EuropeanInformationSociety(Integer time_period, String ref_area, String indicator, String breakdown,
			String unit_measure, Double value) {
		super();
		this.Time_period = time_period;
		this.Ref_area = ref_area;
		this.Indicator = indicator;
		this.Breakdown = breakdown;
		this.Unit_measure = unit_measure;
		this.Value = value;

	}
//implementazioni getters e setters

	/** @return time_period */
	public Integer getTime_period() {
		return Time_period;
	}

	/** @param time_period */
	public void setTime_period(Integer time_period) {
		this.Time_period = time_period;
	}

	/** @return ref_area */
	public String getRef_area() {
		return Ref_area;
	}

	/** @param ref_area */
	public void setRef_area(String ref_area) {
		this.Ref_area = ref_area;
	}

	/** @return indicator */
	public String getIndicator() {
		return Indicator;
	}

	/** @param indicator */
	public void setIndicator(String indicator) {
		this.Indicator = indicator;
	}

	/** @return breakdown */
	public String getBreakdown() {
		return Breakdown;
	}

	/** @param breakdown */
	public void setBreakdown(String breakdown) {
		this.Breakdown = breakdown;
	}

	/** @return unit_measure */
	public String getUnit_measure() {
		return Unit_measure;
	}

	/** @param unit_measure */
	public void setUnit_measure(String unit_measure) {
		this.Unit_measure = unit_measure;
	}

	/** @return value */
	public Double getValue() {
		return Value;
	}

	/** @param value */
	public void setValue(Double value) {
		this.Value = value;
	}

	/** @return una stringa che rappresenta l'oggetto */
	@Override
	public String toString() {
		return "EuropeanInformationSociety [time_period=" + Time_period + ",ref_area=" + Ref_area + ",indicator="
				+ Indicator + ",breakdown=" + Breakdown + ",unit_measure=" + Unit_measure + ",value=" + Value + " ]";
	}

}
