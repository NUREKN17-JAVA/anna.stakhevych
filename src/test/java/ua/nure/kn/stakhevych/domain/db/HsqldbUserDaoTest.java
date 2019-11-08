package ua.nure.kn.stakhevych.domain.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.stakhevych.domain.User;
import junit.framework.TestCase;


public class HsqldbUserDaoTest extends DatabaseTestCase {

	private static final String UPDATED_MY_LAST_NAME = "Mao";
	private static final String MY_LAST_NAME = "Doe";
	private static final String MY_FIRST_NAME = "John";
	private static final long ID = 1L;
	private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:hsqldb:file:db/Java_Laba_Stakhevych";
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    
    private static final Long DELETE_USER_ID = 1002L;
    private UserDao userDao;
	
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		// connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);

	}

	public void testCreate() {
		// fail("Not yet implemented");
		try {
			User user = new User();
			user.setFirstName(MY_FIRST_NAME);
			user.setLastName(MY_LAST_NAME);
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindAll() {
		try {
			Collection collection =  dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size", 2, collection.size());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFind() throws DatabaseException {
       try{
           User user = dao.find(ID);
           assertNotNull(user);
       } catch (DatabaseException e){
           e.printStackTrace();
           fail(e.toString());
       }

    }
	
	public void testUpdate() throws DatabaseException {
        User testUpdateUserLN = new User(ID, MY_FIRST_NAME, MY_LAST_NAME, new Date());
        dao.create(testUpdateUserLN);
        testUpdateUserLN.setLastName(UPDATED_MY_LAST_NAME);
        dao.update(testUpdateUserLN);
        User updatedUser = dao.find(testUpdateUserLN.getId());
        assertNotNull(updatedUser);
        assertEquals(testUpdateUserLN.getFirstName(), updatedUser.getFirstName());
        assertEquals(testUpdateUserLN.getLastName(), updatedUser.getLastName());
    }
	
	public void testDelete() throws DatabaseException {
        User user = new User();
        user.setId(DELETE_USER_ID);
        try {
            dao.delete(user);
            dao.find(DELETE_USER_ID); 
            fail(); 
        } catch (DatabaseException e) {
            assert(e.getMessage().contains(DELETE_USER_ID.toString()));
        }

    }
	private User createUser() {
        User user = new User();
        user.setId(ID);
        user.setFirstName(MY_FIRST_NAME);
        user.setLastName(MY_LAST_NAME);
        user.setDateOfBirth(new Date());
        return user;
    }

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		// TODO Auto-generated method stub
		connectionFactory = new ConnectionFactoryImpl(DRIVER, URL, USER, PASSWORD);
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}