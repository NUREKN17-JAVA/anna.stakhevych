package ua.nure.kn.stakhevych.domain.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;

public class RequestServer extends CyclicBehaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub

		ACLMessage message = myAgent.receive();
		if(message != null) {
			if(message.getPerformative() == ACLMessage.REQUEST){
				myAgent.send(createReply(message));
			}else {
				Collection users = parseMessage(message);
				((SearchAgent) myAgent).showUsers(users);
			}
		}else {
			block();
		}
	}

	private Collection<User> parseMessage(ACLMessage message) {
		// TODO Auto-generated method stub
		 Collection users = new ArrayList();
	        String content = message.getContent();
	        if (content != null) {
	            StringTokenizer tokenizerl = new StringTokenizer(content, ";");
	            while (tokenizerl.hasMoreTokens()) {
	            	String userInfo = tokenizerl.nextToken();
	            	StringTokenizer tokenizerl2 = new StringTokenizer(userInfo, ",");
	            	String id = tokenizerl2.nextToken();
	            	String firstName =tokenizerl2.nextToken();
	            	String lastName =tokenizerl2.nextToken();
	            	users.add(new User(new Long(id), firstName, lastName, null));
	               
	            }
	        }
	        return users;
	}

	private ACLMessage createReply(ACLMessage message) {
		// TODO Auto-generated method stub
		 ACLMessage reply = message.createReply();
	        String content = message.getContent();
	        StringTokenizer tokenizer = new StringTokenizer(content, ",");
	        if (tokenizer.countTokens() == 2) {
	            String firstName = tokenizer.nextToken();
	            String lastName = tokenizer.nextToken();
	            Collection<User> users = null;
	            try {
	            	users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
	            } catch (DataBaseException e) {
	                e.printStackTrace();
	                users = new ArrayList (0);
	            }
	            StringBuffer buffer = new StringBuffer();
	            for (User user : users) {
	                buffer.append(user.getId()).append(",");
	                buffer.append(user.getFirstName()).append(",");
	                buffer.append(user.getLastName()).append(";");
	            }
	            reply.setContent(buffer.toString());
	        }
	        return reply;
	    }
	

}
