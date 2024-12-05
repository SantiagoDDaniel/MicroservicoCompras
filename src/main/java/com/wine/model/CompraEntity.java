package com.wine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteEntity cliente;
    @ManyToOne
    private ProdutoEntity produto;
    private Integer quantidade;
    private Double valorTotal;

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = BigDecimal.valueOf(valorTotal).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
