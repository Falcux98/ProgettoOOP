package Tool;

//Classe per instanziare gli oggetti conteneti statistiche numeriche
public class NumStatistiche 
{
	double avg, min, max, devStd, sum;
	int count;
	
	/**
	 * Costruttore
	 * 
	 * @param avg
	 * @param min
	 * @param max
	 * @param devStd
	 * @param sum
	 * @param count
	 */
		public NumStatistiche(double avg, double min, double max, double devStd, double sum, int count)
	{
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.devStd = devStd;
		this.sum = sum;
		this.count = count;
	}
	/**@return avg*/	
	public double getAvg()
	{
		return avg;
	}
	/**@param avg*/	
	public void setAvg(double avg)
	{
		this.avg = avg;
	}
	/**@return min*/	
	public double getMin()
	{
		return min;
	}
	/**@param min*/	
	public void setMin(double min)
	{
		this.min= min;
	}
	/**@return max*/	
	public double getMax()
	{
		return max;
	}
	/**@param max*/	
	public void setMax(double max)
	{
		this.max = max;
	}
	/**@return devStd*/	
	public double getDevStd()
	{
		return devStd;
	}
	/**@param devStd*/	
	public void setDevStd(double devStd)
	{
		this.devStd = devStd;
	}
	/**@return sum*/	
	public double getSum()
	{
		return sum;
	}
	/**@param sum*/	
	public void setSum(double sum)
	{
		this.sum = sum;
	}
	/**@return count*/	
	public int getCount()
	{
		return count;
	}
	/**@param count*/	
	public void setCount(int count)
	{
		this.count = count;
	}
	
/**@return una stringa che rappresenta l'oggetto*/	
@Override
public String toString()
{
	return "Statistiche numeriche [avg="+avg+", min="+min+",max ="+max+",devStd="+devStd+",sum="+sum+",count="+count+ "]";
}
	
}
