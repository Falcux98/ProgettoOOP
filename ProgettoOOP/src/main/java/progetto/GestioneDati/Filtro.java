package progetto.GestioneDati;

import java.util.*; 
import java.lang.reflect.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/*Classe che definisce un oggeto filtro con un metodo che implementa sei tipi di filtri e un metodo che verifica
 * se il filtro immesso e' valido o meno
 */

public class Filtro 
{
	private Object rif;
	private String op;
	private String fieldName;
	
	/**
	 * Metodo che controlla se l'oggetto passato Ã¨ un numero, una stringa o una lista e confronta tramite gli operatori logici e condizionali i parametri val e rif
	 * @param val valore da controllare del dataset
	 * @param op operatore preso in considerazione
	 * @param rif valore di riferimento passato durante la richiesta
	 * @return valore booleano
	 */
	public static boolean check(Object val, String op, Object rif) {
		
		// casting variabili
		
		if(rif instanceof Number && val instanceof Number) {
			Double rifN = ((Number)rif).doubleValue();
			Double valN = ((Number)val).doubleValue();
			if(op.equals("&eq"))
					return valN.equals(rifN);
				else if(op.equals("&not"))
							return !valN.equals(rifN);
						else if (op.equals("&gt"))
									return valN > rifN;
								else if (op.equals("&gte"))
											return valN >= rifN;
										else if (op.equals("&lt"))
													return valN < rifN;
												else if (op.equals("&lte"))
															return valN <= rifN;
														else {
															throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operatore non valido.");
														}	
		}
		
		else if(rif instanceof String && val instanceof String) {
			String rifS = ((String)rif);
			String valS = ((String)val);
			if(op.equals("&eq"))
					return valS.equals(rifS);
				else if(op.equals("&not"))
							return !(valS.equals(rifS));
						else {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operatore non valido.");
						}
		}
		
		else if(rif instanceof List && val instanceof String) {
			List rifL = (List)rif;
			String valS = ((String)val);
			if(!rifL.isEmpty() && rifL.get(0) instanceof String) {
				List<String> stringList = new ArrayList<>();  // crea una nuova lista di stringhe
				for(Object str : rifL) {
					stringList.add((String)str);  // scorre un ciclo for per effettuare il casting su ogni elemento della lista
				}
				// operatori utilizzati
				if(op.equals("&in"))
						return stringList.contains(valS);
					else if(op.equals("&nin"))
								return !stringList.contains(valS);
							else {
								throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operatore non valido.");
							}
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La lista potrebbe essere vuota o contenere elementi non validi.");
			}
		}
		
		else if(rif instanceof List && val instanceof Number) {
			List rifL = (List)rif;
			Double valN = ((Number)val).doubleValue();
			if(!rifL.isEmpty() && rifL.get(0) instanceof Number) {
				List<Double> numberList = new ArrayList<>();  // crea una nuova lista di numeri
				for(Object num : rifL) {
					numberList.add(((Number)num).doubleValue());  // scorre un ciclo for per effettuare il casting su ogni elemento della lista
				}
				if(op.equals("&in"))
						return numberList.contains(valN);
					else if(op.equals("&nin"))
							return !numberList.contains(valN);
							else if(op.equals("&bt")) {
								double sup = numberList.get(1);
								double inf = numberList.get(0);
								return valN <= sup && valN >=inf;
							}
							else {
								throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operatore non valido.");
							}
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La lista potrebbe essere vuota o contenere elementi non validi.");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Elementi non validi.");
		}
	}
	
	/**
	 * Metodo che aggiunge alla lista solo i valori del dataset che soddisfano l'operazione richiesta
	 * 
	 * @param src contiene la lista del dataset
	 * @param fieldName contiene il nome del campo del quale si si vuole applicare il filtro
	 * @param op contiene l'operatore che effettua il controllo
	 * @param rif contiene il valore del riferimento
	 * @return
	 */
	
	public List<EuropeanInformationSociety> select(List<EuropeanInformationSociety> src, String fieldName, String op, Object rif) {
		List<EuropeanInformationSociety> list = new ArrayList<EuropeanInformationSociety>();
		for(EuropeanInformationSociety obj : src) {
			
			try {
				
				Field[] fields = EuropeanInformationSociety.class.getDeclaredFields();
				Object tmp = null;
				// fa scorrere il vettore dei campi ed estrae i relativi metodi invocandoli poi con il metodo invoke
					for(int i=0; i<fields.length; i++) {
						if(fields[i].getName().equals(fieldName)) {
						Method m = obj.getClass().getMethod("get" + fields[i].getName());
								tmp = m.invoke(obj); // assegna ad una variabile temporanea il valore estratto dal metodo getter invocato con invoke
						if(Filtro.check(tmp, op, rif))
							list.add(obj);  // aggiunge l'oggetto alla lista solo se soddisfa la condizione imposta dall'operatore
							}
						
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();	
			}catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// metodi getter della classe
	
	public Object getRif() {
		return rif;
	}
	
	public String getOp() {
		return op;
	}
	
	public String getFieldName() {
		return fieldName;
	}
}