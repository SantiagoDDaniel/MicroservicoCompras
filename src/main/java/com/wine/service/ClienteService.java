package com.wine.service;

import com.wine.dto.ClienteDto;
import com.wine.dto.ClienteFielResponse;
import com.wine.model.ClienteEntity;

import java.util.List;

public interface ClienteService {
    ClienteDto buscarPorCpf(String cpf);
    List<ClienteFielResponse> buscarClientesFieis();

}