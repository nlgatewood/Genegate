package genegate.processor;

/**
 * Generic Sequence processing class.  inherited by DNAProcessor and ProteinProcessor
 * 
 * @author Nathaniel Gatewood
 */
public class SequenceProcessor {

	private Sequence seqO;
	
	/*----------------------------------------------------------
	 * Processor(String dnaSequence). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public SequenceProcessor(Sequence seqO){
		
		this.seqO = seqO;
	}
	
	/*----------------------------------------------------------
	 * getSequence(). 
	 *----------------------------------------------------------*/
	public String getSequence() {
		
		return seqO.getSequence();
	}
	
	/*----------------------------------------------------------
	 * setSequence(). 
	 *----------------------------------------------------------*/
	public void setSequence(String sequence) {
		
		seqO.setSequence(sequence);
	}

	/*----------------------------------------------------------
	 * getSequenceLength().
	 *----------------------------------------------------------*/
	public int getSequenceLength(){
		
		return seqO.getSequenceLength();
	}
}
