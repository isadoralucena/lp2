# 🎬 FilmNow - Sistema de Gestão de Filmes

**FilmNow** é um sistema simples de gerenciamento de filmes, que simula o armazenamento e controle de uma lista pessoal de filmes. Permite adicionar, listar, detalhar e destacar filmes em uma **HotList** personalizada.

## 🎯 Objetivos

Este projeto tem como objetivo exercitar conceitos fundamentais da programação orientada a objeto, incluindo:

- Escrita e execução de **testes com JUnit**
- Reconhecimento de **composição** e **relacionamentos entre classes**
- Uso correto dos métodos `equals()` e `hashCode()`
- Tratamento de **exceções**
- Manipulação de arquivos (leitura de dados de um arquivo `.csv`)
- Estruturas de dados como arrays

## 📁 Estrutura do sistema

As principais funcionalidades do sistema são:

- ✅ Adicionar filmes ao sistema
- 📋 Listar todos os filmes cadastrados
- 🔍 Exibir detalhes de um filme específico
- 🌟 Gerenciar uma **HotList**, com adição e remoção de filmes
- 📂 Carregar filmes a partir de um arquivo CSV

## ▶️ Como Usar

A classe `MainFilmNow.java` oferece uma interface por menu de texto. Ao executar o programa, ele:

1. Carrega os filmes a partir de um arquivo chamado `filmes_inicial.csv`
2. Exibe um menu com opções como:

```text
---
MENU
(A)Adicionar filme
(M)Mostrar todos
(D)Detalhar filme
(E)Exibir HotList
(H)Atribuir Hot
(R)Remover Hot
(S)Sair

Opção>
```

## 🧪 Testes
O projeto inclui testes automatizados utilizando o JUnit, garantindo o funcionamento correto das principais operações do sistema.