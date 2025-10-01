package com.Tracker.data.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    private String id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
    private User user;
}
