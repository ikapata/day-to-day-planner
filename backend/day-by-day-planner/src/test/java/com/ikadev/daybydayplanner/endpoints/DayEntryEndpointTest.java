package com.ikadev.daybydayplanner.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.model.Mood;
import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.persistence.repository.DayEntryRepository;
import com.ikadev.daybydayplanner.service.EntryService;
import com.ikadev.daybydayplanner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import security.SpringSecurityWebAuxTestConfig;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringSecurityWebAuxTestConfig.class)
@AutoConfigureMockMvc
class DayEntryEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationContext applicationContext;

    @MockBean
    EntryService entryService;
    @MockBean
    UserService userService;

    String uri = "/api/entries";
    @Autowired
    private DayEntryRepository dayEntryRepository;

    @Test
    void getAllEntriesNotLoggedIn() throws Exception {
        mockMvc.perform(get(uri)).andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("test")
    void getAllEntriesLoggedIn() throws Exception {
        mockMvc
                .perform(get(uri))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails("test")
    void getEntryForDate() throws Exception {
        String uriWithDate = uri + "/2020-02-02";
        mockMvc
                .perform(get(uriWithDate))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getEntryForDateNotLoggedIn() throws Exception {
        String uriWithDate = uri + "/2020-02-02";
        mockMvc
                .perform(get(uriWithDate))
                .andExpect(status().is4xxClientError());
    }

    // TODO -> Complete the test when user is logged in.
//    @Test
//    @WithUserDetails("test")
//    void saveEntry() throws Exception {
//    }

    @Test
    void saveEntryNotLoggedIn() throws Exception {
        DayEntry dayEntry = DayEntry.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(dayEntry);

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(status().is4xxClientError());
    }
}