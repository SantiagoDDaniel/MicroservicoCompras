package com.wine.service.impl;

import com.wine.dto.ProdutoDto;
import com.wine.dto.ProdutoResponseDto;
import com.wine.model.ClienteEntity;
import com.wine.model.ProdutoEntity;
import com.wine.repository.ClienteRepository;
import com.wine.repository.ProdutoRepository;
import com.wine.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ProdutoDto buscarPorCodigo(Long codigo) {
        return produtoRepository.findById(codigo)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o código: " + codigo));
    }

    @Override
    public ProdutoResponseDto buscarRecomendacao(String cpf) {
        // Buscar cliente pelo CPF
        ClienteEntity cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o CPF: " + cpf));

        // Agrupar tipos de vinho mais comprados e encontrar o mais frequente
        Long tipoVinhoMaisFrequente = cliente.getCompras().stream()
                .collect(Collectors.groupingBy(compra -> compra.getProduto().getCodigo(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("Nenhuma recomendação disponível para este cliente."));

        return produtoRepository.findById(tipoVinhoMaisFrequente)
                .map(p -> new ProdutoResponseDto(p.getCodigo(), p.getTipoVinho(), p.getPreco(), p.getSafra()))
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para o código: " + tipoVinhoMaisFrequente));
    }

    private ProdutoDto convertEntityToDto(ProdutoEntity entity) {
        ProdutoDto dto = new ProdutoDto();
        dto.setCodigo(entity.getCodigo());
        dto.setTipoVinho(entity.getTipoVinho());
        dto.setPreco(entity.getPreco());
        dto.setSafra(entity.getSafra());
        dto.setAnoCompra(entity.getAnoCompra());
        return dto;
    }
}