package com.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprasResponse {

    private Long id;
    private Integer quantidade;
    private ClienteDto cliente;
    private Double valorTotal;
}
