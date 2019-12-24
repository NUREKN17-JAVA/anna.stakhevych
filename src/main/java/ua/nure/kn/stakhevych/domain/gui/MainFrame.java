package ua.nure.kn.stakhevych.domain.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.UserDao;
import ua.nure.kn.stakhevych.domain.util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
	private UserDao dao;
	private EditPanel editPanel;
	private JPanel detailsPanel;
	
	public MainFrame(){
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}
	
	public UserDao getDao() {
		return dao;
	}
	private void initialize() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
	private JPanel getContentPanel() {
		// TODO Auto-generated method stub
		if(contentPanel == null){
			contentPanel =  new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}
	private JPanel getBrowsePanel() {
		// TODO Auto-generated method stub
		if(browsePanel==null){
			browsePanel = new BrowsePanel(this);
			
		}
		 ((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}
	
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
		
	}
	
	private AddPanel getAddPanel() {
		// TODO Auto-generated method stub
		if(addPanel == null){
			addPanel = new AddPanel(this);			
		}
		return addPanel;
	}
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	} 

	private EditPanel getEditPanel() {
		// TODO Auto-generated method stub
		if(editPanel == null){
			editPanel = new EditPanel(this);			
		}
		return editPanel;
	}
	
	public void showEditPanel() {
		showPanel(getEditPanel());
	}
	
	public User getSelectUser() {
		return ((BrowsePanel)browsePanel).getSelectUser();
	}
	
	public void showDetailsPanel(User user) {
		JPanel detailsPanel = getDetailsPanel();
        ((ShowDeleilsPanel) detailsPanel).showUserDetails(user);
        showPanel(detailsPanel);
		
	}
	
	private JPanel getDetailsPanel() {
		if (detailsPanel == null){
			detailsPanel = new ShowDeleilsPanel(this);
		}
		return detailsPanel;
	}
	
	private void showPanel(JPanel panel) {
		// TODO Auto-generated method stub
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true); 
		panel.repaint();
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

	

}
