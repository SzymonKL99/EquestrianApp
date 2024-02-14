package com.crud.equestrianapp.service;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.domain.Payment;
import com.crud.equestrianapp.exceptions.ClientNotFoundException;
import com.crud.equestrianapp.repository.ClientRepository;
import com.crud.equestrianapp.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final LessonRepository lessonRepository;

    public void saveClient(final Client client) {
        clientRepository.save(client);
    }

    public Client getClient(Long id) throws ClientNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new ClientNotFoundException("Client with id " + id + " not found");
        }

        return clientOptional.get();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(final Client client) throws ClientNotFoundException {
        Optional<Client> optionalUpdatedClient = clientRepository.findById(client.getId());

        if (optionalUpdatedClient.isEmpty()) {
            throw new ClientNotFoundException("Client with id " + client.getId() + " not found");
        }

        Client updatedClient = optionalUpdatedClient.get();
        client.setLessonsList(updatedClient.getLessonsList());
        client.setPayments(updatedClient.getPayments());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) throws ClientNotFoundException {
        Optional<Client> clientToDelete = clientRepository.findById(id);

        if (clientToDelete.isEmpty()) {
            throw new ClientNotFoundException("Client with id " + id + " not found");
        }

        Client client = clientToDelete.get();
        List<Lesson> lessonList = client.getLessonsList();
        lessonList.forEach(lesson -> { lesson.getClients().remove(client);
        lessonRepository.save(lesson);
        });
        client.setLessonsList(new ArrayList<>());
        clientRepository.save(client);
        clientRepository.deleteById(id);
    }

    public List<Lesson> getLessonByClientId(Long id) throws ClientNotFoundException {
        Client client = getClient(id);
        if (client == null) {
            throw new ClientNotFoundException("Client with" + id + "not found");
        }
        return client.getLessonsList();
    }

    public List<Payment> getPaymentByClientId(Long id) throws ClientNotFoundException {
        Client client = getClient(id);
        if (client == null) {
            throw new ClientNotFoundException("Client with" + id + "not found");
        }
        return client.getPayments();
    }
}
