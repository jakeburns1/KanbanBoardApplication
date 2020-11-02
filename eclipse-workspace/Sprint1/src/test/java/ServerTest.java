import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainPack.Board;
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

	@BeforeEach
	void set() {
		jake = new UserConcrete();
		jake.createBoard("server board", jake, null, null);
		jake.setPassword("centre1234");
		jake.setUsername("jakeburns");
		board = jake.getBoards().get(0);
		
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
