package mainPack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RmiInterface extends Remote
{


	
		public String concatenate(String string1, String string2) throws RemoteException;
		//public Component getCompenet() throws RemoteException;

}
