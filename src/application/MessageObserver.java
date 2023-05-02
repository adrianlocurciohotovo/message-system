package application;

public class MessageObserver implements Observer{

	@Override
	public void updated(Message message) {
		System.out.println("****** NOTIFICATION START******");
		System.out.println(message.getTarget().getUsername() + " received a message from: " + message.getFrom().getUsername() + " and it says: " + message.getContent());
		System.out.println("****** NOTIFICATION END  ******");
	}

}
