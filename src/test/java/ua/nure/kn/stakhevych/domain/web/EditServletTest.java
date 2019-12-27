package ua.nure.kn.stakhevych.domain.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.stakhevych.domain.User;

public class EditServletTest extends MockServletTestCase {

	private static final String Last_NameTest = "Doe";
	private static final String First_NameTest = "John";
	private static final String WRONG_DATE_TEST = "Something go wrong! Sorry! Check date!";
	private static final String ID_TEST = "1000";
	private static final String OK_BUTTON = "Ok";
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	public void testEdit(){
		Date date = new Date();
		User user = new User(new Long(1000),First_NameTest,Last_NameTest,date);
		getMockUserDao().expect("update",user);
		
		addRequestParameter("id",ID_TEST);
		addRequestParameter("firstName",First_NameTest);
		addRequestParameter("lastName",Last_NameTest);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
	}
	
	public void testEditEmptyFirstName(){
		Date date = new Date();
		addRequestParameter("id",ID_TEST);
		addRequestParameter("lastName",Last_NameTest);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	
	public void testEditEmptyLastName(){
		Date date = new Date();
		addRequestParameter("id",ID_TEST);
		addRequestParameter("firstName",First_NameTest);
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	
	public void testEditEmptyDateOfBirth(){
		Date date = new Date();
		addRequestParameter("id",ID_TEST);
		addRequestParameter("firstName",First_NameTest);
		addRequestParameter("lastName",Last_NameTest);
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	
	public void testEditEmptyDateIncorrect(){
		Date date = new Date();
		addRequestParameter("id",ID_TEST);
		addRequestParameter("firstName",First_NameTest);
		addRequestParameter("lastName",Last_NameTest);
		addRequestParameter("date",WRONG_DATE_TEST);
		addRequestParameter("okButton",OK_BUTTON);
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
