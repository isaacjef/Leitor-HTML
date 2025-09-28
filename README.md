# 👓 Leitor HTML
Objetivo: desenvolver um código que receba baixe código fonte do site de eventos e busque pelos dados dos palestrantes, através de REGEX, e salve-os num banco de dados.



# 📜 Tarefas:
    (1) [ Feito ] Extrair código fonte o site de eventos e salvar em txt;
    (2) [  ] Ler TXT para extrair os dados dos palestrantes;
    (3) [ Feito ] Baixar as imagens de cada palestrante deve ser salva na pasta download;
    (4) [ Feito ] Criar banco de dados chamado event.db com os dados dos palestrantes.

# 📘 Descrição das tarefas:
### (3) Baixar imagem:
    Criada diretamente na classe Main.java
    > A função baixarImagem():
    → Salva imagem no diretório "download"
    → Retorna o caminho da imagem, para armazenar no atributo do Palestrante

### (4) Formato do event.db:
    id INTEGER PRIMARY KEY AUTOINCREMENT;
    name VARCHAR(255) NOT NULL;
    work VARCHAR(255) NOT NULL;
    email VARCHAR(255) NOT NULL;
    image VARCHAR(255) NOT NULL;

    - Classe Database.java: Serve para criação do banco de dados com base no dados dos palestrantes


# 📦 Disposição do programa
     .
     ├── Busca-Palestrantes
     │   ├── src
     │   │    ├── Main.java
     │   │    ├── Palestrante.java
     │   │    └── Database.java
     │   ├── download
     │   │    ├── imagem1.png 
     │   │    ├── ...
     │   │    └── imagem2.png 
     │   ├── db
     │   │    └── event.db
     │   └── lib
     │        └── sqlite-jdbc-3.50.1.0.jar  
     ├── README.md
     └── pagina_baixada.txt
     .

# 🔧 Principais Funções



# ✒️ Autores: 
| [<img src="https://avatars.githubusercontent.com/u/99749672?v=4" width=115><br><sub>Gabriel Alexandre</sub>](https://https://github.com/aieFaria) |  [<img src="https://lh3.googleusercontent.com/a-/ALV-UjUSbAUZs8fIDLpE2IxgftQvn59uYcg5JtGjnglwGdLloMGgyXM=s50-c-k-no" width=115><br><sub>Isaac Jerferson</sub>](https://github.com/guilhermeonrails) |  [<img src="https://avatars.githubusercontent.com/u/160502160?v=4" width=115><br><sub>Raislson Bernardo</sub>](https://github.com/alexfelipe) |
| :---: | :---: | :---: |


