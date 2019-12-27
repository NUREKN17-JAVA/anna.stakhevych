package ua.nure.kn.stakhevych.domain.agent;

import java.rmi.ServerException;
import java.util.Collection;

import javax.security.sasl.SaslException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;
import ua.nure.kn.stakhevych.domain.agent.SearchGui;

public class SearchAgent extends Agent {

	private AID[] aids;
	private SearchGui gui = null; 


	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
//		System.out.println(getAID().getName()+ " started");
		
		gui = new SearchGui(this);
		gui.setVisible(true);
		
		DFAgentDescription description = new DFAgentDescription();
		description.setName(getAID());
		ServiceDescription serviseDescription = new ServiceDescription();
		serviseDescription.setName("JADE-searching");
		serviseDescription.setType("searching");
		description.addServices(serviseDescription);
		try {
			DFService.register(this, description);
         } catch (FIPAException e) {
             e.printStackTrace();
         }
		
		addBehaviour(new TickerBehaviour(this, 60000) {

			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				 DFAgentDescription description = new DFAgentDescription();
//	                description.setName(getAID());
	                ServiceDescription serviceDescription = new ServiceDescription();
//	                serviceDescription.setName("JADE-Searching");
	                serviceDescription.setType("searching");
	                description.addServices(serviceDescription);
	                try {
	                    DFAgentDescription[] descriptions = DFService.search(myAgent, description);
	                   aids = new AID[descriptions.length];
	                    for (int i = 0; i < descriptions.length; i++) {
	                    	 DFAgentDescription d = descriptions[i];
	                        aids[i] = d.getName();
	                    }
	                    	                
	                } catch (FIPAException e) {
	                    e.printStackTrace();
	                }
	            }
			
		});
		
		addBehaviour(new RequestServer());
		System.out.println("Agent " + getAID().getName() + " is ready! Awaiting orders!");
	    
	}

	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		System.out.println(getAID().getName() + " terminated.");
		try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
		gui.setVisible(false);
		gui.dispose();
		super.takeDown();
	}

	public void search(String firstName, String lastName) throws SearchException {
		try {
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if(users.size() > 0 ) {
			showUsers(users);
			}else {
				// TODO послать другим агентамы
				addBehaviour(new SearchRequestBehaviour(aids, firstName, lastName));
			}
		}catch(DataBaseException e){
			throw new SearchException(e);
		}
	}
	
  void showUsers(Collection user) {
		// TODO Отобразить найденых пользвателей
	  gui.addUsers(user);
	}
}
