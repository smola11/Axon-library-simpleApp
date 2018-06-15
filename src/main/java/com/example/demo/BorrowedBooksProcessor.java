package com.example.demo;

import lombok.Value;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Do niego przychodza "karteczki" co zostalo wypozyczone/oddane; musi sluchac zdarzen;
@Component
public class BorrowedBooksProcessor {

    // List that is safe in terms of concurrency;
    private final List<BorrowedBookModel> borrowed = new CopyOnWriteArrayList<>();

    // Juz część "read" z CQRS
    @EventHandler
    public void on(BookBorrowedEvent event) {
        borrowed.add(new BorrowedBookModel(event.getBookName()));
    }

    @QueryHandler
    public BorrowedBooksResponse on(BorrowedBooksQuery query) {
        return new BorrowedBooksResponse(borrowed
                .stream().map(it -> it.getName())
                .toArray(String[]::new));
    }
}

@Value
class BorrowedBookModel {
    private String name;
}
