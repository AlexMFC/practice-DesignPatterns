package com.alexs.design_patterns.service.impl;

import com.alexs.design_patterns.model.Address;
import com.alexs.design_patterns.model.Client;
import com.alexs.design_patterns.repository.AddressRepository;
import com.alexs.design_patterns.repository.ClientRepository;
import com.alexs.design_patterns.service.ClientService;
import com.alexs.design_patterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService cepService;
    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> findClient = clientRepository.findById(id);
        Client client = findClient.isEmpty() ? null:findClient.get();
        return client;
    }

    @Override
    public void insert(Client client) {
        saveClientWithCep(client);
    }


    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientDb = clientRepository.findById(id);
        if(clientDb.isPresent()){
            saveClientWithCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }


    private void saveClientWithCep(Client client) {
        String cep = client.getAddress().getCep();

        //VERIFICA SE EXISTE E SE NAO EXISTIR CAI NA CALLBACK PARA CADASTRAR NOVO ENDERECO
        Address address = addressRepository.findById(cep).orElseGet(() ->{
            Address newAddress = cepService.verifyCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);

        clientRepository.save(client);
    }
}
