package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ChecklistComponent extends Component
{

	String items[];
	String checked[];
	/**
	 * @return the items
	 */
	public ChecklistComponent() {
		
	}
	public String[] getItems()
	{
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(String[] items)
	{
		this.items = items;
	}
	/**
	 * @return the checked
	 */
	public String[] getChecked()
	{
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String[] checked)
	{
		this.checked = checked;
	}
	
	public void storeToDisk() {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Checklist.xml")));
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
