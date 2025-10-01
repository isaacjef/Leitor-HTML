# 👓 Leitor HTML
**Objetivo:** desenvolver um código que receba o código fonte do site de eventos, do IF Goiano, baixe a página e transforme em um arquivo .txt, e a partir daí, que busque pelos dados dos palestrantes, através de REGEX, e salve-os num banco de dados.



# 📜 Tarefas:
    (1) [ X ] Extrair código fonte o site de eventos e salvar em txt;
    (2) [ X ] Ler TXT para extrair os dados dos palestrantes;
    (3) [ X ] Baixar as imagens de cada palestrante deve ser salva na pasta download;
    (4) [ X ] Criar banco de dados chamado event.db com os dados dos palestrantes.

# 📘 Descrição das tarefas:
### (3) Formato do event.db:
    id INTEGER PRIMARY KEY AUTOINCREMENT;
    name VARCHAR(255) NOT NULL;
    work VARCHAR(255) NOT NULL;
    email VARCHAR(255) NOT NULL;
    image VARCHAR(255) NOT NULL;

# 📦 Disposição do programa
     .
     ├── Busca-Palestrantes
     │   ├── src
     |   |    ├── Database.java
     │   │    ├── Main.java
     |   |    ├── Palestrante.java
     |   |    └── TratarDados.java
     │   ├── download
     |   |    ├── imagem1.png 
     |   |    └── imagem2.png
     |   └── db
     |        └── event.db    
     ├── README.md
     └── eventos_ifgoiano.txt
     .

# 📚 Classes:
<img width="1013" height="684" alt="image" src="https://github.com/user-attachments/assets/a86c55d5-9baa-4743-9ecf-9c4e50517da9" />


# 🔧 Principais Funções




# ✒️ Autores: 
| [<img src="https://avatars.githubusercontent.com/u/99749672?v=4" width=115><br><sub>Gabriel Alexandre</sub>](https://https://github.com/aieFaria) |  [<img src="https://lh3.googleusercontent.com/a-/ALV-UjUSbAUZs8fIDLpE2IxgftQvn59uYcg5JtGjnglwGdLloMGgyXM=s50-c-k-no" width=115><br><sub>Isaac Jerferson</sub>](https://github.com/guilhermeonrails) |  [<img src="https://avatars.githubusercontent.com/u/160502160?v=4" width=115><br><sub>Raislson Bernardo</sub>](https://github.com/alexfelipe) |
| :---: | :---: | :---: |


