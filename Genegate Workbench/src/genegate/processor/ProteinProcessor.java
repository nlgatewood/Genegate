package genegate.processor;

/**
 * Encapsulates the Protein processing methods/attributes. The methods are the work-horse for the Protein
 * sequence processing. Extends the class 'SequenceProcessor'
 * 
 * @author Nathaniel Gatewood
 */
public class ProteinProcessor extends SequenceProcessor{
	
	/*----------------------------------------------------------
	 * DNAProcessor(String dnaSequence). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public ProteinProcessor(String proteinSequence){
		
		super(new Sequence(proteinSequence));
	}
}
