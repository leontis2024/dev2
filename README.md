# Leontis
Leontis é um aplicativo em desenvolvimento que será apresentado na Expo Tech 2024. O app permite escanear obras em museus, acessar guias digitais de museus, acompanhar notícias sobre o mundo da arte, entre outros recursos. 

## API do Leontis - Desenvolvimento II

### Descrição
Esse repositório guarda as APIs do aplicativo Leontis. 
A *API REST* desenvolvida com *Spring Boot* permite a comunicação entre a interface e o banco de dados **SQL** além de autenticação de acesso para segurança.
A *API REST* desenvolvida com *Spring Boot* permite que uma imagem cadastrada pelo usuário seja guardada no banco **Firebase** e retorna como resposta o link da imagem.

### Funcionalidades
1. **Gerenciamento de usuários:**
   - Operações CRUD (Create, Read, Update, Delete)
2. **Gerenciamento de imagens:**
   - Upload de imagens no banco de dados
   - Retorno do link da imagem
3. **Gerenciamento da relação usuário/museus**:
   - Seguir museus
   - Deixar de seguir museus
   - Consultar museus seguidos por um usuário
4. **Gerenciamento da relação gênero/usuário:**
   - Manifestar interesse em um gênero
   - Deixar de ter interesse em um genero
   - Consultar interesses de um usuário

## Tecnologias Utilizadas
- *Spring Boot*: Framework principal para o desenvolvimento da API.
- *Firebase*: Banco de dados NoSQL usado para o armazenamento de dados, especialmente imagens.
- *Swagger*: Ferramenta para documentação interativa da API.

### Passos do projeto:
 - [x] Criação da API de imagens(Firebase)
 - [x] Criação da API de dados de cadastro(SQL)
 - [x] Contrução da parte da API para usuários(SQL)
 - [x] Contrução da parte da API para relação usuário/museu(SQL) 
 - [X] Contrução da parte da API para relação usuário/gênero(SQL)
 - [ ] Contrução da parte da API para obras(SQL)
 - [X] Contrução da parte da API para gênero(SQL)
 - [ ] Contrução da parte da API para guia(SQL)
 - [ ] Contrução da parte da API para relação obra/guia(SQL)
 - [ ] Contrução da parte da API para artistas(SQL)
 - [ ] Contrução da parte da API para funcionamento(SQL)
 - [ ] Contrução da parte da API para interações(MongoDB)

### Links 
http://localhost:8080/swagger-ui/index.html

### Principais endpoints
- */api/usuario*: CRUD de usuários.
- */api/usuarioGenero*: Gerenciamento de interesse em generos.
- */api/usuarioMuseu*: Gerenciamento de seguidores dos museus.
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
