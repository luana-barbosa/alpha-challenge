# StarWars Challenge :low_brightness:

Consiste na implementação de uma aplicação Android, utilizando a linguagem Kotlin, que consome a API pública do StarWarts (https://swapi.dev/api/), onde lista 3 categorias, sendo People, Films e Planets,
é possivel fazer a busca de usuário por nome/titulo e visualização das informações de um item específico.
Segue a documentação da api [(https://developer.github.com/v3/)](https://swapi.dev/documentation)

## Work In Progress :hammer:

- Estou realizando a migração de XML para compose
- Implementação nas dependencias para utilizar compose
- Alteração da lib de carregamento de Imagem, de Picasso para Coil
- Alteração da navegação para o compose

### Especificações Técnicas :heavy_check_mark:

- SDK (Versão Mínima): 23
- SDK (Versão Alvo): 34
- Arquitetura: MVVM + Clean Architecture

### Bibiliotecas utilizadas :heavy_check_mark:

- Lottie (Utilizada para animações em Json)
- Navigation (Usado para navegar entre fragmentos)
- Retrofit (Usado para comunicação da API REST)
- Picasso (Carregamento e cache de imagens)
- Koin (Injeção de Dependências)
- Coroutines (Como um padrão de design de simultaneidade para simplificar o código executado de forma assíncrona)
- LiveData (Para programação de estilo reativo (de VM para UI))
- Lifecycle (Usado para obter o evento de ciclo de vida de uma atividade ou fragmento e executa alguma ação em resposta à mudança)
- Entre outras.

### Testes Unitários :heavy_check_mark:
- Para os testes Unitários, foi utilizado JUnit4 e MockK sendo realizado testes da ViewModel, Use Case e Reposistory.

-  ### Contribuições :heavy_check_mark:
- Sinta-se à vontade para registrar um problema por erros, sugestões ou solicitações de recursos..

### Device:heavy_check_mark:
![Captura de tela 2024-01-26 200309](https://github.com/luana-barbosa/alpha-challenge/assets/64818568/d89726ef-961a-43ad-b7fa-602bd791ce86)

