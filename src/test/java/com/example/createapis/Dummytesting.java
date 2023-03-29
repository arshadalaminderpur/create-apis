package com.example.createapis;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import com.example.createapis.Entity.Book;
import com.example.createapis.controller.BookController;
import com.example.createapis.controller.TransactionController;
import com.example.createapis.service.BookService;
import com.example.createapis.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class Dummytesting {

    private MockMvc mockMvc;

    @InjectMocks
    private BookController transactionController;


    @Mock
    private BookService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void writeTest()throws Exception{


        Book response=new Book();
        response.setPrice(500);
        ResponseEntity<Book> resultEntity=ResponseEntity.status(HttpStatus.OK).body(response);
        Mockito.when(service.addBook(Mockito.any())).thenReturn(resultEntity);
        Book book=new Book();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/add-book")
                        .contentType(APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();

        Book actualResponse = new ObjectMapper().readValue(responseContent, Book.class);

        log.info(response.toString()+"response="+actualResponse);
        Assert.assertEquals(response,actualResponse);


    }



}
