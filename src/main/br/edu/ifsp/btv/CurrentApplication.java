package main.br.edu.ifsp.btv;

import java.util.ArrayList;

import main.br.edu.ifsp.btv.models.User;

public class CurrentApplication {

    private static CurrentApplication instance;
    private static User loggedUser = null;
    private static ArrayList<User> userList;

    private CurrentApplication(){

    }

    public static synchronized CurrentApplication getInstance(){
        if(instance==null){
            return new CurrentApplication();
        }
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		CurrentApplication.userList = userList;
	}
}
