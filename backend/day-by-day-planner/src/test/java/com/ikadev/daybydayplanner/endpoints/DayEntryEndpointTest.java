package com.ikadev.daybydayplanner.endpoints;

import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.model.Mood;
import com.ikadev.daybydayplanner.service.EntryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import security.SpringSecurityWebAuxTestConfig;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringSecurityWebAuxTestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DayEntryEndpointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EntryService entryService;

    String uri = "/api/entries";

    @Test
    void getAllEntriesNotLoggedIn() throws Exception {
        mockMvc.perform(get(uri))
                .andExpect(status().is4xxClientError());
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
    @WithUserDetails("test")
    void getEntryForInvalidDate() throws Exception {
        String uriWithDate = uri + "/2020-30-30";
        mockMvc
                .perform(get(uriWithDate))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getEntryForDateNotLoggedIn() throws Exception {
        String uriWithDate = uri + "/2020-02-02";
        mockMvc
                .perform(get(uriWithDate))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("test")
    void saveEntry() throws Exception {
        DayEntry dayEntry = DayEntry.builder()
                .mood(Mood.OK)
                .date(LocalDate.of(2020, 2, 2))
                .build();
        String username = "test";
        String json = """
                {"mood": "OK",
                "date": "2020-02-02"
                }
                """;
        when(entryService.saveEntry(dayEntry, username)).thenReturn(dayEntry);

        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void saveEntryNotLoggedIn() throws Exception {
        String json = """
                {"mood": "OK",
                "date": "2020-02-02"
                }
                """;
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getMoodsForYearNotLoggedIn() throws Exception {
        String urlMoods = uri + "/date-moods/2020";
        mockMvc.perform(get(urlMoods))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("test")
    void getMoodsForYearLoggedIn() throws Exception {
        String urlMoods = uri + "/date-moods/2020";
        mockMvc.perform(get(urlMoods))
                .andExpect(status().is2xxSuccessful());
    }

}