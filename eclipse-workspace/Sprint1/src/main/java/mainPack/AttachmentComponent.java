package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AttachmentComponent extends Component
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5812296224826933512L;
	File file;

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
	}
	
	public AttachmentComponent() {
		
	}
	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("AttachmentComp.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}

	@Override
	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
