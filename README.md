# Leontis
Leontis é um aplicativo em desenvolvimento que será apresentado na Expo Tech 2024. O app permite escanear obras em museus, acessar guias digitais de museus, acompanhar notícias sobre o mundo da arte, entre outros recursos.

## API do Leontis - Desenvolvimento II

### Descrição
Esse repositório guarda as APIs do aplicativo Leontis.
A *API REST* desenvolvida com *Spring Boot* permite a comunicação entre a interface e o banco de dados **SQL** além de autenticação de acesso para segurança.


### Funcionalidades
1. **Gerenciamento de usuários:**
   - Operações CRUD (Create, Read, Update, Delete)
2. **Gerenciamento da relação usuário/museus**:
   - Seguir museus
   - Deixar de seguir museus
   - Consultar museus seguidos por um usuário
3. **Gerenciamento da relação gênero/usuário:**
   - Manifestar interesse em um gênero
   - Deixar de ter interesse em um genero
   - Consultar interesses de um usuário
4. **Gerenciamento de busca artista:**
   - Consultar artistas
5. **Gerenciamento de busca artista/gênero:**
   - Consultar relacionamento de artista com genero
6. **Gerenciamento de busca dia de funcionamento:**
   - Consultar dia de funcionamento de um museu
7. **Gerenciamento de busca endereço de museu:**
   - Consultar endereço de um museu
8. **Gerenciamento de busca gênero:**
   - Consultar gêneros
9. **Gerenciamento de busca guia:**
   - Consultar guias
10. **Gerenciamento de busca museu:**
- Consultar museus
11. **Gerenciamento de busca obra:**
- Consultar obras


## Tecnologias Utilizadas
- *Spring Boot*: Framework principal para o desenvolvimento da API.
- *Postgres SQL*: Banco de dados usado para o armazenamento de dados.
- *Swagger*: Ferramenta para documentação interativa da API.

### Passos do projeto:
- [x] Criação da API de dados de cadastro(SQL)
- [x] Contrução da parte da API para usuários(SQL)
- [x] Contrução da parte da API para relação usuário/museu(SQL)
- [X] Contrução da parte da API para relação usuário/gênero(SQL)
- [X] Contrução da parte da API para obras(SQL)
- [X] Contrução da parte da API para gênero(SQL)
- [X] Contrução da parte da API para guia(SQL)
- [X] Contrução da parte da API para relação obra/guia(SQL)
- [X] Contrução da parte da API para artistas(SQL)
- [X] Contrução da parte da API para funcionamento(SQL)
- [X] Contrução da parte da API para interações(MongoDB)

### Links
https://dev2-tfqz.onrender.com/swagger-ui/index.html

### Principais endpoints
- */api/usuario*: CRUD de usuários.
- */api/usuarioGenero*: Gerenciamento de interesse em generos.
- */api/usuarioMuseu*: Gerenciamento de seguidores dos museus.
- */api/artista*: Gerenciamento de busca de artistas.
- */api/artistaGenero*: Gerenciamento de busca de relacionamento artista/gênero.
- */api/diaFuncionamento*: Gerenciamento de busca de dia de funcionamento de museu.
- */api/enderecoMuseu*: Gerenciamento de busca de endereço de museu.
- */api/genero*: Gerenciamento de busca de gênero.
- */api/guia*: Gerenciamento de busca de guia.
- */api/museu*: Gerenciamento de busca de museu.
- */api/obra*: Gerenciamento de busca de obra.
### Commits Semânticos
- **Feat:** (nova funcionalidade para o usuário, não uma nova funcionalidade para o script de build)
- **Fix:** (correção de bug para o usuário, não uma correção para um script de build)
- **Docs:** (mudanças na documentação)
- **Style:** (formatação, falta de ponto e vírgula, etc.; nenhuma mudança no código de produção)
- **Refactor:** (refatoração de código de produção, por exemplo, renomeando uma variável)
- **Test:** (adicionando testes que estavam faltando, refatorando testes; nenhuma mudança no código de produção)
### Equipe
❤️ Feito com carinho por:
- [Ana Romera](https://github.com/anaBeatrizRomera)
- [Neife Junior](https://github.com/NeifeJunior)
   