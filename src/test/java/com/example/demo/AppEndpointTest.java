package com.example.demo;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

// Integration test - the whole application is started at "DEFINED_PORT
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // Functionality
    @Test
    public void shouldBorrowBook() {
        restTemplate.postForLocation("/api/borrow", new BorrowBookDtoModel("book name", "user name"));

        val response = restTemplate.getForEntity("/api/borrowed", BorrowedBooksDtoModel.class)
                .getBody();

        Assertions.assertThat(response).isEqualTo(BorrowedBooksDtoModel.builder().booksName("book name").build());
    }
}