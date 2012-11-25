package Automat;


/**
 * @author Dennis
 * @version 1.0
 * @created 26-Okt-2012 07:10:51
 */
public class Display extends Aktor{
		
	public Display(int adresse){
		super(adresse);
	}
	
	public void setText(String text){
		this.HWaccess.write(adresse, text);
	}

}