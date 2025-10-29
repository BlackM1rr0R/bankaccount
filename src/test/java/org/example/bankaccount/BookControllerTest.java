package org.example.bankaccount;



import org.example.bankaccount.controller.BookController;
import org.example.bankaccount.module.Book;
import org.example.bankaccount.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAvailableBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");

        Mockito.when(bookService.getAvailableBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Harry Potter")));
    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");

        Mockito.when(bookService.addBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Harry Potter\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Harry Potter")));
    }
}
