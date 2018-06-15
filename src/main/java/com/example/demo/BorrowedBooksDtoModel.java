package com.example.demo;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedBooksDtoModel {

    @Singular
    private List<String> booksNames;
}
