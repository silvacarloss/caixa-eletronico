package br.edu.ifsp.btv;

import br.edu.ifsp.btv.models.User;

public class CurrentApplication {

    private static CurrentApplication instance;
    private User loggedUser;

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
}
