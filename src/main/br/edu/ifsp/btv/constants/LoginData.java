package br.edu.ifsp.btv.constants;

import br.edu.ifsp.btv.models.User;

import java.util.ArrayList;

public class LoginData {
	public static ArrayList<User> listUsers = new ArrayList<>();

	private static User user1 = new User("Carlos", "12345","001", "001-1", 420.0);
	private static User user2 = new User("Ademar", "22222","001", "002-1", 220.0);
	private static User user3 = new User("João", "11111","001", "003-1", 20.0);
	private static User user4 = new User("Vitor", "55555","001", "004-1", 0.0);
	private static User user5 = new User("Marlene", "33444","001", "005-1", 300.0);
	private static User user6 = new User("Lívia", "11222","001", "006-1", 1420.0);

	public void fillLoginDataUserList(){
		listUsers.add(user1);
		listUsers.add(user2);
		listUsers.add(user3);
		listUsers.add(user4);
		listUsers.add(user5);
		listUsers.add(user6);
	}

	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	public User searchUser(){
		User user = null;
		return user;
	}
}
