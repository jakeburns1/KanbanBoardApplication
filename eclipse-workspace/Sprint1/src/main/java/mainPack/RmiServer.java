package mainPack;

import java.io.File;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class RmiServer extends UnicastRemoteObject implements RmiInterface
{ 
	
		

		private static final long serialVersionUID = 1496158845636674987L;
		
		Hashtable<String, User> users;
		Hashtable<Integer, Board> boards;
		private Registry registry;
		private int newBoardID;
		 
		 
		 //static ArrayList<User> users;
		// static UserConcrete p;
		 //static ArrayList<Long> boardIdent = new ArrayList<Long>();

		public RmiServer(int port) throws RemoteException
		{
			super();
			users = new Hashtable<String, User>();
			boards = new Hashtable<Integer, Board>();
			startServer(port);
			getBoards();
		}
		
		
		public void startServer(int port)
		{
			try
			{
				registry = LocateRegistry.createRegistry(port);
				RmiServer server = this;
				
				String name = "RMISERVER";
				registry.rebind(name, server);
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
			
		}


//		public static void startServer() {
//			try
//			{
//				Registry registry = LocateRegistry.createRegistry(2108); 
//				RmiServer s = new RmiServer();
//				
//				
//				String name = "RMISERVER";
//				registry.rebind(name, s);
//				
//				RmiInterface mp = (RmiInterface) registry.lookup(name);
//				
//				String answer = mp.concatenate("Jacob", "Ballard");
//				
//				System.out.println(answer);
//				
//			} catch (RemoteException e)
//			{
//				e.printStackTrace();
//			} catch (NotBoundException e)
//			{
//				e.printStackTrace();
//			}
//			
//			
//		}
		
		
		public static void main(String[] args)
		{
			
			
			//startServer(5050);
			//getBoards();
			
			
			
			
		}
		
		@SuppressWarnings({ "unlikely-arg-type", "static-access" })
		public void getBoards()
		{
			//ArrayList<Board> boards = new ArrayList<Board>();
			//BoardConcrete boardFromDisk = Board.loadFromDisk();
			BoardConcrete board = new BoardConcrete();
			 //users = new ArrayList<User>();
				
			//  p = UserConcrete.loadFromDisk();
			 
			File file = new File("./");
			File[] files = file.listFiles();
			
			for(File xml: files) {
				if(xml.getName().contains("xml") && !xml.getName().equals("pom.xml")) {
					board = (BoardConcrete) board.loadFromDisk(xml.getName());
					if(!boards.containsKey(board.getSerialversionuid())) {
						if(board.getSerialversionuid()> newBoardID) {
							newBoardID = (int) board.getSerialversionuid();
						}
						boards.put((int) board.getSerialversionuid(), board);
						addUsersTable(board);
					}
					else {
						
					}
				}
				
			}
			
			
//			
//			 users.add(p);
//			//User u = new UserConcrete();
//			boards.add(boardFromDisk);
//			
//			for (Board b: boards) {
//				boardIdent.add(b.getSerialversionuid());
//			}
			
			//System.out.println(boards);
			//return boards;
		}
		
		private void addUsersTable(Board board)
		{
				User owner = (User) board.getOwner();
				if(!users.containsKey(owner.getUnique())) {
					users.put(owner.getUnique(), owner);
				}
				
				for(User u: board.getMembers()) {
					UserConcrete s =  (UserConcrete) u;
					if(!users.containsKey(s.getUnique())) {
						users.put(s.getUnique(), s);
					}
				}
		}


		public void shutdown() {
			try
			{
				registry.unbind("RMISERVER");
			} catch (AccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		/**
		 * @return the newBoardID
		 */
		public int getNewBoardID()
		{
			return newBoardID;
		}


		public User checkUsernamePassword(String user, String pass) {
			//System.out.println("TESTING TESTING TESTING");
			for(User u: users.values()) {
				if(u.login(user, pass)) {
					return u;
				}
			}
			
			return null;
			
		}
		
		public static Board loadBoardFromDisk() {
			return Board.loadFromDisk();
		}
		
		public Board getBoard(String id) {
			
			return boards.get(id);
		}
		
	
		public void updateBoard(Board board) {
			
			BoardConcrete b = (BoardConcrete) board;
			
			this.boards.replace((int) b.getSerialversionuid(), b);
			b.save();

		}
		
		public Board save(Board b) {
			b.storeToDisk();
			return null;
		}
		
//		public Boolean login(String username, String password) {
//			
//			Boolean success = false;
//			if(username.equals(username) && password.equals(password)) {
//				success  = true;
//			}
//			
//			return success;
//			
//		}
		
		public static void addBoardToUser() {
			

			
		}
		public Board createBoard(String s, User u) {
			
			Board board = new BoardConcrete();
			board.setOwner(u);
			board.updateBoardName(s);
			save(board);
			return board;
		}

	}


