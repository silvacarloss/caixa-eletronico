package main.br.edu.ifsp.btv;

import java.util.ArrayList;
import java.util.HashMap;

import main.br.edu.ifsp.btv.models.User;

public class CurrentApplication {

    private static CurrentApplication instance;
    private static User loggedUser = null;
    private static ArrayList<User> userList;
    private static Integer[] bankNotes = { };
    private static HashMap<String, Double> availableNotes;
    
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
	
	public void setBanknotes(Integer[] notes) {
		this.bankNotes = notes;
	}

	public Integer[] getBanknotes() {
		return bankNotes;
	}

	public HashMap<String, Double> getAvailableNotes() {
		return availableNotes;
	}

	public void setAvailableNotes(HashMap<String, Double> availableNotes) {
		CurrentApplication.availableNotes = availableNotes;
	}
}
