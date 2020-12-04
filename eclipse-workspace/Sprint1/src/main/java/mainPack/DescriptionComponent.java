package mainPack;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DescriptionComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5780721723958570092L;
	String text;

	/**
	 * @param text
	 */
	public DescriptionComponent(String text) {
		super();
		this.text = text;
	}

	public DescriptionComponent() {

	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public void editData(String string) {
		text = string;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	public void storeToDisk() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("DescriptionComp.xml")));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(this);
		encoder.close();
	}

	@Override
	public void setItems(ArrayList<String> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChecked(ArrayList<String> checked) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getChecked() {
		// TODO Auto-generated method stub
		return null;
	}

}
