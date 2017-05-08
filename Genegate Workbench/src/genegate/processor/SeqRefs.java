package genegate.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Reference class.  Contains static, final attributes used across different classes.
 * 
 * @author Nathaniel Gatewood
 */
public class SeqRefs {
	
	//Create an Unmodifiable List of DNA Bases
	//----------------------------------------
	private static ArrayList<String> dnaBasesM = new ArrayList<String>()
		{{
			add("A");	add("T");	add("G");	add("C");
		}};
			
	public static final List<String> dnaBases = Collections.unmodifiableList(dnaBasesM);
	
	//Create an Unmodifiable Map of the codons
	//----------------------------------------
	private static final Hashtable<String,String> codonsM = new Hashtable<String,String>()
		{{
			put("UUU", "F");	put("CUU", "L");	put("AUU", "I");	put("GUU", "V");
			put("UUC", "F");	put("CUC", "L");	put("AUC", "I");	put("GUC", "V");
			put("UUA", "L");	put("CUA", "L");	put("AUA", "I");	put("GUA", "V");
			put("UUG", "L");	put("CUG", "L");	put("AUG", "M");	put("GUG", "V");
			
			put("UCU", "S");	put("CCU", "P");	put("ACU", "T");	put("GCU", "A");
			put("UCC", "S");	put("CCC", "P");	put("ACC", "T");	put("GCC", "A");
			put("UCA", "S");	put("CCA", "P");	put("ACA", "T");	put("GCA", "A");
			put("UCG", "S");	put("CCG", "P");	put("ACG", "T");	put("GCG", "A");
			
			put("UAU", "Y");	put("CAU", "H");	put("AAU", "N");	put("GAU", "D");
			put("UUC", "Y");	put("CAC", "H");	put("AAC", "N");	put("GAC", "D");
			put("UAA", "*");	put("CAA", "Q");	put("AAA", "K");	put("GAA", "E");
			put("UAG", "*");	put("CAG", "Q");	put("AAG", "K");	put("GAG", "E");
				
			put("UGU", "C");	put("CGU", "R");	put("AGU", "S");	put("GGU", "G");
			put("UGC", "C");	put("CGC", "R");	put("AGC", "S");	put("GGC", "G");
			put("UGA", "*");	put("CGA", "R");	put("AGA", "R");	put("GGA", "G");
			put("UGG", "W");	put("CGG", "R");	put("AGG", "R");	put("GGG", "G");
		}};
		
	public static final Map<String,String> codons = Collections.unmodifiableMap(codonsM);
		
}
