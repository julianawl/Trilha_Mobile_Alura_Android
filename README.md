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
