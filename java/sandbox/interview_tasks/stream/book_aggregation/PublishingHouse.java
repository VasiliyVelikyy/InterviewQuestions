package sandbox.interview_tasks.stream.book_aggregation;

class PublishingHouse {
    private int id;
    private String name; //Питер, Эксмо

    public PublishingHouse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
