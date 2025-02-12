package sandbox.interview_tasks.atm_app;

public enum Banknote {
    TEST(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final int nominate;

    Banknote(int nominate) {
        this.nominate = nominate;
    }

    public int getNominate() {
        return nominate;
    }
}
