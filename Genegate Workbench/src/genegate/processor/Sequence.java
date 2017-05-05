package genegate.processor;

/**
 * Encapsulates a 'sequence' String; including DNA and Protein.  In the future, I may want to split
 * DNA and Protein sequences into different classes and have them inherit this class.
 * 
 * @author Nathaniel Gatewood
 */
public class Sequence {
	
	private String sequence;
	
	/*----------------------------------------------------------
	 * DNASequence(). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public Sequence(String sequence){
		
		this.sequence = sequence;
		formatSequence();
	}
	
	/*----------------------------------------------------------
	 * getSequence().
	 *----------------------------------------------------------*/
	public String getSequence(){
		
		return sequence;
	}
	
	/*----------------------------------------------------------
	 * setSequence(String sequence).
	 *----------------------------------------------------------*/
	public void setSequence(String sequence){
		
		this.sequence = sequence;
		formatSequence();
	}
	
	/*----------------------------------------------------------
	 * getSequenceLength().
	 *----------------------------------------------------------*/
	public int getSequenceLength(){
		
		return sequence.length();
	}
	
	/*----------------------------------------------------------
	 * formatSequence().
	 *----------------------------------------------------------*/
	public void formatSequence(){
		
		this.sequence = this.sequence.replaceAll("(\n|\\s)", "");
	}
}
