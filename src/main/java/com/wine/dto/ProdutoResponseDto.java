package com.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto {
    private Long codigo;
    private String tipoVinho;
    private Double preco;
    private String safra;
}
