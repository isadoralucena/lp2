# ComplementACAO

Sistema para gerenciamento de estudantes, dicas educacionais e atividades complementares acadêmicas. O sistema permite criar e gerenciar estudantes, adicionar dicas com diversos elementos (texto, multimídia, referências), controlar atividades complementares (monitoria, pesquisa, estágio, representação estudantil), gerar relatórios e acompanhar créditos acumulados.

O sistema foi desenvolvido para exercitar e aplicar diversos conceitos de Programação Orientada a Objetos (POO) e boas práticas de design em Java.

## 📁 Estrutura do sistema
O sistema é organizado em camadas:

- Facade: Ponto único de acesso para as funcionalidades do sistema, que encapsula a lógica das classes controladoras.

- Controllers: Classes responsáveis pelo controle das regras de negócio e interação com os modelos.

- Models: Classes que representam os objetos do domínio, como Estudante, Dica, Atividade, ElementoTexto, ElementoMultimidia, ElementoReferencia, entre outras. Elas encapsulam dados e comportamentos associados.

- Herança: Uso de classes abstratas e herança para criar hierarquias claras e reaproveitar código.

- Enums: Tipos enumerados são usados para definir categorias fixas, como tipos de atividades, subtipo de pesquisa, status e níveis de importância.

- Validadores: métodos dedicados a validar entradas, como CPF, senha, formatos de links e regras de negócio.

- Testes: Testes unitários desenvolvidos com JUnit para assegurar a qualidade e confiabilidade do sistema.

## ⚙️ Funcionalidades principais
- Gestão de Estudantes
    - Cadastro de estudantes com nome, CPF, senha e matrícula.

    - Validação dos dados de entrada (ex.: formato do CPF).

    - Alteração de senha.

    - Listagem de estudantes cadastrados.

    - Ordenação e ranking de usuários por número de dicas criadas.

- Gestão de Dicas
    - Criação de dicas temáticas associadas a estudantes.

    - Adição de elementos diversos nas dicas:
        - Texto

        - Multimídia (link, cabeçalho, tempo)

        - Referências (título, fonte, ano, conferida, importância)

    - Listagem e visualização detalhada de dicas.

- Gestão de atividades complementares
    - Criação de atividades dos tipos:

        - Monitoria

        - Pesquisa e Extensão

        - Estágio

        - Representação Estudantil

    - Validação e controle das atividades por meio de enums e classes específicas.

    - Alteração de descrição e comprovação das atividades.

    - Consulta e geração de relatórios parciais e finais sobre créditos.

    - Verificação de metas de créditos alcançadas.

    - Histórico de atividades e possibilidade de exclusão de itens do histórico.

## ▶️ Como usar
A classe Facade oferece uma interface programática para uso do sistema, integrando as operações de usuário e dica. Para interagir com o sistema via interface textual ou gráfica, deve-se usar os métodos expostos pela Facade.

Exemplo de uso típico:

```java
Facade facade = new Facade();

// Criar um estudante
facade.criarEstudante("João Silva", "12345678901", "senha123", "202312345");

// Alterar senha do estudante
facade.alterarSenhaEstudante("12345678901", "senha123", "novaSenha!");

// Adicionar uma dica para o estudante
int dicaId = facade.adicionarDica("12345678901", "novaSenha!", "Tema de Java");

// Adicionar elementos na dica
facade.adicionarElementoTextoDica("12345678901", "novaSenha!", dicaId, "Estudar POO é essencial.");
facade.adicionarElementoMultimidiaDica("12345678901", "novaSenha!", dicaId, "http://video.com/aula", "Aula sobre POO", 20);

// Gerar relatório final
String relatorio = facade.gerarRelatorioFinal("12345678901", "novaSenha!");
System.out.println(relatorio);
```

## 🎯 Objetivos

Este projeto foi desenvolvido para exercitar e consolidar os seguintes conceitos:

- Aplicação dos principais conceitos de Programação Orientada a Objetos (POO):

- Classes abstratas e herança.

- Interfaces e polimorfismo (de tipo e de código).

- Encapsulamento e visibilidade (public, private, protected).

- Implementação de comparação e ordenação de objetos.

- Uso dos padrões GRASP para boas práticas de design orientado a objetos.

- Implementação de relacionamentos entre classes:

    - Associação

    - Composição

    - Herança

- Uso de Enums para representar valores fixos.

- Desenvolvimento de métodos validadores para garantir integridade dos dados.

- Desenvolvimento e execução de testes unitários com JUnit para validar funcionalidades.

- Organização do código em pacotes coerentes e reutilizáveis.

- Geração de relatórios e manipulação.

- Implementação de uma interface de acesso única (Facade) para simplificar o uso do sistema.

## 🧪 Testes
O sistema conta com testes automatizados realizados com JUnit, que abrangem:

- Todas as funcionalidades principais do sistema, incluindo:

    - Criação, alteração e exclusão de estudantes, dicas e atividades.

    - Validação das regras de negócio.

    - Operações sobre os elementos das dicas.

    - Controle e verificação das atividades complementares.

    - Geração de relatórios e rankings.

- Casos de erro e situações excepcionais para garantir a robustez do sistema, tais como:

    - Dados inválidos ou incompletos.

    - Tentativas de operações não permitidas.

    - Validação de formatos (CPF, senhas, links).

    - Comportamento esperado frente a entradas inválidas.

Os testes asseguram a confiabilidade, estabilidade e a correta implementação das regras do sistema, facilitando manutenção e futuras evoluções.
