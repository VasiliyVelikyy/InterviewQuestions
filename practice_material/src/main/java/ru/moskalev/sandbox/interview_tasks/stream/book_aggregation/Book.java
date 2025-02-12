package ru.moskalev.sandbox.interview_tasks.stream.book_aggregation;

class Book {
    private int id;
    private String name;
    private int authorId;
    private int pubHouseId;

    public Book(int id, String name, int authorId, int pubHouseId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.pubHouseId = pubHouseId;
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

    public int getAuthorId() {
        return authorId;
    }

    public int getPubHouseId() {
        return pubHouseId;
    }

}
