package br.edu.ifsp.btv.Controllers;

import java.util.List;

public class Dispenser {

    private double withdrawLimit;
    private double disponibleAmount;
    private List<MoneyNote> availableNotes;

    Dispenser(double withdrawLimit, double disponibleAmount, List<MoneyNote> moneyNotes){
        this.withdrawLimit = withdrawLimit;
        this.disponibleAmount = disponibleAmount;
        this.availableNotes = moneyNotes;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public double getDisponibleAmount() {
        return disponibleAmount;
    }

    public void setDisponibleAmount(double disponibleAmount) {
        this.disponibleAmount = disponibleAmount;
    }

    public List<MoneyNote> getAvailableNotes() {
        return availableNotes;
    }

    public void setAvailableNotes(List<MoneyNote> availableNotes) {
        this.availableNotes = availableNotes;
    }

}
