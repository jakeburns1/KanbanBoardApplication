package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ChecklistComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106003741992623813L;
	ArrayList<String> items;
	ArrayList<String> checked;

	/**
	 * @return the items
	 */
	public ChecklistComponent() {

	}

	public ArrayList<String> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

	/**
	 * @return the checked
	 */
	public ArrayList<String> getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(ArrayList<String> checked) {
		this.checked = checked;
	}

	public void storeToDisk() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Checklist.xml")));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
