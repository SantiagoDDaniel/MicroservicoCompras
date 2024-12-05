package com.wine.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wine.dto.ClienteDto;
import com.wine.dto.CompraDto;
import com.wine.dto.ProdutoDto;
import com.wine.model.ClienteEntity;
import com.wine.model.CompraEntity;
import com.wine.model.ProdutoEntity;
import com.wine.repository.ClienteRepository;
import com.wine.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    @Value("${api.clientes.url}")
    private String apiClientesUrl;

    @Value("${api.produto.url}")
    private String apiProdutoUrl;

    public DataLoader(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<ProdutoDto> produtosDto = objectMapper.readValue(new URL(new URI(apiProdutoUrl).toString()), new TypeReference<>() {});
        List<ProdutoEntity> produtos = produtosDto.stream()
                .map(dto -> {
                    ProdutoEntity produto = new ProdutoEntity();
                    produto.setCodigo(dto.getCodigo());
                    produto.setTipoVinho(dto.getTipoVinho());
                    produto.setPreco(dto.getPreco());
                    produto.setSafra(dto.getSafra());
                    produto.setAnoCompra(dto.getAnoCompra());
                    return produto;
                })
                .collect(Collectors.toList());
        produtoRepository.saveAll(produtos);

        List<ClienteDto> clientesJson = objectMapper.readValue(new URL(new URI(apiClientesUrl).toString()), new TypeReference<>() {});
        for (ClienteDto clienteJson : clientesJson) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNome(clienteJson.getNome());
            cliente.setCpf(clienteJson.getCpf());

            List<CompraEntity> compras = new ArrayList<>();
            for (CompraDto compraJson : clienteJson.getCompras()) {
                ProdutoEntity produto = produtoRepository.findById(Long.valueOf(compraJson.getCodigo()))
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + compraJson.getCodigo()));

                CompraEntity compra = new CompraEntity();
                compra.setCliente(cliente);
                compra.setProduto(produto);
                compra.setQuantidade(compraJson.getQuantidade());
                compra.setValorTotal(produto.getPreco() * compraJson.getQuantidade());
                compras.add(compra);
            }
            cliente.setCompras(compras);
            clienteRepository.save(cliente);
        }

        System.out.println("Dados carregados com sucesso!");
    }
}