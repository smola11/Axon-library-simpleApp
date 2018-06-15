package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// All serialized objects need to have constructor (we cannot use @Value)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookDtoModel {

    private String bookName;
    private String userName;
}
