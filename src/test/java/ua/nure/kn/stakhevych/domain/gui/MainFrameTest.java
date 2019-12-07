package ua.nure.kn.stakhevych.domain.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.DaoFactoryImpl;
import ua.nure.kn.stakhevych.domain.db.MockUserDao;
import ua.nure.kn.stakhevych.domain.db.MockDaoFactory;
import ua.nure.kn.stakhevych.domain.util.Messages;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import junit.framework.Assert;

public class MainFrameTest extends JFCTestCase {

//	private static final String RISE_LAST_NAME = "Rise";
//	private static final String SASHA_FIRST_NAME = "Sasha";
//	private static final String AGE_LABEL = "ageLabel";
//	private static final String BIRTH_DATE_LABEL = "birthDateLabel";
//	private static final String LAST_NAME_LABEL = "lastNameLabel";
//	private static final String FIRST_NAME_LABEL = "firstNameLabel";
//	private static final String ID_LABEL = "idLabel";
//	private static final String DETAILS_PANEL = "detailsPanel";
//	private static final String FIND_ALL_COMMAND = "findAll";
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
//	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
//	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
//	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
//	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
//	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
//	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
//	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
//	private static final String EDIT_PANEL_COMPONENT_NAME = "editPanel";
//	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
//	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final Date DATE_OF_BIRTH = new Date();
	private  Date now = new Date();
//	private static final String CREATE_COMMAND = "create";
//	private static final String DELETE_COMMAND = "delete";
//	private static final String UPDATE_COMMAND = "update";
	
	private static final int ID_TEST = 3;
	private static final String LAST_NAME_TEST = "Bush";
	private static final String FIRST_NAME_TEST = "George";
	
	//private Mock mockUserDao;
	//private List<User> users;
	
	private ArrayList<User> users;
	//private MainFrame mainFrame;
	private Mock mockUserDao;

	private MainFrame mainFrame = new MainFrame();

//	private MainFrame mainFrame;
	@Before
	protected void setUp() throws Exception {

		super.setUp();
	//	try {mainFrame = new MainFrame();
		try{
			Properties properties = new Properties();
			properties.setProperty("ua.nure.kn.stakhevych.domain.db.UserDao",
					MockUserDao.class.getName());
			properties.setProperty("dao.factory", MockDaoFactory.class.getName()); //$NON-NLS-1$
			DaoFactory.getInstance().init(properties);

		try{	//mockUserDao = ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao();	
			
			mockUserDao = ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao();	
			
			
			User expectedUser = new User(new Long(ID_TEST), FIRST_NAME_TEST, LAST_NAME_TEST, DATE_OF_BIRTH);
	        users = new ArrayList<User>();
	        users.add(expectedUser);
	        
	        mockUserDao.expectAndReturn("findAll",users);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
			
			setHelper(new JFCTestHelper());
		//try{
			mainFrame = new MainFrame();

		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);

	}

	@After
	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
mainFrame.setVisible(false);
getHelper().cleanUp(this);
super.tearDown();
} catch (Exception e) {
	e.printStackTrace();
}
	}

	private Component find(Class componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component); //$NON-NLS-1$ //$NON-NLS-2$
		return component;
	}

	public void testBrowseControls() {
		find(JPanel.class,"browsePanel");
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(3,table.getColumnCount());
		assertEquals(Messages.getString("UserTableModel.id"),table.getColumnName(0));
		assertEquals(Messages.getString("UserTableModel.first_name"),table.getColumnName(1));
		assertEquals(Messages.getString("UserTableModel.last_name"),table.getColumnName(2));
		find(JButton.class,"addButton");
		find(JButton.class,"editButton");
		find(JButton.class,"deleteButton");
		find(JButton.class,"detailsButton");
	}

	public void testAddUser() {
		try {
            String firstName = FIRST_NAME_TEST;
            String lastName = LAST_NAME_TEST;

            User user = new User(FIRST_NAME_TEST, LAST_NAME_TEST, DATE_OF_BIRTH);

            User expectedUser = new User(new Long(1), FIRST_NAME_TEST, LAST_NAME_TEST, DATE_OF_BIRTH);
            mockUserDao.expectAndReturn("create", user, expectedUser);
                    
            ArrayList<User> users = new ArrayList<User>(this.users);
            users.add(expectedUser);
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton addButton = (JButton) find(JButton.class, "addButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

            find(JPanel.class, "addPanel");

            fillField(firstName, lastName, now);

            JButton okButton = (JButton) find(JButton.class, "okButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }

	}
	
	private void fillField(String firstName, String lastName, Date now2) {
		  JTextField firstNameField = (JTextField) find(JTextField.class,
	                "firstNameField");
	        JTextField lastNameField = (JTextField) find(JTextField.class,
	                "lastNameField");
	        JTextField dateOfBirthField = (JTextField) find(JTextField.class,
	                "dateOfBirthField");

	        getHelper().sendString(
	                new StringEventData(this, firstNameField, firstName));
	        getHelper().sendString(
	                new StringEventData(this, lastNameField, lastName));
	        DateFormat formatter = DateFormat.getDateInstance();
	        String date = formatter.format(now);
	        getHelper().sendString(
	                new StringEventData(this, dateOfBirthField, date));
	    }

	public void testEditPanel(){
		try { 
			
        find(JPanel.class, "browsePanel");

        User expectedUser = new User(new Long(3), FIRST_NAME_TEST, LAST_NAME_TEST, DATE_OF_BIRTH);
        mockUserDao.expect("update", expectedUser);
        List<User> users = new ArrayList<User>(this.users);
        users.add(expectedUser);

        mockUserDao.expectAndReturn("findAll", users);

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
        JButton editButton = (JButton) find(JButton.class, "editButton");
        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
        getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
        
        find(JPanel.class, "editPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class,
                "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class,
                "lastNameField");
        
        getHelper().sendString(
                new StringEventData(this, firstNameField, "1"));
        getHelper().sendString(
                new StringEventData(this, lastNameField, "1"));

        JButton okButton = (JButton) find(JButton.class, "okButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
        

        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(2, table.getRowCount());
    	} catch (Exception e) {
        fail(e.toString());
    }
	}
	
	
	public void testShowDeteilsPanel(){
		try { 
						
			 ArrayList<User> users = new ArrayList<User>(this.users);
	            mockUserDao.expectAndReturn("findAll", users);

	            JTable table = (JTable) find(JTable.class, "userTable");
	            assertEquals(1, table.getRowCount());

	            JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
	            getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

	            String title = "Details user";
	            findDialog(title);

	            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
	            getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));


	            find(JPanel.class, "detailsPanel");

	            JButton cancelButton = (JButton) find(JButton.class, "cancelButton");
	            getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

	            find(JPanel.class, "browsePanel");
	            table = (JTable) find(JTable.class, "userTable");
	            assertEquals(1, table.getRowCount());
		        
		        
    	} catch (Exception e) {
        fail(e.toString());
    }
	}
	
	 private void findDialog(String title) {
	        JDialog dialog;
	        DialogFinder dFinder = new DialogFinder(title);
	        dialog = (JDialog) dFinder.find();
	        assertNotNull("Could not find dialog '" + title + "'", dialog);
	        getHelper();
	        TestHelper.disposeWindow( dialog, this );
	    }
	 
	 public void testDeleteUser() {
			User expectedUser = new User(new Long(3),FIRST_NAME_TEST, LAST_NAME_TEST, DATE_OF_BIRTH);
	        mockUserDao.expect("deleteButton", expectedUser);
	        ArrayList<User> users = new ArrayList<User>();
	        mockUserDao.expectAndReturn("findAll", users);
	        JTable table = (JTable) find(JTable.class,  "userTable");
	        assertEquals(1, table.getRowCount());
	        JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
	        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
	        getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));
	        find(JPanel.class, "brousePanel");
	        table = (JTable) find(JTable.class, "userTable");
	        assertEquals(0, table.getRowCount());
	}
	
	private void fillFields(String firstName, String lastName, Date dateOfBirth) {
		JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
		JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		String dateString = DateFormat.getInstance().format(dateOfBirth);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, dateString));
	}
	
}
