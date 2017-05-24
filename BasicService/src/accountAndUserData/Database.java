package accountAndUserData;

import java.util.ArrayList;

public class Database {
	private ArrayList<User> users;
	
	public Database(){
		users = new ArrayList<User>();
	}
	
	public void createNewUser(String userName, String password){
		users.add(new User(userName, password));
	}
}
