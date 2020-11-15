package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DescriptionComponent extends Component
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5780721723958570092L;
	String text;
	
	
	/**
	 * @param text
	 */
	public DescriptionComponent(String text)
	{
		super();
		this.text = text;
	}
	
	public DescriptionComponent() {
		
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	public void editData(String string) {
		text = string;
	}


	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}
	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("DescriptionComp.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
}
