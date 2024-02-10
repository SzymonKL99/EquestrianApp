package com.crud.equestrianapp.service.mapper;


import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.service.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClientMapper {

    public Client mapToClient(final ClientDto clientDto) {
        return new Client(
                clientDto.getId(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getPhone(),
                clientDto.getEmail()
        );
    }
    public ClientDto mapToClientDto(final Client client) {
        return new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhone(),
                client.getEmail()
        );
    }

    public List<ClientDto> mapToClientDtoList(final List<Client> clientList) {
        return clientList.stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toList());
    }

}
