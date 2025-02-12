package ru.moskalev.sandbox.interview_tasks.stream.book_aggregation;


// В коде роль МС выполняют методы заглушки
class BookAggregation {
    private String pubHouseName;
    private String author;
    private long bookCount;

    public BookAggregation(String author, String pubHouseName, long bookCount) {
        this.author = author;
        this.pubHouseName = pubHouseName;
        this.bookCount = bookCount;
    }
}
