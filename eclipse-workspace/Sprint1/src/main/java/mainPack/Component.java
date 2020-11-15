package mainPack;

import java.io.Serializable;

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
}
