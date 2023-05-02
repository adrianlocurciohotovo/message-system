package application;

public class Message {
	
	private String content;
	
	private User from;
	private User target;
	
	Message(User from, User target, String content) {
		this.from = from;
		this.target = target;
		this.content = content;
	}
	
	public User getFrom() {
		return this.from;
	}

	public User getTarget() {
		return target;
	}
	
	public String getContent() {
		return content;
	}
	
}
