import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainPack.Board;
import mainPack.CardConcrete;
import mainPack.Component;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.RmiClient;
import mainPack.RmiServer;
import mainPack.User;
import mainPack.UserConcrete;

class ServerTest
{
	static RmiServer server;
	static RmiClient client;
	
	//Board board= new BoardConcrete("Jake's Server Board",);
	User jake;
	Board board;
	
	ListConcrete list;
	ListConcrete list2;
	CardConcrete testCard;
	CardConcrete testCard2;
	Set<String> labels;
	Set<User> members;
	Set<Component> components;
	ArrayList<ListN> lists;

	@BeforeEach
	void set() {
		lists = new ArrayList<ListN>();
		list = new ListConcrete("Day 1");
		list2 = new ListConcrete("Day 2");
		lists.add(list);
		lists.add(list2);
		members = new HashSet<User>();
		
		jake = new UserConcrete();
		members.add(jake);
		jake.createBoard("test board 1", jake, members, lists);
		jake.createBoard("test board 2", jake, members, null);
		jake.setPassword("centre1234");
		jake.setUsername("jakeburns");
		board = jake.getBoards().get(0);
		
		testCard = new CardConcrete("Test card", labels, members, components);
		testCard2 = new CardConcrete("Second card", labels, members, components);
		list.addCards(testCard);
		list.addCards(testCard2);
		list2.addCards(testCard);
		
	}
	@BeforeAll
	static void setUp() throws Exception
	{
		int port = 3039;
		
		server = new RmiServer(port);
		//server.startServer(port);
		
		client = new RmiClient(port);
		
		
	}

	@AfterAll
	static void stop() {
		server.shutdown();
	}
	
	@Test
	void test() throws RemoteException
	{
		client.createBoard("banana", jake);
		//client.updateBoard(board);
		//fail("Not yet implemented");
		//server.loadBoardFromDisk();
		assertNotNull(client.checkUsernamePassword("jakeburns", "centre1234"));
		//assertEquals(board,client.getBoard((int) board.getSerialversionuid()));
		
		board.updateBoardName("pablo");
		client.updateBoard(board);
		//System.out.println(board.getUnique());
		//assertEquals(board.getBoardName(), client.getBoard(board.getUnique()).getBoardName());
		//client.getBoard(board.getUnique());
	}

}
