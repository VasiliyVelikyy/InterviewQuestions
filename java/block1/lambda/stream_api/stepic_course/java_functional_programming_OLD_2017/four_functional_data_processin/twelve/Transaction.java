package block1.lambda.stream_api.stepic_course.java_functional_programming_OLD_2017.four_functional_data_processin.twelve;

public class Transaction {
    private String uuid;
    private String state;
    private int sum;
    private String created;

    public Transaction(String uuid, String state, int sum, String created) {
        this.uuid = uuid;
        this.state = state;
        this.sum = sum;
        this.created = created;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
