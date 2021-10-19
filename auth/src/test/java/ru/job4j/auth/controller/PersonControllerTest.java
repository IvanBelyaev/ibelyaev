package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenGetPersonsThenReturnsAllPersons() throws Exception {
        Person ivan = Person.of("ivan", "123");
        ivan.setId(1);
        Person irina = Person.of("irina", "qwe");
        irina.setId(2);
        when(personRepository.findAll()).thenReturn(List.of(irina, ivan));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("irina")))
                .andExpect(jsonPath("$[0].id", is(2)));
    }

    @Test
    public void whenGetPersonWithIdThenReturnsPersonWithTheSameId() throws Exception {
        Person person = Person.of("ivan", "123");
        person.setId(1);

        when(personRepository.findById(1)).thenReturn(java.util.Optional.of(person));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.login", is("ivan")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void whenGetPersonWithNotExistingIdThenReturnsEmptyPerson() throws Exception {
        when(personRepository.findById(1)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.login", nullValue()))
                .andExpect(jsonPath("$.id", is(0)));
    }

    @Test
    public void whenPostPersonThenCreatesNewPerson() throws Exception {
        Person person = Person.of("ivan", "123");
        person.setId(1);

        when(personRepository.save(person)).thenReturn(person);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.login", is("ivan")));
    }

    @Test
    public void whenPutPersonThenPersonIsUpdated() throws Exception {
        Person person = Person.of("ivan", "123");
        person.setId(1);

        when(personRepository.save(person)).thenReturn(person);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeletePersonThenPersonIsDeleted() throws Exception {
        Person person = Person.of("", "");

        doNothing().when(personRepository).delete(person);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}
