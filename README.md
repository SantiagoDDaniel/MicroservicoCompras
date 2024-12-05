Infelizmente, eu não consigo gerar um arquivo diretamente para download. No entanto, você pode copiar o conteúdo abaixo, salvá-lo em um arquivo chamado README.md no seu editor de texto preferido ou no diretório do seu projeto.

# Teste Técnico - Microserviço de Compras

Este projeto é uma implementação de um microserviço em **Spring Boot** para atender aos requisitos de um teste técnico. O microserviço consome dados de produtos e clientes/compra disponibilizados via mocks e expõe endpoints RESTful para fornecer informações relevantes.

## Índice
- [Descrição](#descrição)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contato](#contato)

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
    "clienteNome": "João Silva",
    "clienteCpf": "123.456.789-00",
    "produto": {
      "nome": "Vinho Tinto",
      "tipo": "Tinto",
      "preco": 50.0
    },
    "quantidade": 2,
    "valorTotal": 100.0
  }
]
```
### 2. **GET /maior-compra/{ano}**

Retorna os detalhes da maior compra de um ano específico.
Exemplo: /maior-compra/2023
**Exemplo de Resposta:**
```json
{
  "clienteNome": "Maria Souza",
  "clienteCpf": "987.654.321-00",
  "produto": {
    "nome": "Espumante",
    "tipo": "Branco",
    "preco": 150.0
  },
  "quantidade": 3,
  "valorTotal": 450.0
}
```
### 3. **GET /clientes-fieis**

Retorna os 3 clientes mais fiéis, com base na recorrência e valores de compras.
**Exemplo de Resposta:**
```json
[
  {
    "clienteNome": "Carlos Pereira",
    "clienteCpf": "123.456.789-00",
    "comprasTotais": 25,
    "valorTotalGasto": 5000.0
  }
]
```
### 4. **GET /recomendacao/{cpf}**

Retorna uma recomendação de vinho baseada nos tipos mais comprados pelo cliente.
Exemplo: /recomendacao/12345678900/tinto
**Exemplo de Resposta:**
```json
{
  "clienteNome": "Ana Lima",
  "recomendacao": {
    "nome": "Vinho Reserva Especial",
    "tipo": "Tinto",
    "preco": 80.0
  }
```
