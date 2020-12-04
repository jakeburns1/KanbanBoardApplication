import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainPack.UserConcrete;

class UserTest {

	UserConcrete jake;
	Set<UserConcrete> mem;

	@BeforeEach
	void setUp() throws Exception {
		// jake = new UserConcrete("jakeburns", "centrecollege");
		mem.add(jake);
	}

	@Test
	void loginTest() {
		assertEquals(true, jake.login("jakeburns", "centrecollege"));

	}

	@Test

	void createBoardTest() {
		// assertEquals("Test", jake.createBoard("Jake's Board", jake, mem, null));
	}

}
