package ua.nure.kn.stakhevych.domain.agent;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class SearchRequestBehaviour extends Behaviour {

	private String firstName;
	private String lastName;
	private AID[] aids;

	public SearchRequestBehaviour(AID[] aids, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.aids =aids;	
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		if(aids != null) {
			ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
			message.setContent(firstName + "," + lastName);
			
			for(int i =0; i< aids.length; i++) {
				message.addReceiver(aids[i]);
			}
			myAgent.send(message);
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
