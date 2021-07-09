package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.*;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final String BOOK_FILE_PATH = "src/main/resources/dbData/books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedBooks() {
        if (bookRepository.count() > 0) {
            return;
        }

        try {
            Files.readAllLines(Path.of
                    (BOOK_FILE_PATH))
                    .forEach(bookEntry -> {
                        String[] tokens = bookEntry.split("\\s+");

                        EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
                        LocalDate releaseDate = LocalDate.parse(tokens[1],
                                DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int copies = Integer.parseInt(tokens[2]);
                        BigDecimal price = new BigDecimal(tokens[3]);
                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];

                        String title = Arrays.stream(tokens)
                                .skip(5)
                                .collect(Collectors.joining(" "));

                        Random rand = new Random();
                        List<Author> authorList = authorRepository.findAll();
                        Author author = authorList.get(rand.nextInt(authorList.size()));

                        List<Category> categoriesList = categoryRepository.findAll();
                        Category category = categoriesList.get(rand.nextInt(categoriesList.size()));

                        Book book = new Book(title, editionType, price, copies,
                                releaseDate, ageRestriction, author, category);

                        bookRepository.save(book);
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
