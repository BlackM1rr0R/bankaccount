package org.example.bankaccount.service;

import org.example.bankaccount.module.Book;
import org.example.bankaccount.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public Book addBook(Book book) {
        book.setAvailable(true);
        book.setCreatedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book oldBook = bookRepository.findById(id).get();
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        return bookRepository.save(oldBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
