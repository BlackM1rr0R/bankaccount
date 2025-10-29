package org.example.bankaccount.service;

import org.example.bankaccount.module.Account;
import org.example.bankaccount.module.Book;
import org.example.bankaccount.module.Rental;
import org.example.bankaccount.repository.AccountRepository;
import org.example.bankaccount.repository.BookRepository;
import org.example.bankaccount.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    public RentalService(RentalRepository rentalRepository, AccountRepository accountRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
    }
    public Rental rentBook(Long userId, Long bookId){
        Book book=bookRepository.findById(bookId).get();
        if(!book.isAvailable()){
            throw new RuntimeException("Book is not available");
        }
        Account user=accountRepository.findById(userId).get();
        Rental rental=Rental.builder()
                .book(book)
                .user(user)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .returned(false)
                .build();
        book.setAvailable(false);
       return rentalRepository.save(rental);
    }
    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow();
        rental.setReturned(true);
        rental.setEndDate(LocalDate.now());


        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);


        return rentalRepository.save(rental);
    }


    public List<Rental> getUserRentals(Long userId) {
        return rentalRepository.findByUserId(userId);
    }
}
