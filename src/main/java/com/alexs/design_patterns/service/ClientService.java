package com.alexs.design_patterns.service;

import com.alexs.design_patterns.model.Client;


public interface ClientService {
    Iterable<Client> findAll();
    Client findById(Long id);
    void insert(Client client);
    void update(Long id, Client client);
    void delete(Long id);
}
