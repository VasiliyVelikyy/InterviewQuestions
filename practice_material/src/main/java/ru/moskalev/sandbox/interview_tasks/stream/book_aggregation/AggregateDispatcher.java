package ru.moskalev.sandbox.interview_tasks.stream.book_aggregation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AggregateDispatcher {
    public static void main(String[] args) {
        List<BookAggregation> result = aggregate(
                getPublishingHouse(),
                getBook(),
                getAuthor());

    }

    private static List<BookAggregation> aggregate(List<PublishingHouse> publishingHouse,
                                                   List<Book> book,
                                                   List<Author> author) {

        Map<Integer, Author> authorMap = author.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        Map<Integer, PublishingHouse> publishMap = publishingHouse.stream().collect(Collectors.toMap(PublishingHouse::getId, Function.identity()));

        Map<String, Long> map = book.stream().map(elem -> elem.getAuthorId() + " : " + elem.getPubHouseId())
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return map.entrySet().stream().map(entry -> {
            var elems = entry.getKey().split(":");
            var authorId = Integer.parseInt(elems[0].trim());
            var pubId = Integer.parseInt(elems[1].trim());
            return new BookAggregation(authorMap.get(authorId).getName(),
                    publishMap.get(pubId).getName(),
                    entry.getValue());
        }).collect(Collectors.toList());

    }

    private static List<PublishingHouse> getPublishingHouse() {
        return Arrays.asList(new PublishingHouse(1, "Питер"),
                new PublishingHouse(2, "Эксмо"));
    }

    private static List<Book> getBook() {
        return Arrays.asList(new Book(1, "Мастер и Маргарита", 1, 1),
                new Book(2, "Горе от ума", 1, 2),
                new Book(2, "Преступление и наказание", 1, 1),
                new Book(3, "Капитанская дочка", 2, 2));
    }

    private static List<Author> getAuthor() {
        return Arrays.asList(new Author(1, "Иван Иванов"),
                new Author(2, "Пётр Петров"));
    }
}
