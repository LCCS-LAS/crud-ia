# CRUD completo com Java + Spring Boot

Projeto de exemplo com CRUD de **produtos** usando:

- Spring Web
- Spring Data JPA
- Bean Validation
- Banco H2 em memória

## Como executar

```bash
mvn spring-boot:run
```

A API sobe por padrão em `http://localhost:8080`.

## Endpoints

- `POST /api/produtos` - cria produto
- `GET /api/produtos` - lista produtos
- `GET /api/produtos/{id}` - busca produto por ID
- `PUT /api/produtos/{id}` - atualiza produto
- `DELETE /api/produtos/{id}` - remove produto

### Exemplo de payload

```json
{
  "nome": "Teclado mecânico",
  "descricao": "Switch blue",
  "preco": 299.90,
  "quantidadeEstoque": 10
}
```

## Console do H2

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:cruddb`
- user: `sa`
- senha: *(vazia)*
