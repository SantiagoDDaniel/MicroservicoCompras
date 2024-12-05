package com.wine.service;

import com.wine.dto.ComprasResponse;
import com.wine.dto.MaiorCompraResponse;

import java.util.List;

public interface CompraService {

    List<ComprasResponse> listarCompras();
    MaiorCompraResponse buscarMaiorCompraPorAno(int ano);
}
