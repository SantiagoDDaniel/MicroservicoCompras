package com.wine.controller;

import com.wine.dto.ClienteFielResponse;
import com.wine.dto.ComprasResponse;
import com.wine.dto.MaiorCompraResponse;
import com.wine.dto.ProdutoResponseDto;
import com.wine.service.ClienteService;
import com.wine.service.CompraService;
import com.wine.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComprasController {
    private final CompraService compraService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public ComprasController(CompraService compraService, ClienteService clienteService, ProdutoService produtoService) {
        this.compraService = compraService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    /**
     * Busca todas as compras realizadas.
     *
     * @return Lista de compras realizadas.
     */

    @GetMapping("/compras")
    public ResponseEntity<List<ComprasResponse>> buscarCompras() {
        List<ComprasResponse> comprasResponses = compraService.listarCompras();
        return ResponseEntity.ok().body(comprasResponses);
    }

    /**
     * Busca os clientes fiéis.
     *
     * @return Lista de clientes fiéis.
     */
    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteFielResponse>> buscarClientesFieis() {
        List<ClienteFielResponse> clienteFielResponses = clienteService.buscarClientesFieis();
        return ResponseEntity.ok().body(clienteFielResponses);
    }

    /**
     * Busca um cliente por CPF.
     *
     * @param cpf CPF do cliente.
     * @return ClienteDto com os dados do cliente.
     */
    @GetMapping("/recomendacao/cliente/tipo/{cpf}")
    public ResponseEntity<ProdutoResponseDto> buscarRecomendacao(@PathVariable String cpf) {
        ProdutoResponseDto produtoDtos = produtoService.buscarRecomendacao(cpf);
        return ResponseEntity.ok().body(produtoDtos);
    }

    /**
     * Busca a maior compra realizada em um ano.
     *
     * @param ano Ano da compra.
     * @return MaiorCompraResponse com os dados da maior compra.
     */
    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<MaiorCompraResponse> buscarMaiorCompraPorAno(@PathVariable Integer ano) {
        MaiorCompraResponse maiorCompraResponse = compraService.buscarMaiorCompraPorAno(ano);
        return ResponseEntity.ok().body(maiorCompraResponse);
    }
}
