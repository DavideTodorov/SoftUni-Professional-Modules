package com.example.bookshopsystem;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.repositories.CategoryRepository;
import com.example.bookshopsystem.services.AuthorService;
import com.example.bookshopsystem.services.BookService;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public ConsoleRunner(AuthorRepository authorRepository, CategoryRepository categoryRepository,
                         BookRepository bookRepository, AuthorService authorService,
                         CategoryService categoryService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

         seedDatabase();

        System.out.println("Please, enter the number of query to be executed: ");
        int numberOfQuery = Integer.parseInt(scanner.nextLine());
        switch (numberOfQuery) {
            case 1:
                queryOne();
                break;
            case 2:
                queryTwo();
                break;
            case 3:
                queryThree();
                break;
            case 4:
                queryFour();
                break;
        }
    }

    private void queryFour() {
        List<Book> booksByAuthor = authorRepository.findByAuthorOrderByReleaseDateDescThenByTitle();

        booksByAuthor.forEach(b -> {
            System.out.printf("%s, %s, %d%n",
                    b.getTitle(), b.getReleaseDate().toString(), b.getCopies());
        });
    }

    private void queryThree() {
        List<String> byCountOfBooks = authorRepository.findAndOrderByCountOfBooks();

        for (String inf : byCountOfBooks) {
            String[] tokens = inf.split(",");
            System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2]);
        }
    }

    private void queryTwo() {
        LocalDate dateBefore = LocalDate.of(1990, 1, 1);
        List<Book> books = bookRepository.findAllByReleaseDateBefore(dateBefore);

        Set<Author> authors = new HashSet<>();

        for (Book book : books) {
            Author author = book.getAuthor();
            authors.add(author);
        }

        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
    }

    private void queryOne() {
        LocalDate after = LocalDate.of(2000, 1, 1);
        List<Book> books = bookRepository.findAllByReleaseDateAfter(after);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void seedDatabase() {
        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();
    }
}



