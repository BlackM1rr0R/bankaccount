package org.example.bankaccount.controller;

import org.example.bankaccount.module.Book;
import org.example.bankaccount.module.Rental;
import org.example.bankaccount.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @PostMapping
    public ResponseEntity<Rental> rentBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(rentalService.rentBook(userId, bookId));
    }


    @PutMapping("/{id}/return")
    public ResponseEntity<Rental> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.returnBook(id));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rental>> getUserRentals(@PathVariable Long userId) {
        return ResponseEntity.ok(rentalService.getUserRentals(userId));
    }
}
