package ua.nure.kn.stakhevych.domain.db;

import java.util.Collection;

import ua.nure.kn.stakhevych.domain.User;

public interface UserDao {
	User create(User user) throws DataBaseException;
	
	void update(User user) throws DataBaseException;
	
	void delete(User user) throws DataBaseException;
	
	User find(Long id) throws DataBaseException;
	
	Collection<User> findAll() throws DataBaseException;
	//Collection findAll() throws DataBaseException;
	
	Collection find(String firstName, String lastName) throws DataBaseException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);

/*	 User create(User user) throws DataBaseException;

	   void update(User user) throws DataBaseException;

	   void delete(User user) throws DataBaseException;

	   User find(Long id) throws DataBaseException;

	   Collection findAll() throws DataBaseException;
		
		void setConnectionFactory(ConnectionFactory connectionFactory);*/

}