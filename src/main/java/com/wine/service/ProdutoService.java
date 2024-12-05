package com.wine.service;

import com.wine.dto.ProdutoDto;
import com.wine.dto.ProdutoResponseDto;


public interface ProdutoService {
    ProdutoDto buscarPorCodigo(Long codigo);
    ProdutoResponseDto buscarRecomendacao(String cpf);
}