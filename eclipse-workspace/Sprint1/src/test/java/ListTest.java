import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainPack.Board;
import mainPack.BoardConcrete;
import mainPack.Card;
import mainPack.CardConcrete;
import mainPack.Component;
import mainPack.DescriptionComponent;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.User;
import mainPack.UserConcrete;

class ListTest
{
	
	ListConcrete list;
	ListConcrete list2;
	CardConcrete testCard;
	CardConcrete testCard2;
	Set<String> labels;
	Set<User> members;
	Set<Component> components;
	ArrayList<ListN> lists;
	User jake;
	User bob;
	DescriptionComponent comp;
	Board board;
	

	@BeforeEach
	void setUp() throws Exception
	{	
		jake = new UserConcrete();
		bob = new UserConcrete();
		jake.setUsername("jakeburns");
		jake.setPassword("centre1234");
		bob.setPassword("computerscience");
		bob.setUsername("bobarnold");
		lists = new ArrayList<ListN>();
		list = new ListConcrete("Day 1");
		list2 = new ListConcrete("Day 2");
		lists.add(list);
		lists.add(list2);
		members = new HashSet<User>();
		members.add(jake);
		board = jake.createBoard("Test Board", jake, members, lists);
		labels = new HashSet<String>();
		
		components = new HashSet<Component>();
		
		
		
		comp = new DescriptionComponent("This is the description.");
		labels.add("Design Day");
		members.add(jake);
		components.add(comp);
		testCard = new CardConcrete("Test card", labels, members, components);
		testCard2 = new CardConcrete("Second card", labels, members, components);
		list.addCards(testCard);
		list.addCards(testCard2);
	}

	@Test
	void test()
	{
		//assertEquals("test", list.getCards());
		assertEquals(true, testCard.getLabels().contains("Design Day"));
		assertEquals("Day 1", list.getListName());
	}
	
	@Test
	void cardTests() {
		assertEquals("Test card", testCard.getCardName());
	}
	
	@Test
	void testBoardName() {
		assertEquals("Test Board", board.getBoardName());
		
	}
	
	@Test
	void testBoardOwner() {
		assertEquals(jake, board.getOwner());
	}
	
	@Test
	void userHasBoards() {
		assertNotNull(jake.getBoards());
	}
	
	@Test
	void testOrderedSetofLists() {
		assertEquals(lists, board.getLists());
	}
	
	@Test
	void testListOrderSetOfCards() {
		assertEquals(true, list.getCards().contains(testCard2));
		list.removeCard(testCard2);
		assertEquals(false, list.getCards().contains(testCard2));
		assertEquals(true, list.getCards().contains(testCard));
	}

	@Test
	void moveCardToIndexTest() {
		list.reorderCard(testCard, 2);
		assertEquals(testCard2, list.getCards().get(0));
		assertEquals(testCard,  list.getCards().get(1));
		
	}
	
	@Test
	void updateBoardName() {
		board.updateBoardName("Jake's Board");
		assertEquals("Jake's Board", board.getBoardName());
	}
	
	@Test
	void updateListName() {
		list.updateName("Banana List");
		assertEquals("Banana List", list.getListName());
	}
	
	@Test
	void updateCardName() {
		testCard.setCardName("Bad Card");
		assertEquals("Bad Card", testCard.getCardName());
	}
	
	@Test
	void moveCardBetweenListTest() {
		assertEquals(false, list2.getCards().contains(testCard));
		list.moveCard(testCard, list2, 0);
		assertEquals(true,list2.getCards().contains(testCard));
	}
	
	@Test
	void listReorderTest() {
		board.reorderList(list, 2);
		assertEquals(list2, board.getLists().get(0));
		assertEquals(list, board.getLists().get(1));
		
	}
	
	@Test
	void ownerAddUserTest() {
		assertEquals(false, board.getMembers().contains(bob));
		assertEquals(0, bob.getBoards().size());
		board.addMember(bob);
		assertEquals(1 ,bob.getBoards().size());
	}
	
	@Test
	void cardLabelsTest() {
		assertEquals(true, testCard.getLabels().contains("Design Day"));
		assertEquals(false, testCard.getLabels().contains("Banana"));
		testCard.createLabel("Banana");
		assertEquals(true, testCard.getLabels().contains("Banana"));
		
		testCard.setMembers(bob);
		
	}
	
	@Test
	void membersWithCardTest() {
		testCard.deleteMember(jake);
		assertEquals(false, testCard.getMembers().contains(jake));
		assertEquals(false, testCard.getMembers().contains(bob));
		testCard.setMembers(jake);
		assertEquals(true, testCard.getMembers().contains(jake));
		testCard.setMembers(bob);
		assertEquals(true, testCard.getMembers().contains(bob));
		
	}
	
	@Test
	void componentTest() {
		testCard.deleteComponent(comp);
		assertEquals(false, testCard.getComponents().contains(comp));
		testCard.addComponent(comp);
		assertEquals(true, testCard.getComponents().contains(comp));
	}
	
	@Test
	void testXML() {
		board.storeToDisk();
		testCard.storeToDisk();
		comp.storeToDisk();
		list.storeToDisk();
		jake.storeToDisk();
		
		BoardConcrete diskB = BoardConcrete.loadFromDisk();
		CardConcrete diskC = CardConcrete.loadFromDisk();
		ListConcrete diskL = ListConcrete.loadFromDisk();
		assertTrue(board.equals(diskB));
		assertTrue(testCard.equals(diskC));
	    assertTrue(list.equals(diskL));
	}
	
	
	
}
