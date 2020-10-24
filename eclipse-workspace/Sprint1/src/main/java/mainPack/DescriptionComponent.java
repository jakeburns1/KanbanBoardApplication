package mainPack;

public class DescriptionComponent extends Component
{

	String text;
	
	
	/**
	 * @param text
	 */
	public DescriptionComponent(String text)
	{
		super();
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
}
