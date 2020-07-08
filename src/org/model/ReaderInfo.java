package org.model;


public class ReaderInfo {
	private String username;
	private String name;
	private String password;
	private String mail;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "ReaderInfo{" +
				"username='" + username + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", mail='" + mail + '\'' +
				'}';
	}
}
