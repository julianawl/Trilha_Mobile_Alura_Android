# :moneybag: Leilão
#### Nesta branch foi desenvolvido um conjunto de testes para verificar o funcionamento da aplicação de Leilão. Na app *LeilaoCopia*, é possível apenas verificar os lances já feitos. Quanto aos testes desenvolvidos, utilizou-se princípios básicos de testes automatizados, TDD, além de testes de unidade, e aqueles que esperam exceptions. Também foi utilizada a biblioteca [*hamcrest*](http://hamcrest.org).
#### O testes foram realizados com os cursos:
1. Android parte 1: testes automatizados e TDD
2. Android parte 2: Boas práticas e novos cenários de testes

# :moneybag: Leilão Att
#### Foi feita uma atualização, adicionando novas features como a possibilidade de adicionar usuários e lances. Além disso, foi feita uma conexão com uma api web disponibilizada pelo curso para armazenar os dados e adicionar itens para o leilão. Para a realização dos testes, utilizou-se a biblioteca [_Mockito_](https://site.mockito.org).

#### O testes foram realizados com o curso:
1. [Testes no Android: mocks e integrações](https://cursos.alura.com.br/course/testes-android-mockito-e-integracoes)

## Como usar
#### O servidor está localizado na pasta _servidor_leilao_
#### É necessário alterar a URL_BASE na classe RetrofitInicializador com o seu IP e a porta que você está usando
```java
private static final String URL_BASE = "insert your IP here;
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        OkHttpClient client = configuraHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
````
