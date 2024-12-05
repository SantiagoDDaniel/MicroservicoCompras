# Teste Técnico - Microserviço de Compras

Este projeto é uma implementação de um microserviço em **Spring Boot** para atender aos requisitos de um teste técnico. O microserviço consome dados de produtos e clientes/compra disponibilizados via mocks e expõe endpoints RESTful para fornecer informações relevantes.

## Índice
- [Descrição](#descrição)
- [Endpoints Disponíveis](#endpoints-disponíveis)
---

## Descrição

O microserviço consome os seguintes dados mockados:
- **Produtos**: [Mock de Produtos](https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json)
- **Clientes e Compras**: [Mock de Clientes e Compras](https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json)

Com base nesses dados, o microserviço expõe os seguintes endpoints.

---

## Endpoints Disponíveis

### 1. **GET /compras**
Retorna uma lista de compras ordenadas de forma crescente por valor total.  
**Exemplo de Resposta**:
```json
[
  {
    "id": 24,
    "quantidade": 2,
    "cliente": {
      "nome": "Hadassa Daniela Sales",
      "cpf": "1051252612",
      "compras": [
        {
          "codigo": "19",
          "quantidade": 3,
          "tipoVinho": "Espumante",
          "preco": 135.5,
          "safra": "2019",
          "anoCompra": 2020
        },
        {
          "codigo": "17",
          "quantidade": 3,
          "tipoVinho": "Branco",
          "preco": 125.25,
          "safra": "2017",
          "anoCompra": 2018
        },
        {
          "codigo": "12",
          "quantidade": 2,
          "tipoVinho": "Branco",
          "preco": 106.5,
          "safra": "2018",
          "anoCompra": 2019
        }
      ]
    },
    "valorTotal": 213
  },
  {
    "id": 31,
    "quantidade": 2,
    "cliente": {
      "nome": "Fabiana Melissa Nunes",
      "cpf": "824643755772",
      "compras": [
        {
          "codigo": "18",
          "quantidade": 2,
          "tipoVinho": "Rosé",
          "preco": 120.99,
          "safra": "2018",
          "anoCompra": 2019
        },
        {
          "codigo": "10",
          "quantidade": 10,
          "tipoVinho": "Chardonnay",
          "preco": 130.75,
          "safra": "2020",
          "anoCompra": 2021
        }
      ]
    },
    "valorTotal": 241.98
  }
]
```
### 2. **GET /maior-compra/{ano}**

Retorna os detalhes da maior compra de um ano específico.
Exemplo: /maior-compra/2020
**Exemplo de Resposta:**
```json
{
  "nomeCliente": "Ian Joaquim Giovanni Santos",
  "cpfCliente": "96718391344",
  "tipoProduto": "Rosé",
  "safraProduto": "2019",
  "quantidade": 20,
  "valorTotal": 2435
}
```
### 3. **GET /clientes-fieis**

Retorna os 3 clientes mais fiéis, com base na recorrência e valores de compras.
**Exemplo de Resposta:**
```json
[
  {
    "nomeCliente": "Andreia Emanuelly da Mata",
    "cpfCliente": "27737287426",
    "quantidadeDeCompras": 6,
    "valorTotalGasto": 3210.19
  },
  {
    "nomeCliente": "Ian Joaquim Giovanni Santos",
    "cpfCliente": "96718391344",
    "quantidadeDeCompras": 5,
    "valorTotalGasto": 7631.69
  },
  {
    "nomeCliente": "Geraldo Pedro Julio Nascimento",
    "cpfCliente": "05870189179",
    "quantidadeDeCompras": 5,
    "valorTotalGasto": 3416.87
  }
]
```
### 4. **GET /recomendacao/cliente/tipo/{cpf}**

Retorna uma recomendação de vinho baseada nos tipos mais comprados pelo cliente.
Exemplo: /recomendacao/cliente/tipo/9671839134
**Exemplo de Resposta:**
```json
{
  "codigo": 17,
  "tipoVinho": "Branco",
  "preco": 125.25,
  "safra": "2017"
}
```
