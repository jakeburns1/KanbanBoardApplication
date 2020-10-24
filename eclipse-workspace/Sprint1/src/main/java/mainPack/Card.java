package mainPack;

public interface Card
{

	 // moveCard(parentList, card);
	public void createLabel(String text);
	public void deleteLabel(String text);
	public void addComponent(Component component);
	public void deleteComponent(Component component);
	public void updateName(String newName);
	public void deleteMember(User member);
}
