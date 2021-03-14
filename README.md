# 🌐 Web Service
#### Nesta branch foi implementado os métodos necessários para que pudesse haver comunicação entre o app de agenda e um web service. A aplicação é capaz de adicionar, editar e remover alunos da agenda, tanto no servidor quanto na app. Foi utilizado para fazer as atualizações: Retrofit, Firebase Cloud Messaging e EventBus.

#### Esta app foi desenvolvida com os cursos:
1. [Android com Web Service parte 1: Sincronize sua App com o Servidor](https://cursos.alura.com.br/course/android-sincronizacao-com-servidor)
2. [Android com Web Service parte 2: Mais técnicas de sincronização](https://cursos.alura.com.br/course/android-sincronizacao-com-servidor-parte-2)
3. [Android com Web Service parte 3: Sincronização em modo offline](https://cursos.alura.com.br/course/android-sincronizacao-com-servidor-parte-3)

## Como usar
#### O servidor utilizado encontra-se na pasta _servidor_web_
#### É necessário alterar o *base_url* no RetrofitInicializador.java
```java
retrofit = new Retrofit.Builder()
                .baseUrl("insert your ip and door here")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
````

###### :pushpin: Os cursos realizados foram gravados em 2016 utilizando a versão 2.3 do Android Studio e o SDK 23 do Android, com algumas atualizações feitas em 2018. Atualmente os cursos se encontram como desabilitados

## English
#### In this branch the necessary methods were implemented to make the connection between the agenda app and a web service. In th app we can add, edit and remove students from the agenda both in the server and in the app. For the upgrades we used: Retrofit, Firebase Cloud Messaging and EventBus.

#### This app was made with the courses:
1. Android with Web Service part 1: Synchronize your app with the server
2. Android with Web Service part 2: More synchronization techniques
3. Android with Web Service part 3: Offline mode sincronization

## How to use
#### The used server is in the _servidor_web_ folder
#### It's necessary to change the _base_url_ in RetrofitInicializador.java

###### :pushpin: The courses made were recorded in 2016 using the 2.3 Android Studio and Android 23 SDK, with some upgrades made in 2018. Now, the courses are disabled.
