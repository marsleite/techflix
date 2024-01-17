# Sistema de Gestão de Vídeos - Grupo29

## Introdução

Bem-vindo ao Sistema de Gestão de Vídeos! Este projeto é uma aplicação backend desenvolvida em Java, utilizando o framework Spring Boot com o módulo WebFlux. O objetivo é fornecer uma plataforma eficiente para a gestão, categorização e recomendação de vídeos.

## Configuração do Projeto

### Requisitos

- JDK 17
- Gradle
- Spring Boot 3.1.7

### Configuração do Banco de Dados

1. Configure as propriedades do banco de dados no arquivo `application.yml`.
2. Certifique-se de ter um servidor MySQL em execução.

### Executando o Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/sistema-gestao-videos.git

### Execute a aplicação com Gradle:

   ```bash
   gradle bootRun
   ```
A aplicação estará disponível em http://localhost:8080.

## Endpoints

### Vídeos
- Criar um vídeo: `POST /videos`

```json
{
  "titulo": "Título do Vídeo",
  "descricao": "Descrição do Vídeo",
  "url": "https://www.youtube.com/watch?v=123456789",
  "categoria": "Categoria do Vídeo",
  "dataPublicacao": "2024-01-15"
}
```

- Listar todos os vídeos: `GET /videos`

- Listar um vídeo específico: `GET /videos/{id}`

- Atualizar um vídeo: `PUT /videos/{id}`

```json
{
  "titulo": "Título do Vídeo",
  "descricao": "Descrição do Vídeo",
  "url": "https://www.youtube.com/watch?v=123456789",
  "categoria": "Categoria do Vídeo"
}
```

- Deletar um vídeo: `DELETE /videos/{id}`

### Categorias
- Criar uma categoria: `POST /categorias`

```json
{
  "nome": "Nome da Categoria"
}
```

- Listar todas as categorias: `GET /categorias`

- Listar uma categoria específica: `GET /categorias/{id}`

### Usuários
- Criar um usuário: `POST /usuarios`

```json
{
  "nome": "Nome do Usuário",
  "email": "email@email.com",
}
```

- Listar todos os usuários: `GET /usuarios`

- Listar um usuário específico: `GET /usuarios/{id}`

### Favoritos

- Marcar Vídeo como Favorito: `POST /usuarios/{id}/favoritos`

```json
{
  "idVideo": 1,
  "idUsuario": 1
}
```

- Desmarcar Vídeo como Favorito: `DELETE /usuarios/{id}/favoritos/{idVideo}`

### Estatísticas

- Listar os vídeos mais recentes: `GET /estatisticas/videos-mais-recentes`

### Conclusão

Obrigado por utilizar o Sistema de Gestão de Vídeos! Esperamos que tenha gostado. Caso tenha alguma dúvida, entre em contato conosco pelo e-mail: test@techflix.com