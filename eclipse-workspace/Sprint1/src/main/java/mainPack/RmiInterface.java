package mainPack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RmiInterface extends Remote {

	public User checkUsernamePassword(String user, String pass) throws RemoteException;

	public Board getBoard(String id) throws RemoteException;

	public void updateBoard(Board board) throws RemoteException;

	public Board createBoard(String s, User u) throws RemoteException;

	public Board save(Board b) throws RemoteException;

}
