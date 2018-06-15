package com.example.demo;

import lombok.val;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.GenericQueryMessage;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// Here we only translate incoming DTOs into Commands
@RestController
public class AppEndpoint {

    private final QueryBus queryBus; // dispatches queries to Query Handlers
    private final CommandGateway commandBus; // dispatches commands

    public AppEndpoint(CommandGateway commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping("api/borrow")
    public void borrow(@RequestBody BorrowBookDtoModel dto) {
        val command = new BorrowBookCommand(UUID.randomUUID().toString(), dto.getBookName());
        commandBus.send(command);
    }

    @GetMapping("api/borrowed")
    public CompletableFuture<BorrowedBooksDtoModel> borrowed() {
        // Query (contains question and answer)
        val query = new GenericQueryMessage<BorrowedBooksQuery, BorrowedBooksResponse>(new BorrowedBooksQuery(),
                ResponseTypes.instanceOf(BorrowedBooksResponse.class));

        // We are sending query
        return queryBus.query(query).thenApply(it -> { // model -> dto
            String[] result = it.getPayload().getBooksNames();
            val resultAsList = Arrays.asList(result);
            return new BorrowedBooksDtoModel(resultAsList);
        });
    }
}
