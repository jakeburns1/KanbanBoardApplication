package mainPack;

import java.util.Set;

public interface Card
{

	 // moveCard(parentList, card);
	public void createLabel(String text);
	public void deleteLabel(String text);
	public void addComponent(Component component);
	public void deleteComponent(Component component);
	public void setCardName(String newName);
	public void deleteMember(User member);
	public void storeToDisk();
	public String getCardName();
	public Set<String> getLabels();
}
