package com.crud.equestrianapp.controllerTest;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.repository.ClientRepository;
import com.crud.equestrianapp.service.dto.ClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldGetClients() throws Exception {
        //Given
        Client client1 = new Client(null, "Adam", "Kowalski", "123456789", "kowalski@example.com");
        Client client2 = new Client(null, "Jan", "Klon", "987654321", "klon@example.com");

        clientRepository.save(client1);
        clientRepository.save(client2);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/clients")
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Adam")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Kowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("Klon")));
    }

    @Test
    void shouldGetClient() throws Exception {
        //Given
        Client client = new Client(null,"Adam", "Kowalski", "123456789", "kowalski@example.com");
        clientRepository.save(client);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/clients/{clientId}", client.getId())
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(client.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Adam")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Kowalski")));
    }

    @Test
    void shouldCreateClient() throws Exception {
        //Given
        ClientDto dto = new ClientDto(null, "Adam", "Kowalski", "123456789", "kowalski@example.com");
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateClient() throws Exception {
        //Given
        Client client = new Client(null, "Adam", "Kowalski", "123456789", "kowalski@example.com");
        clientRepository.save(client);

        ClientDto dto = new ClientDto(client.getId(), "Jan", "Klon", "987654321","klon@example.com");

        String jsonContent = objectMapper.writeValueAsString(dto);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/clients/{clientId}", client.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Client updated = clientRepository.findById(client.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("Jan", updated.getFirstName());
        assertEquals("Klon", updated.getLastName());
        assertEquals("klon@example.com", updated.getEmail());
    }

    @Test
    void shouldDeleteClient() throws Exception {
        //Given
        Client client = new Client(null, "Adam", "Kowalski","123456789", "kowalski@example.com");
        clientRepository.save(client);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/clients/{clientId}", client.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        assertFalse(clientRepository.findById(client.getId()).isPresent());
    }
}
