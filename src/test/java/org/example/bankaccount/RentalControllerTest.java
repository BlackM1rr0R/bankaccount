package org.example.bankaccount;

import org.example.bankaccount.controller.RentalController;
import org.example.bankaccount.module.Rental;
import org.example.bankaccount.service.RentalService;
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

@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @Test
    void testRentBook() throws Exception {
        Rental rental = new Rental();
        rental.setId(1L);

        Mockito.when(rentalService.rentBook(1L, 2L)).thenReturn(rental);

        mockMvc.perform(post("/rental")
                        .param("userId", "1")
                        .param("bookId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testGetUserRentals() throws Exception {
        Rental rental = new Rental();
        rental.setId(1L);

        Mockito.when(rentalService.getUserRentals(1L)).thenReturn(List.of(rental));

        mockMvc.perform(get("/rental/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}

