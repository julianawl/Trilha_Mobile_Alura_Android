# :moneybag: Leilão
#### Nesta branch foi desenvolvido um conjunto de testes para verificar o funcionamento da aplicação de Leilão. Na app *LeilaoCopia*, é possível apenas verificar os lances já feitos. Quanto aos testes desenvolvidos, utilizou-se princípios básicos de testes automatizados, TDD, além de testes de unidade, e aqueles que esperam exceptions. Também foi utilizada a biblioteca [*hamcrest*](http://hamcrest.org).
#### O testes foram realizados com os cursos:
1. Android parte 1: testes automatizados e TDD
2. Android parte 2: Boas práticas e novos cenários de testes

## English
#### In this branch was developed series of tests to verify the operation of this app. With the app is possible to verify the bids already made. As the developed tests, we used the basic principles of test automation, TDD, and unit tests, those which expect exceptions. Also, it was used the [_hamcrest_](http://hamcrest.org) library. This app is located in the folder _LeilaoCopia_.
#### The tests were made with the courses:
1. Android part 1: Test automation and TDD
2. Android part 2: Best practices and new tests scenarios

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

## English
#### An upgrade was made, adding new features like the possibility to add users and bids. Also, a connection with an API, made available by the course, was made to store data and add new items to the auction. For the tests, we used the [_Mockito_](https://site.mockito.org) library.

#### The tests were made with the course:
1. Tests on Android: mocks and integrations

## How to use
#### The server is located in the folder _servidor_leilao_
#### It's necessary to change the URL_BASE in the class RetrofitInicializador with your IP and the door you're using
