package ua.nure.kn.stakhevych.domain.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;
import ua.nure.kn.stakhevych.domain.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
		// TODO Auto-generated constructor stub
		parent = frame;
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		this.setName("browsePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		// TODO Auto-generated method stub
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton(),null);
			buttonPanel.add(getEditButton(),null);
			buttonPanel.add(getDeleteButton(),null );
			buttonPanel.add(getDetailsButton(),null);
			
		}
		return buttonPanel;
	}

	
	private JButton getDetailsButton() {
		// TODO Auto-generated method stub
		if(detailsButton == null){
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details")); //$NON-NLS-1$
			detailsButton.setName("detailsButton"); //$NON-NLS-1$
			detailsButton.setActionCommand("details"); //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		// TODO Auto-generated method stub
		if(deleteButton == null){
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}
//	private JButton getEditButton() {
//		// TODO Auto-generated method stub
//		if(editButton == null){
//			editButton = new JButton();
//			editButton.setText(Messages.getString("BrowsePanel.edit")); //$NON-NLS-1$
//			editButton.setName("editButton"); //$NON-NLS-1$
//			editButton.setActionCommand("edit"); //$NON-NLS-1$
//			editButton.addActionListener(this);
//		}
//		return editButton;
//	}

	private JButton getAddButton() {
		// TODO Auto-generated method stub
		if(addButton == null){
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); //$NON-NLS-1$
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
			addButton.addActionListener(this);
			
		}
		return addButton;
	}
	private JButton getEditButton() {
		// TODO Auto-generated method stub
		if(editButton == null){
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); //$NON-NLS-1$
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JScrollPane getTablePanel() {
		// TODO Auto-generated method stub
		if(tablePanel ==null){
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		// TODO Auto-generated method stub
		if(userTable == null){
			userTable = new JTable();
			userTable.setName("userTable"); //$NON-NLS-1$
		}
		
		return userTable;
	}

	public void initTable() {
		UserTabelModel model;
		try {
			model = new UserTabelModel(parent.getDao().findAll());
		} catch (DataBaseException e) {
			model = new UserTabelModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		//UserTabelModel model =  new UserTabelModel(parent.getDao().findAll());
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String actionCommand = e.getActionCommand();
		if("add".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
		}
		
		if("edit".equalsIgnoreCase(actionCommand)){ //$NON-NLS-1$
			int selectedRow = userTable.getSelectedRow();
			int selectedColumn = userTable.getSelectedColumn();
			if (selectedColumn !=-1 || selectedRow!=-1){
				this.setVisible(false);
				parent.showEditPanel();
			}else{
				JOptionPane.showMessageDialog(this,Messages.getString("BrowsePanel.choosing_user1"),"Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
		}
		
		 if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
	            User selectedUser = getSelectUser();
	            if (selectedUser != null) {
	                int result = JOptionPane.showConfirmDialog(this, Messages.getString("BrowsePanel.accept_deleting"), //$NON-NLS-1$
	                        "Confirm deleting", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
	                if (result == JOptionPane.YES_OPTION) {
	                    try {
	                        parent.getDao().delete(selectedUser);
	                        getUserTable().setModel(new UserTabelModel(parent.getDao().findAll()));
	                    } catch (DataBaseException ex) {
	                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	                    }
	                }
	            }
	        }
	        if ("details".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
	        	User user = getSelectUser();
				this.setVisible(false);
				parent.showDetailsPanel(user);
	        }
		
	}

	public User getSelectUser() {
		int selectedRow = getUserTable().getSelectedRow();
		int idColumn = 0;
		
		Long userId = null;
		User user = null;
		if(selectedRow==-1){
			JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.choosing_user2"),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
		userId = (Long) userTable.getValueAt(userTable.getSelectedRow(),idColumn);
		try{
			user = parent.getDao().find(userId);
		}catch(DataBaseException e){
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		}
		return user;

	}

}
