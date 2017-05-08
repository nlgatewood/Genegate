package genegate.processor;

import java.lang.ArithmeticException;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Encapsulates the DNA processing methods/attributes. The methods are the work-horse for the DNA
 * sequence processing. Extends the class 'SequenceProcessor'
 * 
 * @author Nathaniel Gatewood
 */
public class DNAProcessor extends SequenceProcessor{
	
	/*----------------------------------------------------------
	 * DNAProcessor(String dnaSequence). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public DNAProcessor(String dnaSequence){
		
		super(new Sequence(dnaSequence));
	}
	
	/*----------------------------------------------------------
	 * calculateGCContent(). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public String calculateGCContent() {
		
		String dnaSequence = getSequence();
		DecimalFormat decF = new DecimalFormat("###.##");
		int gcCount = 0;
		float gcPercent = 0;
		
		//Count the number of 'G' and 'C' bases in the DNA sequence
		for(int i=0; i<dnaSequence.length(); i++){
			
			if(dnaSequence.charAt(i) == 'G' || dnaSequence.charAt(i) == 'C'){
				
				gcCount++;
			}
		}
		
		//Divide the gc count by the length.
		try{
			gcPercent = ((float) gcCount/dnaSequence.length())*100;
			
		}catch(ArithmeticException e){
			
			gcPercent = 0;
		}
		
		return decF.format(gcPercent);
	}
	
	/*----------------------------------------------------------
	 * transcribeToRNA(). 
	 *----------------------------------------------------------*/
	public String transcribeToRNA() {
		
		String dnaSequence = getSequence();
		String rnaSequence = "";
		
		for(int i=0; i<dnaSequence.length(); i++){
			
			char thisBase;
			
			if(dnaSequence.charAt(i) == 'T'){
				
				thisBase = 'U';
			}
			else{
				thisBase = dnaSequence.charAt(i);
			}
			
			rnaSequence += Character.toString(thisBase);
		}
		
		return rnaSequence;
	}
	
	/*----------------------------------------------------------
	 * translateToProtein(int orf). 
	 *----------------------------------------------------------*/
	public String translateToProtein(int orf){
		
		String rnaSequence = transcribeToRNA();
		String[] rnaBases = rnaSequence.split("");
		Map<String,String> codonMap = SeqRefs.codons;
		String proteinSequence = "";
		
		for(int i=orf-1; 3<=(rnaBases.length-i); i+=3){
			
			String codon = rnaBases[i]+rnaBases[i+1]+rnaBases[i+2];
			proteinSequence += codonMap.get(codon);
		}
	
		return proteinSequence;
	}
}
