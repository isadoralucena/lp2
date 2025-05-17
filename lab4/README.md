# MrBet

MrBet é um sistema de gerenciamento de times, campeonatos e apostas. A aplicação permite que usuários cadastrem times, organizem campeonatos com número limitado de participantes, registrem apostas com valor e colocação, e consultem informações diversas sobre esses elementos

## 📁 Estrutura do sistema
O sistema é composto pelas seguintes entidades principais:

- Time: representa um time de futebol com identificador único (ID), nome e mascote.

- Campeonato: representa um campeonato com número limitado de participantes e times inscritos.

- Aposta: representa uma aposta realizada em um time dentro de um campeonato, contendo valor e colocação.

- MrBetSistema: classe controller que centraliza a lógica e o gerenciamento dos dados.

## 🎯 Objetivos

Este projeto tem como objetivo exercitar conceitos fundamentais da programação orientada a objetos em Java, incluindo:

- Escrita e execução de **testes com JUnit**
- Reconhecimento de **relacionamentos entre classes**
- Uso de padrões GRASP
- Uso de estruturas de dados, como arrays, listas, conjuntos e mapas

## ▶️ Como Usar
O arquivo `Main.java` oferece uma interface por menu de texto. Ao executar o programa, ele:

1. Permite o cadastro e consulta de times.

2. Gerencia campeonatos com número limitado de participantes.

3. Registra apostas feitas em determinados campeonatos.

4. Exibe informações detalhadas de times, campeonatos e apostas.

O menu interativo inclui opções como:

```text
MENU
(T)Cadastrar Time
(E)Exibir Time
(C)Adicionar Campeonato
(P)Incluir Time em Campeonato
(V)Verificar Time em Campeonato
(A)Apostar
(R)Recuperar Apostas
(B)Exibir Campeonatos do Time
(S)Sair

Opção>
```
Cada opção aciona uma funcionalidade específica do sistema, que interage com a classe controladora MrBetSistema.

## 🧪 Testes
O projeto inclui testes automatizados utilizando o JUnit, garantindo o funcionamento correto das principais operações do sistema.