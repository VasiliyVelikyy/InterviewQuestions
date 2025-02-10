package sandbox.interview_tasks.bankomat_app;

public enum Banknote {
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int nominate;

    Banknote(int nominate) {
        this.nominate = nominate;
    }

    public int getNominate() {
        return nominate;
    }
}
