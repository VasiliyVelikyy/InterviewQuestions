package sandbox.interview_tasks.bankomat_app;

public class DebitCard {
    private String clientId;
    private int clientBalance;

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
