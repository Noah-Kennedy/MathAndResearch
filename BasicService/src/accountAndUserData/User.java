package accountAndUserData;

import java.util.ArrayList;
import java.util.Random;

import devices.Device;

public class User {
	private int passwordHash;
	private String userName;
	private ArrayList<String> tokens;
	private String customerID;
	
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 64) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	public User(String name, String password){
		userName = name;
		passwordHash = password.hashCode();
		customerID = getSaltString();
		tokens = new ArrayList<String>();
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getCustomerID(){
		return customerID;
	}
	
	private int getPasswordHash(){
		return passwordHash;
	}
	
	public boolean isValidLoggin(String name, String password){
		if(this.getUserName().equals(name) && this.getPasswordHash() == password.hashCode()) return true;
		else return false;
	}
	
	public void attemptLoggin(Device d){
		if(isValidLoggin(d.sendUserName(),d.sendPassword())){
			tokens.add(getSaltString());
			d.recieveToken(tokens.get(tokens.size() - 1));
		}else{
			d.recieveToken("Failed Authentication");
		}
	}
}
