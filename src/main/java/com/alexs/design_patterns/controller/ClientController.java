package com.alexs.design_patterns.controller;

import com.alexs.design_patterns.model.Client;
import com.alexs.design_patterns.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Client>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Client> insert(@RequestBody Client client){
        service.insert(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping
    public ResponseEntity<Client> update(Long id, @RequestBody Client client){
        service.update(id, client);
        return ResponseEntity.ok(client);
    }


    @DeleteMapping
    public ResponseEntity delete(Long id){
        service.delete(id);
        return ResponseEntity.ok("Deletado");
    }
}
