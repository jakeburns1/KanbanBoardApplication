package mainPack;

import java.io.Serializable;
import java.util.Set;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Card extends Serializable
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
	public Set<Component> getComponents();
	public Component getDesComponent();
	public Set<String> getLabels();
	//public void showCardView(Stage s, Scene scene, Card card, RmiClient c, BoardConcrete board);
}
