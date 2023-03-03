package com.ikadev.daytodayplanner.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import security.SpringSecurityWebAuxTestConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringSecurityWebAuxTestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthEndpointTest {

    @Autowired
    MockMvc mockMvc;
    String uri = "/api/auth";



    @Test
    void registerUserUserNameNotExists() throws Exception {
        String uriRegister = uri + "/register";

        String json = """
                {"username": "test10",
                "password": "test",
                "email": "test@gmail.com"
                }
                """;
        mockMvc.perform(post(uriRegister)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void registerUserNoEmail() throws Exception {
        String uriRegister = uri + "/register";

        String json = """
                {"username": "test2",
                "password": "test",
                }
                """;
        mockMvc.perform(post(uriRegister)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void registerUserNoUsername() throws Exception {
        String uriRegister = uri + "/register";

        String json = """
                {"email": "test@test.com",
                "password": "test",
                }
                """;
        mockMvc.perform(post(uriRegister)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithUserDetails("test")
    void loginCorrectCredentials() throws Exception {
        String uriLogin = uri + "/login";

        mockMvc
                .perform(post(uriLogin))
                .andExpect(status().is2xxSuccessful());
    }

}