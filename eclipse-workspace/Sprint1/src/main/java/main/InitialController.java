package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainPack.RmiClient;
import mainPack.RmiServer;
import view.LoginModel;

public class InitialController
{
	LoginModel modelg;
	RmiClient client;
	RmiServer server;
	
	 	@FXML
	    private TextField usernameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    void loginPressed(ActionEvent event) {
	    	server.startServer(4150);
	    	//server.loadBoardFromDisk();
	    	if(client.checkUsernamePassword(usernameField.getText(), passwordField.getText())!=null) {
	    		System.out.println("successfully logged in");
	    	}
	    	else {
	    		System.out.println("login failed");
	    	}
	    }
	    
	    public void setModel(LoginModel newModel1)
		{
			modelg = newModel1;
			
			//StringConverter<Number> fmt = new NumberStringConverter();
			
			//Bindings.bindBidirectional(totalLabel.textProperty(), model.getCount(), fmt);		
		}

}
