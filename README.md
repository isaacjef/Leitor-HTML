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

# 📦 Disposição do programa:
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

- **Main** para execução das principais funções, que fazem o programa funcionar
- **Palestrante**, para definição dos atributos de palestrante, como: nome, email, local de trabalho e imagem. Métodos getters e setters, para melhor controle sobre o acesso aos atributos.
- **TratarDados**, onde estão definidas as principais funções utilizadas no programa, como: baixar a página HTML e converter para .txt; baixar imagem a partir de uma url informada; ler o arquivo .txt convertido, filtrando as informações necessárias via REFEX.
- **Database**, que funciona como uma ponte entre o banco de dados e nossa aplicação. E, definição de métodos que fazem a criação de tabelas, inserção de dados, exclusão de tabela e listagem de dados armazenados no BD.

# ♻️ Fluxograma:
- **Main:**
<img width="1193" height="597" alt="image" src="https://github.com/user-attachments/assets/f1521501-d687-4373-b31b-baf91fce2bed" />
- **TratarDados:**
<img width="1018" height="365" alt="image" src="https://github.com/user-attachments/assets/fee0bd1c-67d5-42f4-a0f0-ea0b1720b8e2" />

# 🔧 Principais Funções:
> - Função para baixar páginas .html e converter para .txt, através de métodos que baixam conteúdo de uma url dada e reescrevem cada linha deste arquivo(.html) um um novo arquivo, no diretório programado.
> - Função para baixar imagens de uma de uma determinada URL, baixada via método RenderedImage (caso queiram explicar), e armazenada em um diretório programado.
> - Função ArrayList<Palestrante> readTxt(), que lê o arquivo .txt gerado pela função baixarTxt, especifica as expressões regulares em variáveis, e lê cada linha do arquivo de texto via Scanner - while (sc.hasNextLine()) - sendo que, para cada linha, as exp. regulares são verificadas com o método da classe Pattern [...]. Como o Scanner lê cada linha uma vez, podemos garantir que os dados serão pegos de forma ordenada e padronizada, podendo utilizar o switch...case para controlar os dados que serão limitados via regex. Os dados obtidos são imediatamente salvos em um objeto do tipo Palestrante, que no final, resultam em todos os palestrantes, e são armazenados em uma lista.
> - 
# ✒️ Autores: 
| [<img src="https://avatars.githubusercontent.com/u/99749672?v=4" width=115><br><sub>Gabriel Alexandre</sub>](https://https://github.com/aieFaria) |  [<img src="https://lh3.googleusercontent.com/a-/ALV-UjUSbAUZs8fIDLpE2IxgftQvn59uYcg5JtGjnglwGdLloMGgyXM=s50-c-k-no" width=115><br><sub>Isaac Jerferson</sub>](https://github.com/guilhermeonrails) |  [<img src="https://avatars.githubusercontent.com/u/160502160?v=4" width=115><br><sub>Raislson Bernardo</sub>](https://github.com/alexfelipe) |
| :---: | :---: | :---: |


