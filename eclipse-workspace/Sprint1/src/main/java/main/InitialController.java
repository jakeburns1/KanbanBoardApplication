package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainPack.RmiClient;
import mainPack.User;
import view.LoginModel;

public class InitialController
{
	LoginModel modelg;
	RmiClient client;
	SelectionController selection;
	User user;
	
	 	@FXML
	    private TextField usernameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    void loginPressed(ActionEvent event) {
	    	if(client.checkUsernamePassword(usernameField.getText(), passwordField.getText())!=null) {
	    		
	    		 user = client.checkUsernamePassword(usernameField.getText(), passwordField.getText());
	    		
	    		System.out.println("successfully logged in");
	    		// load board selection screen
	    		
	    		modelg.showSelectionScreen();
	    		modelg.setUserForSelectionView(user, client);
	    		
	    	}
	    	else {
	    		System.out.println("login failed");
	    	}
	    	
	    }
	    
	    public void setModel(LoginModel newModel1, RmiClient client)
		{
			modelg = newModel1;
			this.client = client;
			
			//StringConverter<Number> fmt = new NumberStringConverter();
			
			//Bindings.bindBidirectional(totalLabel.textProperty(), model.getCount(), fmt);		
		}

}
