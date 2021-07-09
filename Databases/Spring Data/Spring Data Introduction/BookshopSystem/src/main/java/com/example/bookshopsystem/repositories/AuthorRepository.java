package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "select a.firstName, a.lastName, count(b.id) as count_books  from Author a\n" +
            "join Book b on a.id = b.author.id\n" +
            "group by a.id\n" +
            "order by count_books DESC")
    List<String> findAndOrderByCountOfBooks();

    @Query(value = "SELECT b FROM Book b\n" +
            "JOIN Author a on a.id = b.author.id\n" +
            "WHERE concat(a.firstName, ' ', a.lastName) = 'George Powell'\n" +
            "ORDER BY b.releaseDate DESC , b.title")
    List<Book> findByAuthorOrderByReleaseDateDescThenByTitle();
}
