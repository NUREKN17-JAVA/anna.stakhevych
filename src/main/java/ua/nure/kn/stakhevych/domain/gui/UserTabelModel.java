package ua.nure.kn.stakhevych.domain.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.util.Messages;

public class UserTabelModel extends AbstractTableModel {

	private static final String[] COLUMN_NAME = {Messages.getString("UserTabelModel.id"), Messages.getString("UserTabelModel.first_name"), Messages.getString("UserTabelModel.last_name")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	private List users = null;
	public UserTabelModel(Collection users){
		this.users = new ArrayList(users);
	}
	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAME.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAME[column];
	}
	@Override
	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}
	
	public User getValueAt(int rowIndex) {
		// TODO Auto-generated method stub
		return (User) users.get(rowIndex);

	}

}
