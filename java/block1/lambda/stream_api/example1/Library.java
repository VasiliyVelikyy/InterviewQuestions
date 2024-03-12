package block1.lambda.stream_api.example1;

import java.util.ArrayList;
import java.util.List;

class Library {

    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        init();
    }

    private void init() {
        books = new ArrayList<>();
        books.add(new Book("Oruel Jordj", "1984", 1990));
        books.add(new Book("Bruce Eccel", "Effective Java", 2019));
        books.add(new Book("Shild", "Java edition 12", 2023));
        //и так далее для других книг

        readers = new ArrayList<>();
        readers.add(new Reader("Иванов Иван Иванович", "ivanov.email@test.ru", true));
        readers.add(new Reader("Петров Петр Иванович", "petrov.email@test.ru", true));
        readers.add(new Reader("Пупкина Ольга Николаевна", "pup.email@test.ru", false));
        //и так далее для других читателей

        readers.get(0).getBooks().add(books.get(1));
        readers.get(1).getBooks().add(books.get(2));
        readers.get(1).getBooks().add(books.get(0));
        readers.get(1).getBooks().add(books.get(1));

        readers.get(2).getBooks().add(books.get(2));
        readers.get(2).getBooks().add(books.get(0));


        //и так далее для других читателей и взятых книг
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }
}
