package com.wine.service.impl;

import com.wine.dto.ClienteDto;
import com.wine.dto.ClienteFielResponse;
import com.wine.model.ClienteEntity;
import com.wine.model.CompraEntity;
import com.wine.model.ProdutoEntity;
import com.wine.repository.ClienteRepository;
import com.wine.repository.CompraRepository;
import com.wine.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CompraRepository compraRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, CompraRepository compraRepository) {
        this.clienteRepository = clienteRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public ClienteDto buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).map(cliente -> {
                    ClienteDto clienteDto = new ClienteDto();
                    clienteDto.setNome(cliente.getNome());
                    clienteDto.setCpf(cliente.getCpf());
                    return clienteDto;
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o CPF: " + cpf));
    }

    @Override
    public List<ClienteFielResponse> buscarClientesFieis() {
        return compraRepository.findAll().stream()
                .collect(Collectors.groupingBy(CompraEntity::getCliente))
                .entrySet().stream()
                .map(entry -> {
                    ClienteEntity cliente = entry.getKey();
                    List<CompraEntity> compras = entry.getValue();
                    long quantidadeTotal = compras.size();
                    // Soma o valor total gasto
                    double valorTotalGasto = compras.stream()
                            .mapToDouble(compra -> {
                                ProdutoEntity produto = compra.getProduto();
                                double preco = produto.getPreco();
                                int quantidade = compra.getQuantidade();
                                return quantidade * preco;
                            })
                            .sum();

                    // Cria a resposta
                    return new ClienteFielResponse(
                            cliente.getNome(),
                            cliente.getCpf(),
                            quantidadeTotal,
                            BigDecimal.valueOf(valorTotalGasto).setScale(2, RoundingMode.HALF_UP).doubleValue()
                    );
                })
                // Ordena explicitamente
                .sorted((c1, c2) -> {
                    int compareQuantidade = Long.compare(c2.getQuantidadeDeCompras(), c1.getQuantidadeDeCompras());
                    if (compareQuantidade != 0)
                        return compareQuantidade; // Prioriza quantidade de compras
                    return Double.compare(c2.getValorTotalGasto(), c1.getValorTotalGasto()); // Em caso de empate, prioriza valor gasto
                })
                .limit(3)
                .collect(Collectors.toList());
    }
}