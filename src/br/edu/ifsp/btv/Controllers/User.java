package br.edu.ifsp.btv.Controllers;

public class User {
    private int userId;
    private String name, password;
    private boolean loginState;
    private double disponibleValue;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public double getDisponibleValue() {
        return disponibleValue;
    }

    public void setDisponibleValue(double disponibleValue) {
        this.disponibleValue = disponibleValue;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void logout(){
        this.loginState = false;
    }

    public boolean login(){
        return true;
    }

    public boolean withdraw(double valueToWithdraw){
        return true;
    }
}
