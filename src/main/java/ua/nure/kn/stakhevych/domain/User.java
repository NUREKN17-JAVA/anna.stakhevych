package ua.nure.kn.stakhevych.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;




public class User {

	private Long id ;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    
	
    public User() {
    }
	
	public User(Long id, String firstName, String lastName, Date dateOfBirth) {
		// TODO Auto-generated constructor stub
		this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
		
	public String getFullName() {
		StringBuffer sb = new StringBuffer(20);
		return sb.append(getFirstName()).append(" , ").append(getLastName()).toString();
		
	}
	public int getAge() {
		// TODO Auto-generated method stub
		LocalDate birthLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentLocalDate = LocalDate.now(ZoneId.systemDefault());
        return (int) ChronoUnit.YEARS.between(birthLocalDate, currentLocalDate);
	}

}
