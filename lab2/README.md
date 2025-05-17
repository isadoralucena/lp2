# CoISA - Controle Institucional da Situação Acadêmica
Sistema simples que simula o gerenciamento da vida acadêmica de um estudante universitário.

## 🎯 Objetivos

- Criação e uso de classes e objetos

- Princípios de encapsulamento e ocultação da informação

- Métodos acessores (getters) e modificadores (setters)

- Implementação do método toString(), equals(), hashCode(), entre outros

## 📁 Estrutura do sistema

O sistema simula quatro aspectos da vida acadêmica:

- Descanso: registra horas de descanso e calcula o status geral.

- Tempo online: acompanha o tempo online dedicado a disciplinas.

- Disciplina: controla notas e carga horária de uma disciplina.

- Resumos: permite adicionar e consultar resumos de conteúdos estudados.

O método main (na classe `Coisa.java`) demonstra o uso de cada módulo:

```java
public static void main(String[] args) {
    registrarDescanso();
    registrarTempoOnline();
    controlarDisciplina();
    registrarResumos();
}
```

Cada método simula uma funcionalidade do sistema, com saídas impressas no console.