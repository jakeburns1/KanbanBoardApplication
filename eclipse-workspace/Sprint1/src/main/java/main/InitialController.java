package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainPack.RmiClient;
import view.LoginModel;

public class InitialController
{
	LoginModel modelg;
	RmiClient client;
	
	 	@FXML
	    private TextField usernameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    void loginPressed(ActionEvent event) {
	    	if(client.checkUsernamePassword(usernameField.getText(), passwordField.getText())!=null) {
	    		System.out.println("successfully logged in");
	    		// load board selection screen
	    		modelg.showSelectionScreen();
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
