package main.br.edu.ifsp.btv.models;

public class User {
    private String name, password;
    private boolean loginState;
    private String agency;
    private String cc;
    private double disponibleValue;

    public User(String name, String password, String agency, String cc, double disponibleValue){
        this.name = name;
        this.password = password;
        this.agency = agency;
        this.cc = cc;
        this.disponibleValue = disponibleValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
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

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public void logout(){
        this.loginState = false;
    }

    public boolean getLoginState() {
        return loginState;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public boolean withdraw(double valueToWithdraw){
        return true;
    }
}
