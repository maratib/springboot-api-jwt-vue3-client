package com.jp.api.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.jp.api.Welcome;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Welcome.class)
public class WelcomeTest {

    @Autowired
    MockMvc mvc;

    // @Test
    // void WelcomeTest() throws Exception {
    // mvc.perform(get("/")).andExpect(status().isOk());
    // }

}
