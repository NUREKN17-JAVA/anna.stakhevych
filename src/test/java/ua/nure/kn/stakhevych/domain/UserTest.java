package ua.nure.kn.stakhevych.domain;

import static org.junit.Assert.*;

import java.util.Calendar;

import java.util.Date;

import junit.framework.TestCase;



public class UserTest extends TestCase {

	// создаем  экземпляр юзер
	private User user;
	private  static Date dateOfBirthd;
	Calendar calendar;
	
	private static final Long ID = 1L;
	private static final String MY_FIRST_NAME = "John";
    private static final String MY_LAST_NAME  = "Doe";
    private static final String MY_DATE_BIRTH_STRING = "18-12-1999";
    private static final String MY_DATE_PATTERN = "dd-MMM-yyyy";
    private static int MY_BIRTH_YEAR = 1999;
    private static int MY_BIRTH_MONTH = 12;
    private static int MY_BIRTH_DAY = 18;
    
    
	 protected void setUp() throws Exception {
	        super.setUp();
	        user = new User(ID,MY_FIRST_NAME, MY_LAST_NAME, dateOfBirthd);
	        
	        calendar = Calendar.getInstance();
	       
	}
	 
	 public void testGetFullName() {
		 String resultExpected = MY_FIRST_NAME + " , " + MY_LAST_NAME;
	        assertEquals(resultExpected,user.getFullName());
	      
	    }
	 
	 
	 

	    public void testAgeNow() {
	        int ageExpected = 19;

	        calendar.set(MY_BIRTH_YEAR,MY_BIRTH_MONTH,calendar.get(Calendar.DAY_OF_MONTH));

	        user.setDateOfBirth(calendar.getTime());

	        int ageActual = user.getAge();

	        assertEquals(ageExpected, ageActual);
	    }

	   
	    public void testAgeBirthdayOneDayAfterToday() {
	        int ageExpected = 19;

	        calendar.set(MY_BIRTH_YEAR,MY_BIRTH_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
	        calendar.add(Calendar.DAY_OF_MONTH, 1);

	        user.setDateOfBirth(calendar.getTime());

	        int ageActual = user.getAge();
	        assertEquals(ageExpected, ageActual);
	    }


	    public void testAgeOneMonthAheadFromCurrentMonth() {
	        int ageExpected =19;

	        calendar.set(MY_BIRTH_YEAR,MY_BIRTH_MONTH,MY_BIRTH_DAY);
	        calendar.add(Calendar.MONTH, 1);

	        user.setDateOfBirth(calendar.getTime());

	        int ageActual = user.getAge();
	        assertEquals(ageExpected, ageActual);
	    }

	    
	    public void testAgeOneYearBeforeCurrentMonth() {
	        int ageExpected = 20;

	        calendar.set(MY_BIRTH_YEAR,MY_BIRTH_MONTH,MY_BIRTH_DAY);
	        calendar.add(Calendar.YEAR, -1);

	        user.setDateOfBirth(calendar.getTime());

	        int ageActual = user.getAge();
	        assertEquals(ageExpected, ageActual);
	    }

	   
	    public void testNewbornAge() {
	        int ageExpected = 0;

	        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

	        user.setDateOfBirth(calendar.getTime());

	        int ageActual = user.getAge();
	        assertEquals(ageExpected, ageActual);
	    }
	 
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
