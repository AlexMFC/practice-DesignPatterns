package com.alexs.design_patterns.service;

import com.alexs.design_patterns.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Address verifyCep(@PathVariable("cep") String cep);
}
