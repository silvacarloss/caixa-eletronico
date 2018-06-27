package br.edu.ifsp.btv.controllers;

import br.edu.ifsp.btv.CurrentApplication;
import br.edu.ifsp.btv.constants.LoginData;
import br.edu.ifsp.btv.models.User;

import java.util.ArrayList;

public class CaixaEletronicoController {

    public boolean login(String agency, String cc, String password) {
        LoginData loginData = new LoginData();
        ArrayList<User> listUsers = loginData.getListUsers();

        for (User user : listUsers){
            if (user.getPassword().equals(password) &&
                    user.getAgency().equals(agency) &&
                    user.getCc().equals(cc)){
                CurrentApplication.getInstance().setLoggedUser(user);
                return true;
            }
        }

        return false;
    }

    public boolean transferCash(String agency, String cc, double amount, String password){
        LoginData loginData = new LoginData();
        ArrayList<User> listUsers = loginData.getListUsers();

        if (!CurrentApplication.getInstance().getLoggedUser().getPassword().equals(password) ||
            CurrentApplication.getInstance().getLoggedUser().getDisponibleValue() < amount){
            return false;
        }

        for (User user : listUsers){
            if (user.getAgency().equals(agency) && user.getCc().equals(cc)){
                double destinationUserValue = user.getDisponibleValue();
                double currentUserValue = CurrentApplication
                        .getInstance()
                        .getLoggedUser()
                        .getDisponibleValue();
                CurrentApplication.getInstance().getLoggedUser().setDisponibleValue(currentUserValue - amount);
                user.setDisponibleValue(destinationUserValue + amount);
                return true;
            }
        }
        return false;
    }

    public boolean depositCash(String agency, String cc, double amount){
        LoginData loginData = new LoginData();
        ArrayList<User> listUsers = loginData.getListUsers();

        for (User user : listUsers){
            if (user.getAgency().equals(agency) && user.getCc().equals(cc)){
                double destinationUserValue = user.getDisponibleValue();
                user.setDisponibleValue(destinationUserValue + amount);
                return true;
            }
        }
        return false;
    }
}
