package com.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto {
    private String codigo;
    private Integer quantidade;
    private String tipoVinho;
    private Double preco;
    private String safra;
    private Integer anoCompra;
}
