package com.wine.service.impl;

import com.wine.dto.*;
import com.wine.model.ClienteEntity;
import com.wine.model.CompraEntity;
import com.wine.repository.ClienteRepository;
import com.wine.repository.CompraRepository;
import com.wine.service.ClienteService;
import com.wine.service.CompraService;
import com.wine.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository repository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository repository, ProdutoService produtoService, ClienteService clienteService, CompraRepository compraRepository) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.compraRepository = compraRepository;
    }
    @Override
    public List<ComprasResponse> listarCompras() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(CompraEntity::getValorTotal)) // Ordena por valor total crescente
                .map(compra -> {
                    // Cria a resposta principal
                    ComprasResponse compraDto = new ComprasResponse();
                    compraDto.setId(compra.getId());
                    compraDto.setQuantidade(compra.getQuantidade());
                    compraDto.setValorTotal(compra.getValorTotal());

                    // Busca e configura os dados do cliente
                    ClienteDto cliente = clienteService.buscarPorCpf(compra.getCliente().getCpf());
                    List<CompraEntity> comprasDoCliente = repository.findByCliente_Cpf(cliente.getCpf());

                    // Preenche as compras do cliente com os dados completos
                    List<CompraDto> comprasDto = comprasDoCliente.stream()
                            .map(compraEntity -> {
                                CompraDto compraDetalhada = new CompraDto();
                                ProdutoDto produtoDetalhado = produtoService.buscarPorCodigo(compraEntity.getProduto().getCodigo());

                                // Configura os detalhes do produto na compra
                                compraDetalhada.setCodigo(String.valueOf(compraEntity.getProduto().getCodigo()));
                                compraDetalhada.setQuantidade(compraEntity.getQuantidade());
                                compraDetalhada.setTipoVinho(produtoDetalhado.getTipoVinho());
                                compraDetalhada.setPreco(produtoDetalhado.getPreco());
                                compraDetalhada.setSafra(produtoDetalhado.getSafra());
                                compraDetalhada.setAnoCompra(produtoDetalhado.getAnoCompra());
                                return compraDetalhada;
                            })
                            .collect(Collectors.toList());

                    // Configura as compras no cliente
                    cliente.setCompras(comprasDto);
                    compraDto.setCliente(cliente);

                    return compraDto;
                })
                .collect(Collectors.toList());
    }

    public MaiorCompraResponse buscarMaiorCompraPorAno(int ano) {
        return compraRepository.findAll()
                .stream()
                .filter(compra -> compra.getProduto().getAnoCompra() == ano)
                .max(Comparator.comparing(CompraEntity::getValorTotal))
                .map(maiorCompra -> new MaiorCompraResponse(
                        maiorCompra.getCliente().getNome(),
                        maiorCompra.getCliente().getCpf(),
                        maiorCompra.getProduto().getTipoVinho(),
                        maiorCompra.getProduto().getSafra(),
                        maiorCompra.getQuantidade(),
                        maiorCompra.getValorTotal()
                ))
                .orElseThrow(() -> new RuntimeException("Nenhuma compra encontrada para o ano " + ano));
    }
}