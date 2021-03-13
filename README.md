# :package: Estoque
#### Nesta branch é desenvolvido uma app para controle de estoque. Nele é possível adicionar, excluir e editar produtos em estoque, além de poder visualizar uma lista com todos os produtos. Todos os produtos são salvos internamente e externamente.

#### A app foi desenvolvida com o curso:
1. [Android: Acessando uma API web](https://cursos.alura.com.br/course/android-api-web)

## Como usar
#### Na pasta *"servidor"* está o servidor utilizado para testar o funcionamento da aplicação.
#### Na classe _EstoqueRetrofit_ altere a URL_BASE com o seu IP e a porta que estará utilizando

  ```java
  private static final String URL_BASE = "192.0.0.0:8080";
  
  public EstoqueRetrofit() {        
        OkHttpClient client = configuraClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        produtoService = retrofit.create(ProdutoService.class);
    }
  ````
## English
#### In this branch is developed an app for inventory control. In it is possible to add, delete and edit products in the inventory, besides it is possible to view a list with all products. They are stored internally and externally.

#### The app was developed with the course:
1. Android: Accessing an web API

## How to use
#### In the _"servidor"_ folder is the server used to test the app
#### In the class _EstoqueRetrofit_ change the URL_BASE with your IP and the door you'll be using
