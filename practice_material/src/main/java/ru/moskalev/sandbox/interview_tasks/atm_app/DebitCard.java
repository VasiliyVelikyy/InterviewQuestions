package ru.moskalev.sandbox.interview_tasks.atm_app;

public class DebitCard {
    private String clientId;
    private int clientBalance;

    public DebitCard(String clientId, int clientBalance) {
        this.clientId = clientId;
        this.clientBalance = clientBalance;
    }

    public String getClientId() {
        return clientId;
    }

    public int getClientBalance() {
        return clientBalance;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientBalance(int clientBalance) {
        this.clientBalance = clientBalance;
    }
}
