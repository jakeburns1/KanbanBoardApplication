package mainPack;

import java.util.Set;

public class CardConcrete implements Card
{

	String cardName;
	Set<String> labels;
	Set<User> members;
	Set<Component> components;
	
	
	
	/**
	 * @param cardName
	 * @param labels
	 * @param members
	 * @param components
	 */
	public CardConcrete(String cardName, Set<String> labels, Set<User> members, Set<Component> components)
	{
		super();
		this.cardName = cardName;
		this.labels = labels;
		this.members = members;
		this.components = components;
	}

	@Override
	public void createLabel(String text)
	{
		labels.add(text);
	}

	@Override
	public void deleteLabel(String text)
	{
		labels.remove(text);
	}

	/**
	 * @return the members
	 */
	public Set<User> getMembers()
	{
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(User member)
	{
		members.add(member);
	}

	public void deleteMember(User member) {
		members.remove(member);
	}
	/**
	 * @return the cardName
	 */
	public String getCardName()
	{
		return cardName;
	}

	/**
	 * @return the labels
	 */
	public Set<String> getLabels()
	{
		return labels;
	}

	/**
	 * @return the components
	 */
	public Set<Component> getComponents()
	{
		return components;
	}

	public void addComponent(Component component)
	{
		components.add(component);
	}

	public void deleteComponent(Component component) {
		components.remove(component);
	}
	
	public void updateName(String newName)
	{
		cardName = newName;
	}

	

}
