package com.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaiorCompraResponse {
    private String nomeCliente;
    private String cpfCliente;
    private String tipoProduto;
    private String safraProduto;
    private int quantidade;
    private double valorTotal;
}