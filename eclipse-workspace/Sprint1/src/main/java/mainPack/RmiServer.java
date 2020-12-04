package mainPack;

import java.io.File;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class RmiServer extends UnicastRemoteObject implements RmiInterface {

	private static final long serialVersionUID = 1496158845636674987L;

	Hashtable<String, User> users;
	Hashtable<Integer, Board> boards;
	private Registry registry;
	private int newBoardID;

	public RmiServer(int port) throws RemoteException {
		super();
		users = new Hashtable<String, User>();
		boards = new Hashtable<Integer, Board>();
		startServer(port);
		getBoards();
	}

	public void startServer(int port) {
		try {
			registry = LocateRegistry.createRegistry(port);
			RmiServer server = this;

			String name = "RMISERVER";
			registry.rebind(name, server);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		// startServer(5050);
		// getBoards();

	}

	@SuppressWarnings({ "unlikely-arg-type", "static-access" })
	public void getBoards() {

		BoardConcrete board = new BoardConcrete();

		File file = new File("./");
		File[] files = file.listFiles();

		for (File xml : files) {
			if (xml.getName().contains("xml") && !xml.getName().equals("pom.xml")) {
				board = (BoardConcrete) board.loadFromDisk(xml.getName());
				if (!boards.containsKey(board.getSerialversionuid())) {
					if (board.getSerialversionuid() > newBoardID) {
						newBoardID = (int) board.getSerialversionuid();
					}
					boards.put((int) board.getSerialversionuid(), board);
					addUsersTable(board);
				} else {

				}
			}

		}

	}

	private void addUsersTable(Board board) {
		User owner = (User) board.getOwner();
		if (!users.containsKey(owner.getUnique())) {
			users.put(owner.getUnique(), owner);
		}

		for (User u : board.getMembers()) {
			UserConcrete s = (UserConcrete) u;
			if (!users.containsKey(s.getUnique())) {
				users.put(s.getUnique(), s);
			}
		}
	}

	public void shutdown() {
		try {
			registry.unbind("RMISERVER");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the newBoardID
	 */
	public int getNewBoardID() {
		return newBoardID;
	}

	public User checkUsernamePassword(String user, String pass) {
		// System.out.println("TESTING TESTING TESTING");
		for (User u : users.values()) {
			if (u.login(user, pass)) {
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
