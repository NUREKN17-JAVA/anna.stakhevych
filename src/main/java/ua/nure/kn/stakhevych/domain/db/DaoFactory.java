package ua.nure.kn.stakhevych.domain.db;



import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public abstract class DaoFactory {
	
	private static final String SETTING_PROPERTIES = "settings.properties";

	protected static final String USER_DAO = "dao.ua.nure.kn.stakhevych.domain.db.UserDao";
	private static final String DAO_FACTORY = "dao.factory";
	
	protected static Properties properties;
	
	private static DaoFactory instance;
	
	
	protected DaoFactory() {		
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static{
    	properties = new Properties();
        try {
           properties.load(DaoFactory.class.getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
	} catch (IOException e) {
            throw new RuntimeException(e);
        }
}
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null){
			Class factoryClass;
	    		try {
	    		factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	   	}
	       return instance;
	}
	
	/*
	protected DaoFactory() {
		
	}
	
	public static void init(Properties prop){
		properties = prop;
		instance = null;
	}
	
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	
	public abstract UserDao getUserDao(); */

	public static void init(Properties prop) {
		properties = prop;
		instance = null;
	}
	
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	
	public abstract UserDao getUserDao();
}
