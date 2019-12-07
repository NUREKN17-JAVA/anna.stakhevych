package ua.nure.kn.stakhevych.domain.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;
import ua.nure.kn.stakhevych.domain.util.Messages;

public class EditPanel extends JPanel implements ActionListener  {

	private final MainFrame parent;
	
	private Color bgColor;
	
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	private User user;

	public EditPanel(MainFrame frame) {
	        this.parent = frame;
	        user = parent.getSelectUser();
	        initialize();
	}

	private void initialize() {
		this.setName("editPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	
	}

	private JPanel getButtonPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getOkButton(), null);
		}
		return buttonPanel;
	}

	private JButton getOkButton() {
		if (okButton == null){
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	private JButton getCancelButton() {
		// TODO Auto-generated method stub
		if (cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JPanel getFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();			
			fieldPanel.setLayout(new GridLayout(3,2));
			addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private void addLabeledField(JPanel panel, String name, JTextField field) {
    	JLabel label = new JLabel(name);
        label.setLabelFor(field);
        panel.add(label);
        panel.add(field);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		//String actionCommand = e.getActionCommand();
				if("ok".equalsIgnoreCase(e.getActionCommand())){ //$NON-NLS-1$
					User user = new User();
					String firstName = getFirstNameField().getText();
		        	String lastName = getLastNameField().getText();
		        	String dateOfBirth = getDateOfBirthField().getText();

					if(!firstName.isEmpty()&&!lastName.isEmpty()&&!dateOfBirth.isEmpty()){
		        		user.setFirstName(firstName);
		        		user.setLastName(lastName);
		        		DateFormat format = DateFormat.getDateInstance();
		        		try{
		        			Date date = format.parse(getDateOfBirthField().getText());
		        			user.setDateOfBirth(date);
		        		}catch(ParseException e1){
		        			JOptionPane.showMessageDialog(this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		        		}
		        		
		        		try{
		        			parent.getDao().update(user);
		        		}catch(DataBaseException e1){
		        			JOptionPane.showMessageDialog(this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		        		}
		        		
		        		clearFields();
		        		this.setVisible(false);
		        		parent.showBrowsePanel();
		        	}else{
		        		JOptionPane.showMessageDialog(this, Messages.getString("EditPanel.fill_fields"),"Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		    
		        	}
		        	
		        } else if ("cancel".equalsIgnoreCase(e.getActionCommand())) {
		            clearFields();
		            this.setVisible(false);
		            parent.showBrowsePanel();
		        }
		    }

			
			private void clearFields() {
				getFirstNameField().setText(this.user.getFirstName());
				getFirstNameField().setBackground(bgColor);
				
				getLastNameField().setText(this.user.getLastName());
				getLastNameField().setBackground(bgColor);
				
				Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				getDateOfBirthField().setText(formatter.format(this.user.getDateOfBirth()));
				getDateOfBirthField().setBackground(bgColor);
			}

			private JTextField getDateOfBirthField() {
				// TODO Auto-generated method stub
				if(dateOfBirthField == null){
					dateOfBirthField = new JTextField();			
					dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
				}
				return dateOfBirthField;
			}

			private JTextField getLastNameField() {
				// TODO Auto-generated method stub
				if(lastNameField == null){
					lastNameField = new JTextField();			
					lastNameField.setName("lastNameField"); //$NON-NLS-1$
				}
				return lastNameField;
			}

			private JTextField getFirstNameField() {
				if(firstNameField == null){
					firstNameField = new JTextField();			
					firstNameField.setName("firstNameField"); //$NON-NLS-1$
				}
				return firstNameField;
			}

}
