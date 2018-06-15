package com.example.demo;

import lombok.extern.java.Log;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Log
public class BookAggregate {

    @AggregateIdentifier
    private String bookId;

    @CommandHandler
    public BookAggregate(BorrowBookCommand cmd) {
        apply(new BookBorrowedEvent(cmd.getBookId(), cmd.getName()));
    }

    @EventHandler
    public void on(BookBorrowedEvent evt) {
        bookId = evt.getBookId();
    }
}
