# 👓 Leitor HTML
**Objetivo:** desenvolver um código que receba o código fonte do site de eventos, do IF Goiano, baixe a página e transforme em um arquivo .txt, e a partir daí, que busque pelos dados dos palestrantes, através de REGEX, e salve-os num banco de dados.



# 📜 Tarefas:
    (1) [ X ] Extrair código fonte o site de eventos e salvar em txt;
    (2) [ X ] Ler TXT para extrair os dados dos palestrantes;
    (3) [ X ] Baixar as imagens de cada palestrante deve ser salva na pasta download;
    (4) [ X ] Criar banco de dados chamado event.db com os dados dos palestrantes.

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

# 📦 Disposição do programa:
     .
     ├── Leitor-HTML
     │    ├── src
     │    │    ├── Database.java
     │    │    ├── Main.java
     │    │    ├── Palestrante.java
     │    │    └── TratarDados.java
     │    ├── download
     │    │    ├── imagem1.png 
     │    │    └── imagem2.png
     │    └── db
     │         └── event.db    
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

- **Database:**
<img width="1477" height="545" alt="image" src="https://github.com/user-attachments/assets/d213fe9b-c247-42cb-a8b7-0153d91b9c45" />

# 🔧 Principais Funções:
- **TratarDados:**

As funções `baixarHTML()` e `baixarImagem()` funcionam de forma semelhante. Via parâmetro, definimos a URL, quer seja de um site ou de alguma imagem hospedada, e o nome com o qual o arquivo será salvo. A partir da classe URL, cria-se objetos url com as urls informadas, e por fim, os arquivos são baixados via: `BufferedReader(new InputStreamReader(url.openStream()))` para HTML, e `ImageIO.read(url)`, para imagens.
Para salvar o arquivo HTML como .txt, lê-se cara linha do objeto BufferedReader através de um while, e reescreve-se em um novo arquivo, com a classe `BufferedWriter` e o método `BufferedWriter.write()`. Já as imagens, são salvas no diretório programado com o método `ImageIO.write(...)`.

**Como os dados foram buscados e armazenados?** Podemos dividir estes processos em duas etapas: busca e armazenamento.
O método `readTxt()`, que faz a busca de dados, é o responsável pela leitura e filtragem dos dados dos palestrantes. A filtragem é feita a partir de métodos da classe *java.util.regex*: `Pattern.compile(regex)`; `Pattern.matcher("linha do arquivo")`

A expressão regular em Java é definida como uma String, porém, precisa ser compilada em uma instância da classe Matcher, para que seja possível utilizar os métodos de verificação de padrões da mesma, como `Matcher.matches()`, que checa se um determinado conjunto de caracteres bate com a expressão regular definida:
> <img width="785" height="92" alt="image" src="https://github.com/user-attachments/assets/e49256aa-912a-4cda-90db-22b00ed2737d" />

> <img width="414" height="163" alt="image" src="https://github.com/user-attachments/assets/627347b3-3da3-4048-b9bb-5a25ebbe4dcf" />

Assim, resta ler o conteúdo do site, que foi armazenado na pasta do projeto anteriormente. Para isso, utilizamos a classe *Scanner sc*, com dois métodos: `sc.hasNextLine()` dentro de um while, para verificar as linhas até o fim; `sc.nextLine()`, para armazenar a linha atual em uma variável texto. Para certificar de que os conteúdo serão lidos na ordem certa, utilizamos a estrutura switch com uma variável de controle
<img width="1018" height="365" alt="image" src="https://github.com/user-attachments/assets/fee0bd1c-67d5-42f4-a0f0-ea0b1720b8e2" />

- **Database:** O método `connect()` cria um objeto do tipo *Connection*, componente importante da biblioteca JDBC, e uma instância desta classe representa uma sessão com o BD. Aproveitamos para colocar o método `criarTabela()` dentro de `connect()`, para que a existência da tabela seja verificada a cada conexão, e que ela seja criada caso não exista. O método `inserirPalestrantes()`, exige que um *ArrayList<Palestrante>* seja passado via parâmetro - sendo que este arraylist pode ser obtido com o uso do método readTxt().  listarPalestrantes()

# ✒️ Autores: 
| [<img src="https://avatars.githubusercontent.com/u/99749672?v=4" width=115><br><sub>Gabriel Alexandre</sub>](https://https://github.com/aieFaria) |  [<img src="https://lh3.googleusercontent.com/a-/ALV-UjUSbAUZs8fIDLpE2IxgftQvn59uYcg5JtGjnglwGdLloMGgyXM=s50-c-k-no" width=115><br><sub>Isaac Jerferson</sub>](https://github.com/guilhermeonrails) |  [<img src="https://avatars.githubusercontent.com/u/160502160?v=4" width=115><br><sub>Raislson Bernardo</sub>](https://github.com/alexfelipe) |
| :---: | :---: | :---: |


> - Função ArrayList<Palestrante> readTxt(), que lê o arquivo .txt gerado pela função baixarTxt, especifica as expressões regulares em variáveis, e lê cada linha do arquivo de texto via Scanner - while (sc.hasNextLine()) - sendo que, para cada linha, as exp. regulares são verificadas com o método da classe Pattern [...]. Como o Scanner lê cada linha uma vez, podemos garantir que os dados serão pegos de forma ordenada e padronizada, podendo utilizar o switch...case para controlar os dados que serão limitados via regex. Os dados obtidos são imediatamente salvos em um objeto do tipo Palestrante, que no final, resultam em todos os palestrantes, e são armazenados em uma lista.
