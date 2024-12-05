package com.wine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {
    @Id
    private Long codigo;
    private String tipoVinho;
    private Double preco;
    private String safra;
    private Integer anoCompra;
}
