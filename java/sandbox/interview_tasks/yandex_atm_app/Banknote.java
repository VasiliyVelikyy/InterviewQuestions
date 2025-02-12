package sandbox.interview_tasks.yandex_atm_app;

public class Banknote {
    private final int nominate;
    private final int count;

    public Banknote(int nominate, int count) {
        this.nominate = nominate;
        this.count = count;
    }

    public int getNominate() {
        return nominate;
    }

    public int getCount() {
        return count;
    }
}
