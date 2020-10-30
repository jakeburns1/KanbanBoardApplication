package mainPack;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RmiServer extends UnicastRemoteObject implements RmiInterface
{ 
	
		

		private static final long serialVersionUID = 1496158845636674987L;

		protected RmiServer() throws RemoteException
		{
			super();
		}
		public String concatenate(String string1, String string2) throws RemoteException
		{
			return string1 + string2;
		}
		
		public static void startServer() {
			try
			{
				Registry registry = LocateRegistry.createRegistry(2105); 
				RmiServer s = new RmiServer();
				
				
				String name = "RMISERVER";
				registry.rebind(name, s);
				
				RmiInterface mp = (RmiInterface) registry.lookup(name);
				
				String answer = mp.concatenate("Jacob", "Ballard");
				
				System.out.println(answer);
				
			} catch (RemoteException e)
			{
				e.printStackTrace();
			} catch (NotBoundException e)
			{
				e.printStackTrace();
			}
		}
		
		
		public static void main(String[] args)
		{
			
			
			startServer();
			getBoards();
			
			
			
			
		}
		
		public static ArrayList<Board> getBoards()
		{
			ArrayList<Board> boards = new ArrayList<Board>();
			BoardConcrete boardFromDisk = Board.loadFromDisk();
			//User u = new UserConcrete();
			boards.add(boardFromDisk);
			System.out.println(boards);
			return boards;
		}
		
		public static Board loadBoardFromDisk() {
			return null;
		}
		
		public static Board getBoard(String boardID) {
			
			return null;
		}
		
		public static void checkMembersUpdated() {
			
		}
		
		public static void save(Board b) {
			
		}
		
		public static User login() {
			return null;
		}
		
		public static  void addBoardToUser() {
			
		}
	}


