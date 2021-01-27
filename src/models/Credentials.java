package models;

public class Credentials {

	String username;
	String password;

	public Credentials() {

	}

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
