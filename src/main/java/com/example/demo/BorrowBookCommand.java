package com.example.demo;

import lombok.Value;

@Value
public class BorrowBookCommand {
    private String bookId;
    private String name;
}
