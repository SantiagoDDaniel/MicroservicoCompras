package com.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFielResponse {
    private String nomeCliente;
    private String cpfCliente;
    private Long quantidadeDeCompras;
    private Double valorTotalGasto;
}