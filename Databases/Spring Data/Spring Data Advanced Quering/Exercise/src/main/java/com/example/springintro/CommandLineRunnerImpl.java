package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Seed database
        seedData();

        System.out.println("Please enter exercise number from 1 to 13: ");
        int exerciseNumber = Integer.parseInt(scanner.nextLine());

        switch (exerciseNumber) {
            case 1:
                printAllByAgeRestriction();
                break;
            case 2:
                findAllByEditionTypeAndCopiesLessThan();
                break;
            case 3:
                findAllByPriceLessThanOrPriceGreaterThan();
                break;
            case 4:
                findAllByReleaseYearNotIn();
                break;
            case 5:
                findAllByReleaseDateBefore();
                break;
            case 6:
                findAllByFirstNameEndingWith();
                break;
            case 7:
                findAllByTitleContainingAWord();
                break;
            case 8:
                findAllBYAuthorsLastNameStartsWith();
                break;
            case 9:
                countOfBooksWithTitlesLongerThan();
                break;
            case 10:
                countOfCopiesByAuthor();
                break;
            case 11:
                findBookInfoByTitle();
                break;
            case 12:
                increaseCopies();
                break;
            case 13:
                deleteBooksWithCopiesLessThan();
                break;
        }


    }

    private void deleteBooksWithCopiesLessThan() {
        System.out.println("Enter count of copies: ");
        int copies = Integer.parseInt(scanner.nextLine());

        System.out.println(bookService.deleteByCopiesLessThan(copies));
    }

    private void increaseCopies() {
        System.out.println("Enter date: ");
        LocalDate localDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter copies: ");
        int copies = Integer.parseInt(scanner.nextLine());

        List<Book> books = bookService.findAllByReleaseDateAfter(localDate);

        books.forEach(b -> b.setCopies(b.getCopies() + copies));

        bookService.saveAll(books);

        System.out.println(books.size() * copies);
    }

    private void findBookInfoByTitle() {
        System.out.println("Enter a title:");
        String title = scanner.nextLine();

        System.out.println(bookService.findBookInfoByTitle(title)
                .replace(",", " "));
    }

    private void countOfCopiesByAuthor() {
        System.out.println("Please enter an author");
        String author = scanner.nextLine();

        System.out.println(bookService.countOfCopiesByAuthor(author));
    }

    private void countOfBooksWithTitlesLongerThan() {
        System.out.println("Enter a length:");
        int length = Integer.parseInt(scanner.nextLine());

        System.out.println(bookService.countOfBooksWithTitlesLongerThan(length));
    }

    private void findAllBYAuthorsLastNameStartsWith() {
        System.out.println("Please enter a word: ");
        String word = scanner.nextLine().toLowerCase();

        bookService.findAllBYAuthorsLastNameStartsWith(word)
                .forEach(System.out::println);
    }

    private void findAllByTitleContainingAWord() {
        System.out.println("Please enter a word: ");
        String word = scanner.nextLine().toLowerCase();

        bookService.findAllByTitleContainingAWord(word)
                .forEach(System.out::println);
    }

    private void findAllByFirstNameEndingWith() {
        System.out.println("Please enter a suffix:");
        String suffix = scanner.nextLine();

        authorService.findAllByFirstNameEndingWith(suffix)
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    private void findAllByReleaseDateBefore() {
        System.out.println("Please enter a date: ");
        String[] dateInput = scanner.nextLine().split("-");

        LocalDate date = LocalDate.of(Integer.parseInt(dateInput[2]),
                Integer.parseInt(dateInput[1]),
                Integer.parseInt(dateInput[0]));

        bookService.findAllByReleaseDateBefore(date)
                .forEach(b -> System.out.printf("%s %s %.2f%n",
                        b.getTitle(), b.getEditionType().name(), b.getPrice()));
    }

    private void findAllByReleaseYearNotIn() {
        System.out.println("Please enter an year: ");
        String year = scanner.nextLine();

        bookService.findAllByReleaseYearNotIn(year)
                .forEach(System.out::println);
    }

    private void findAllByPriceLessThanOrPriceGreaterThan() {
        bookService.findAllByPriceLessThanOrPriceGreaterThan(new BigDecimal(5), new BigDecimal(40))
                .forEach(b -> System.out.printf("%s - $%.2f%n",
                        b.getTitle(), b.getPrice()));
    }

    private void findAllByEditionTypeAndCopiesLessThan() {
        bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void printAllByAgeRestriction() {
        System.out.println("Please enter age restriction: ");
        String ageRestrictionInp = scanner.nextLine();

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInp.toUpperCase());
        bookService.findAllByAgeRestriction(ageRestriction)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
