package application;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Client {
	public static void main(String[] args) {
		
		MessageAdministrator messageAdministrator = MessageAdministrator.getInstance();
		
		messageAdministrator.attach(new MessageObserver());
		
		var users = loadUsers();
				
		User authenticatedUser = null;
		
		User targetUser = null;
		
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			
			
			do {
				
				
				var usersToShow = users;
				
				if(authenticatedUser != null) {
					// Filter already selected user.
					
						
					ArrayList<User> filteredUsers = new ArrayList<>();
					
					for(User currentUser: users) {
						if(!currentUser.equals(authenticatedUser)) {
							filteredUsers.add(currentUser);
						}
					}
					
					usersToShow = filteredUsers;
				}
				
				System.out.println("Choose your user, options are: ");
				
				showUserOptions(usersToShow);

					
				try {
					String enteredNumber = reader.readLine();
					
					User selectedUser = users.get(Integer.valueOf(enteredNumber) - 1);
					
					if(selectedUser != null) {
						if(authenticatedUser == null) {
							authenticatedUser = selectedUser;
							System.out.println("Authenticated user: " + authenticatedUser.getUsername());
							System.out.println("");
						} else {
							targetUser = selectedUser;
							System.out.println("Target user: " + targetUser.getUsername());
						}
					} else {
						System.out.println("User not found.");
					}
					
					
					
				} catch(IndexOutOfBoundsException e) {
					System.out.println("Select one of available options.");
				} catch(NumberFormatException e) {
					System.out.println("You should enter a valid number.");
				}
				
				
				
				if(authenticatedUser != null && targetUser != null) {					
					String message = null;
					
					do {
						
						System.out.println("Enter your message to " + targetUser.getUsername() + "bellow");
						
						message = reader.readLine();
						
						if(!message.equals("")) {
							messageAdministrator.addMessage(new Message(authenticatedUser, targetUser, message));
							
							
							String answer = null;
							
							do {
								
								System.out.println("Do you want to send another message to " + targetUser.getUsername() + "? (yes/no)");
								
								answer = reader.readLine();
								
								if(answer.equals("yes")) {
									message = null;
									
								}
							
							}while(answer == null || !(answer.equals("yes") || answer.equals("no")));
						}
												
					}while(message == null || message.equals(""));
				}
				
				
				if((authenticatedUser != null && targetUser != null)) {
					
					System.out.println("Do to change your user ?, type 1 to continue or another key to exit.");
					
					String answer = reader.readLine();
					
					if(answer.equals("1")) {
						authenticatedUser = null;
						targetUser = null;
					}
				}
				
				
				
				
			} while(authenticatedUser == null || targetUser == null);
			
			
			
			System.out.println("******CONVERSATION SUMMARY*******");
			
			
			for(Message message: messageAdministrator.getMessages()) {
				System.out.println(message.getTarget().getUsername() + " received a message from " + message.getFrom().getUsername() + " and it says: " + message.getContent());
			}
			
			
		} catch(IOException e) {
			System.out.println("Something failed during console read: " + e.getMessage());
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		
		
		
	}
	
	public static void showUserOptions(ArrayList<User> users) {
		// Iterator pattern
		int currentIteratorPos = 1;
		
		for(User user: users) {
			System.out.print(currentIteratorPos + ". " + user.getUsername() + "\n");
			currentIteratorPos++;
		}
	}
	
	
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<>();
		
		users.add(new User("Adrian"));
		users.add(new User("Leandro"));
		users.add(new User("Martin"));
		
		return users;
	}
	
	
}
