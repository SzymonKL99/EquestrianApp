package com.crud.equestrianapp.testMapper;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.service.dto.ClientDto;
import com.crud.equestrianapp.service.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class ClientMapperTest {

    @Autowired
    ClientMapper clientMapper;

    @Test
    void testMapToClient() {
        //Given
        ClientDto clientDto = new ClientDto(1L, "Adam", "Kowalski", "123456789", "kowalski@example.com");

        //When
        Client client = clientMapper.mapToClient(clientDto);

        //Then
        assertEquals(clientDto.getId(), client.getId());
        assertEquals(clientDto.getFirstName(), client.getFirstName());
        assertEquals(clientDto.getLastName(), client.getLastName());
        assertEquals(clientDto.getPhone(), client.getPhone());
        assertEquals(clientDto.getEmail(), client.getEmail());
    }

    @Test
    void testMapToClientDto() {
        //Given
        Client client = new Client(1L, "Adam", "Kowalski", "123456789", "kowalski@example.com");

        //When
        ClientDto clientDto = clientMapper.mapToClientDto(client);

        //Then
        assertEquals(client.getId(), clientDto.getId());
        assertEquals(client.getFirstName(), clientDto.getFirstName());
        assertEquals(client.getLastName(), clientDto.getLastName());
        assertEquals(client.getPhone(), clientDto.getPhone());
        assertEquals(client.getEmail(), clientDto.getEmail());
    }

    @Test
    void testMapToClientDtoList() {
        //Given
        Client client1 = new Client(1L, "Adam", "Kowalski", "123456789", "kowalski@example.com");
        Client client2 = new Client(2L, "Jan", "Klon", "987654321", "klon@example.com");
        List<Client> clientList = Arrays.asList(client1, client2);

        // When
        List<ClientDto> clientDtoList = clientMapper.mapToClientDtoList(clientList);

        // Then
        assertEquals(clientList.size(), clientDtoList.size());
        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            ClientDto clientDto = clientDtoList.get(i);
            assertEquals(client.getId(), clientDto.getId());
            assertEquals(client.getFirstName(), clientDto.getFirstName());
            assertEquals(client.getLastName(), clientDto.getLastName());
            assertEquals(client.getPhone(), clientDto.getPhone());
            assertEquals(client.getEmail(), clientDto.getEmail());
        }
    }

}
