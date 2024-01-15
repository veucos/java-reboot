package ru.sberbank.edu.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sberbank.edu.config.WebApplicationConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebApplicationConfig.class})
class AppControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/")
                        .contentType("text/html"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print()); // для дебага
    }

    @Test
    void finance() throws Exception {
        mockMvc.perform(get("/finance")
                        .contentType("text/html"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc"))
                .andExpect(model().attributeExists("deposit"))
                .andDo(print()); // для дебага
    }

    @Test
    void calc() throws Exception {
        mockMvc.perform(post("/finance")
                        .contentType("text/html")
                        .param("sum", "100000")
                        .param("percent", "10")
                        .param("years", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/result"))
                .andDo(print()); // для дебага
    }

    @Test
    void result() throws Exception {
        mockMvc.perform(get("/result")
                        .contentType("text/html"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andDo(print()); // для дебага
    }
}