package clases;

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
}
