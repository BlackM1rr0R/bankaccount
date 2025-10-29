package org.example.bankaccount;



import org.example.bankaccount.module.Account;
import org.example.bankaccount.module.Book;
import org.example.bankaccount.module.Rental;

import org.example.bankaccount.repository.AccountRepository;
import org.example.bankaccount.repository.BookRepository;
import org.example.bankaccount.repository.RentalRepository;

import org.example.bankaccount.service.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void testRentBook() {
        // 1️⃣ Test üçün user və book yaradırıq
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        Book book = new Book();
        book.setId(2L);
        book.setTitle("Harry Potter");
        book.setAvailable(true);

        Rental rental = new Rental();
        rental.setId(1L);
        rental.setBook(book);
        rental.setUser(account);

        // 2️⃣ Repository davranışlarını mock edirik
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        // 3️⃣ Metodu çağırırıq
        Rental result = rentalService.rentBook(1L, 2L);

        // 4️⃣ Nəticəni yoxlayırıq
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Harry Potter", result.getBook().getTitle());
        assertEquals("Elcin", result.getUser().getFirstName());
    }

    @Test
    void testReturnBook() {
        // 1️⃣ Mövcud kirayəni yaradırıq
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Harry Potter");
        book.setAvailable(false);

        Rental rental = new Rental();
        rental.setId(1L);
        rental.setBook(book);
        rental.setReturned(false);

        // 2️⃣ Mock davranışlar
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        // 3️⃣ Metodu çağırırıq
        Rental result = rentalService.returnBook(1L);

        // 4️⃣ Nəticəni yoxlayırıq
        assertTrue(result.isReturned());
        assertTrue(result.getBook().isAvailable()); // opsional yoxlama
        verify(rentalRepository, times(1)).save(rental);
    }


    @Test
    void testGetUserRentals() {
        // 1️⃣ Test dataları
        Account account = new Account();
        account.setId(1L);

        Rental rental = new Rental();
        rental.setId(10L);
        rental.setUser(account);

        // 2️⃣ Mock davranış
        when(rentalRepository.findByUserId(1L)).thenReturn(List.of(rental));

        // 3️⃣ Metodu çağırırıq
        List<Rental> result = rentalService.getUserRentals(1L);

        // 4️⃣ Yoxlayırıq
        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getId());
        assertEquals(1L, result.get(0).getUser().getId());
    }
}

