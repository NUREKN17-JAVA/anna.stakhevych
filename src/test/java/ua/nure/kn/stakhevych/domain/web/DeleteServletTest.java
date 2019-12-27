package ua.nure.kn.stakhevych.domain.web;

import static org.junit.Assert.*;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.stakhevych.domain.User;

public class DeleteServletTest extends MockServletTestCase {

	private static final String OK_BUTTON = "Ok";
	private static final String Last_NameTest = "Doe";
	private static final String First_NameTest = "John";

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(DeleteServlet.class);
	}
	
	public void testDelete() {
		User user = new User(new Long(1000), First_NameTest, Last_NameTest, new Date());
        getMockUserDao().expect("delete", user);
        setSessionAttribute("user", user);
        addRequestParameter("ok", OK_BUTTON);
        doPost();
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
