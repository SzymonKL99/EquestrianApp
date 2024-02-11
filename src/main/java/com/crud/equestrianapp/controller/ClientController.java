package com.crud.equestrianapp.controller;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.domain.Payment;
import com.crud.equestrianapp.exceptions.ClientNotFoundException;
import com.crud.equestrianapp.service.ClientService;
import com.crud.equestrianapp.service.dto.ClientDto;
import com.crud.equestrianapp.service.dto.LessonDto;
import com.crud.equestrianapp.service.dto.PaymentDto;
import com.crud.equestrianapp.service.mapper.ClientMapper;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import com.crud.equestrianapp.service.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final PaymentMapper paymentMapper;
    private final LessonMapper lessonMapper;


    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() throws ClientNotFoundException {
        List<Client> clientList = clientService.getAllClients();
        return ResponseEntity.ok(clientMapper.mapToClientDtoList(clientList));
    }

    @GetMapping(value = "{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long clientId) throws ClientNotFoundException {
        return ResponseEntity.ok(clientMapper.mapToClientDto(clientService.getClient(clientId)));
    }

    @GetMapping(value = {"{clientId}/lessons"})
    public ResponseEntity<List<LessonDto>> getLessonsByClientId(@PathVariable Long clientId) throws ClientNotFoundException {
        List<Lesson> lessonsByClientId = clientService.getLessonByClientId(clientId);
        return ResponseEntity.ok(lessonMapper.mapToLessonDtoList(lessonsByClientId));
    }

    @GetMapping(value = {"{clientId}/payments"})
    public ResponseEntity<List<PaymentDto>> getPaymentsByClientId(@PathVariable Long clientId) throws ClientNotFoundException {
        List<Payment> paymentsByClientId = clientService.getPaymentByClientId(clientId);
        return ResponseEntity.ok(paymentMapper.mapToPaymentDtoList(paymentsByClientId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.mapToClient(clientDto);
        clientService.saveClient(client);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{clientId}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto) throws ClientNotFoundException {
        Client client = clientMapper.mapToClient(clientDto);
        Client savedClient = clientService.updateClient(client);
        return ResponseEntity.ok(clientMapper.mapToClientDto(savedClient));
    }

    @DeleteMapping(value = "{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) throws ClientNotFoundException {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

}
