package ua.nure.kn.stakhevych.domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private static final String CONNECTION_DRIVER = "connection.driver";
	private static final String CONNECTION_URL = "connection.url";
	private static final String CONNECTION_PASSWORD = "connection.password";
	private static final String CONNECTION_USER = "connection.user";
	private String driver;
	private String url;
	private String user;
	private String password;
	
	ConnectionFactoryImpl() {}
	

	public ConnectionFactoryImpl(Properties properties) {
		 user = properties.getProperty(CONNECTION_USER);
		 password = properties.getProperty(CONNECTION_PASSWORD);
		 url = properties.getProperty(CONNECTION_URL);
		 driver = properties.getProperty(CONNECTION_DRIVER);
	}

	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public Connection createConnection() throws DataBaseException {
				try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataBaseException(e);
		}
	}

}
