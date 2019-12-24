package ua.nure.kn.stakhevych.domain.db;

public class DaoFactoryImpl extends DaoFactory {

	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	
	@Override
	public UserDao getUserDao(){
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
		return result;
	}
	
	/*
	 * @Override
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}

	@Override
	public UserDao getUserDao() {
		UserDao result = null;
	    try {
	        Class clazz = Class.forName(properties.getProperty(USER_DAO));
	        result = (UserDao) clazz.newInstance();
	        result.setConnectionFactory(getConnectionFactory());
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return result;
	}*/
}
