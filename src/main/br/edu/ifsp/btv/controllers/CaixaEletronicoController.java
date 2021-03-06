package main.br.edu.ifsp.btv.controllers;

import main.br.edu.ifsp.btv.CurrentApplication;
import main.br.edu.ifsp.btv.constants.LoginData;
import main.br.edu.ifsp.btv.models.User;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class CaixaEletronicoController {
	
	LoginData loginData = new LoginData();

    public boolean login(String agency, String cc, String password) {
        loginData.fillLoginDataUserList();
        CurrentApplication.getInstance().setUserList(loginData.getListUsers());
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
    	loginData.fillLoginDataUserList();
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
                User updatedUser = CurrentApplication.getInstance().getLoggedUser();
                updatedUser.setDisponibleValue(currentUserValue - amount);
                user.setDisponibleValue(destinationUserValue + amount);
                updateActualUser(updatedUser);
                return true;
            }
        }
        return false;
    }
    
    public void updateActualUser(User user) {
    	CurrentApplication.getInstance().setLoggedUser(user);
    }

    public boolean depositCash(String agency, String cc, double amount){
        loginData.fillLoginDataUserList();
        CurrentApplication.getInstance().setUserList(loginData.getListUsers());
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
    
    public boolean userExists(String agency, String cc) {
    	loginData.fillLoginDataUserList();
    	//todo remove filldataloginuserlist
    	CurrentApplication.getInstance().setUserList(loginData.getListUsers());
        ArrayList<User> listUsers = loginData.getListUsers();

        for (User user : listUsers){
            if (user.getAgency().equals(agency) &&
                user.getCc().equals(cc)){
                return true;
            }
        }
        
        return false;
    }
    
	public boolean withdraw(double withdrawnValue) {
		HashMap<String, Double> retrievedBankCash = new HashMap<String, Double>();
		double wholeValue = withdrawnValue;
		User user = CurrentApplication.getInstance().getLoggedUser();
		double userDisponibleValue = CurrentApplication.getInstance().getLoggedUser().getDisponibleValue();
		if(userDisponibleValue < withdrawnValue) {
			return false;
		}
		for (double valueNote : CurrentApplication.getInstance().getBanknotes()) {
			if(withdrawnValue >= valueNote){
				double retrievedValue = withdrawnValue / valueNote;
				retrievedBankCash.put(String.valueOf(valueNote), retrievedValue);
				withdrawnValue = withdrawnValue % valueNote;
			}
		}
		
		if(withdrawnValue > 0) {
			JOptionPane.showMessageDialog(null, 
					"Não foi possível retirar todo o dinheiro solicitado, faltaram R$: " 
							+ Double.toString(withdrawnValue), 
					"Erro", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"Quantia retirada com sucesso. ", 
					"Erro", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		user.setDisponibleValue((userDisponibleValue - wholeValue) + withdrawnValue);
		updateActualUser(user);
		return true;
	}
}
