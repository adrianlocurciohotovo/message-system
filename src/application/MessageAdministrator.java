package application;

import java.util.*;

public class MessageAdministrator implements Observable{
	
	private ArrayList<Message> messages = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();
	
	private MessageAdministrator() {}
	
	
	private static class RegistryHolder {
		static MessageAdministrator INSTANCE = new MessageAdministrator();
	}
	
	public static MessageAdministrator getInstance() {
		return RegistryHolder.INSTANCE;
	}
	
	
	public void addMessage(Message message) {
		this.messages.add(message);
		this.notifyObservers();
	}


	@Override
	public void attach(Observer observer) {
		this.observers.add(observer);
	}


	@Override
	public void detach(Observer observer) {
		this.observers.remove(observer);
	}


	@Override
	public void notifyObservers() {
		this.observers.forEach(observer -> observer.updated(this.messages.get(this.messages.size() - 1)));
	}
	
	public ArrayList<Message> getMessages() {
		return this.messages;
	}
	
	
}
