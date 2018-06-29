package main.br.edu.ifsp.btv.models;

import java.util.HashMap;
import java.util.List;

public class Slot {

    private double disponibleAmount;
    private HashMap<String, Double> availableNotes;

    public Slot(double withdrawLimit, double disponibleAmount, HashMap<String, Double> moneyNotes){
        this.disponibleAmount = disponibleAmount;
        this.availableNotes = moneyNotes;
    }

    public double getDisponibleAmount() {
        return disponibleAmount;
    }

    public void setDisponibleAmount(double disponibleAmount) {
        this.disponibleAmount = disponibleAmount;
    }

    public HashMap<String, Double> getAvailableNotes() {
        return availableNotes;
    }

    public void setAvailableNotes(HashMap<String, Double> availableNotes) {
        this.availableNotes = availableNotes;
    }

    public double getMoneyQuantity(){ return 0.0; }
}
