package com.example.demo;

import lombok.Value;

@Value
public class BookBorrowedEvent {
    private String bookId;
    private String bookName;
}
