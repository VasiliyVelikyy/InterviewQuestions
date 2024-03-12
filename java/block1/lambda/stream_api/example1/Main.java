package block1.lambda.stream_api.example1;

import block1.lambda.stream_api.utils.PrintUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class Main {
    private static List<Book> books;
    private static List<Reader> readers;

    public static void main(String[] args) {
        Library library = new Library();

        //Для сортировки — sorted()
        List<Book> sortedBookByIssueYear = library.getBooks()
                .stream()
                .sorted((o1, o2) -> { //отсортировать по году выпуска
                    //при сортировке в натуральном порядке return o1.getIssueYear().compareTo(o2.getIssueYear());
                    //идея может заменить запись как Comparator.comparing(Book::getIssueYear)
                    return o2.getIssueYear().compareTo(o1.getIssueYear());
                })
                .collect(Collectors.toList());
        System.out.println("sortedBookByIssueYear=" + sortedBookByIssueYear);

        //Для преобразования  в другой объект стрима — map()
        List<String> changedEmailStream = library.getReaders()
                .stream()
                .map(elem -> "Hah" + elem.getEmail())
                .collect(Collectors.toList());
        System.out.println("changedEmailStream=" + changedEmailStream);

        //Для фильтрации — filter()
        List<Reader> readersHaveSubscribe = library.getReaders()
                .stream()
                .filter(Reader::isSubscriber)
                .collect(Collectors.toList());
        System.out.println("readersHaveSubscribe=" + readersHaveSubscribe);

        //Для преобразования и создания линейного списка — flatMap()
        List<Book> booksFromReaders = library.getReaders()
                .stream()
                .flatMap(reader -> reader.getBooks().stream())
                .distinct()  //чтобы книги не повторялись так как 1 и 2 читател взяли одинаковые книги
                .collect(Collectors.toList());
        System.out.println("all books from all readers=" + booksFromReaders);

        //Для проверки, есть ли хоть что-то подходящее, — anyMatch()
        boolean isExistAnyBookNewest1991 = library.getBooks().stream().anyMatch(book -> book.getIssueYear() > 1991);
        System.out.println("isExistAnyBookNewest1991= " + isExistAnyBookNewest1991);

        //Чтобы остался только один — reduce()
        int sumOfAllYears = library.getBooks()
                .stream()
                .map(Book::getIssueYear)
                .reduce(0, Integer::sum);
        System.out.println("sumOfAllYears= " + sumOfAllYears);


        //ГРУППИРОВКА
        //Для группировки — collect() + Collectors.groupingBy() и Collectors.mapping()
        /*
        тем, у кого взято меньше двух книг, просто расскажем о новинках библиотеки;
            тем, у кого две книги и больше, напомним о том, что их нужно вернуть в срок.
            То есть надо написать метод, который вернёт два списка адресов (типа EmailAddress):
            с пометкой OK — если книг не больше двух, или TOO_MUCH — если их две и больше.
             Порядок групп не важен.
             */
        Map<String, List<EmailAddress>> addressesMap = library.getReaders()
                .stream()
                //groupingBy на выходе уже формирут map, он принимает параметр по которому будет формироваться ключу, и значение
                .collect(groupingBy(reader -> reader.getBooks().size() >= 2 ? "TOO_MUTCH" : "OK",
                        mapping(reader -> new EmailAddress(reader.getEmail()), Collectors.toList())));
        PrintUtil.addressesMap(addressesMap);

        //более короткая запись если используем текущий объект Reader для группировки
        Map<String, List<Reader>> readersMap = library.getReaders()
                .stream()
                .collect(groupingBy(reader -> reader.getBooks().size() > 2 ? "TOO_MUTCH" : "OK"));
        PrintUtil.readersMap(readersMap);

    }
}
