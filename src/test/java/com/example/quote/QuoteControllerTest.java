package com.example.quote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void healthReturnsOk() throws Exception {
        mvc.perform(get("/health"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void randomQuoteReturnsQuote() throws Exception {
        mvc.perform(get("/quote"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.quote").exists());
    }

    @Test
    void addQuoteWorks() throws Exception {
        mvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON)
                .content("{\"quote\":\"Test quote\"}"))
           .andExpect(status().isCreated());
    }

    @Test
    void addQuoteWithoutFieldFails() throws Exception {
        mvc.perform(post("/quotes").contentType(MediaType.APPLICATION_JSON).content("{}"))
           .andExpect(status().isBadRequest());
    }
}
