package mainPack;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient
{
	RmiInterface r;
	
	public RmiClient(int port){
		Registry registry;
		
		
		try
		{
			registry = LocateRegistry.getRegistry(port);
			try
			{
				r = (RmiInterface) registry.lookup("RMISERVER");
			} catch (NotBoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User checkUsernamePassword(String username, String password) {
		
		try
		{
			//System.out.println("testing testing testing");

			return r.checkUsernamePassword(username, password);
		} catch (RemoteException e)
		{
			e.printStackTrace();
			return null;
		}
		
	
		
	}
	
	public Board getBoard(String string) {
		
		
		try
		{
			return r.getBoard(string);
		} catch (RemoteException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void updateBoard(Board board) {
	
		try
		{
			r.updateBoard(board);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Board createBoard(String s, User u) {
		try
		{
			return r.createBoard(s, u);
		} catch (RemoteException e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	

	
	
	
}
