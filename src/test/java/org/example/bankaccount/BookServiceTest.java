package org.example.bankaccount;


import org.example.bankaccount.module.Book;
import org.example.bankaccount.repository.BookRepository;
import org.example.bankaccount.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAvailableBooks() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");
        book.setAvailable(true);

        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> result = bookService.getAvailableBooks();

        assertEquals(1, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
    }



    @Test
    void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(book);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testUpdateBook() {
        Book existing = new Book();
        existing.setId(1L);
        existing.setTitle("Old Title");

        Book updated = new Book();
        updated.setTitle("New Title");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenReturn(existing);

        Book result = bookService.updateBook(1L, updated);

        assertEquals("New Title", result.getTitle());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
