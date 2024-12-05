package com.wine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {
    @JsonProperty("codigo")
    private Long codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    @JsonProperty("preco")
    private Double preco;
    @JsonProperty("safra")
    private String safra;
    @JsonProperty("ano_compra")
    private Integer anoCompra;


}