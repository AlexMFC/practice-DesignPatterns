package com.alexs.design_patterns.repository;

import com.alexs.design_patterns.model.Address;
import jakarta.persistence.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}
