package ua.nure.kn.stakhevych.domain.web;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.stakhevych.domain.User;

public class DetailsServletTest extends MockServletTestCase {

	private static final String CanselButton = "cancel";
	
	public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
	}

	public void testDetails(){
		
		addRequestParameter("cancelButton", CanselButton );
		
		User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		

	}
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
