package clases;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Pasajero extends Persona{
    private boolean assistCard;
    private boolean priorityPass;

    /// CONSTRUCTOR
    public Pasajero() {
    }

    /// GETTERS Y SETTERS
    public boolean isAssistCard() {
        return assistCard;
    }

    public void setAssistCard(boolean assistCard) {
        this.assistCard = assistCard;
    }

    public boolean isPriorityPass() {
        return priorityPass;
    }

    public void setPriorityPass(boolean priorityPass) {
        this.priorityPass = priorityPass;
    }


    @Override
    public String toString() {
        return "Pasajero{" +
                "assistCard=" + assistCard +
                ", priorityPass=" + priorityPass +
                "} " + super.toString();
    }
}
