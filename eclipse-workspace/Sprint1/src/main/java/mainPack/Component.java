package mainPack;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Component implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1144537457181700935L;

	public void editData() {
		
	}
	
	public void storeToDisk()
	{
	}

	public abstract String getText();
	
	public abstract ArrayList<String> getItems();
	public abstract void setItems(ArrayList<String> items);
	public abstract ArrayList<String> getChecked();
	public abstract void setChecked(ArrayList<String> checked);
}
