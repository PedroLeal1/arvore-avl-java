# Árvore AVL em Java

Trabalho da disciplina **Estruturas de Dados II** (Unipê).

- **Professor:** Edkallenn Silva de Lima
- **Aluno:** Pedro Henrique Leal Vieira (RGM 43620779)
- **Vídeo de apresentação:** https://youtu.be/Utx3ypUxQ1w

## O que o programa faz

Implementa uma Árvore AVL com inserção e balanceamento automático. A cada rotação, o programa imprime a árvore **antes** e **depois**, mostrando o nó desbalanceado, o fator de balanceamento (FB), o caso identificado e a rotação aplicada.

Convenção usada: **FB = altura(direita) − altura(esquerda)**. Uma folha tem altura 1, e um nó vazio tem altura 0.

| Caso | Situação | Correção |
|------|----------|----------|
| LL | nó com FB −2 e filho esquerdo com FB ≤ 0 | rotação simples à direita |
| RR | nó com FB +2 e filho direito com FB ≥ 0 | rotação simples à esquerda |
| LR | nó com FB −2 e filho esquerdo com FB positivo | rotação dupla esquerda-direita |
| RL | nó com FB +2 e filho direito com FB negativo | rotação dupla direita-esquerda |

A `Main` roda 5 exemplos, um por vez (Enter passa para o próximo):

1. Caso LL: 30, 20, 10
2. Caso RR: 10, 20, 30
3. Caso LR: 30, 10, 20
4. Caso RL: 10, 30, 20
5. Caso LL com subárvore T2: 50, 30, 70, 20, 40, 10 (o 40 passa da direita do 30 para a esquerda do 50)

## Arquivos

- `No.java`: o nó (valor, altura, filhos esquerdo e direito).
- `ArvoreAVL.java`: inserção recursiva, alturas, fator de balanceamento, rotações e impressão da árvore.
- `Main.java`: os 5 exemplos.
- `DEMO-AVL.cmd`: compila e roda no Windows, com acentos e cores.

## Como rodar

Precisa do Java (JDK 11 ou mais novo).

No Windows, dê dois cliques em `DEMO-AVL.cmd`. Ou, em qualquer sistema:

```
javac -encoding UTF-8 *.java
java -Dstdout.encoding=UTF-8 Main
```

## Complexidade

| Operação | BST (melhor caso) | BST (pior caso) | AVL (pior caso) |
|----------|-------------------|-----------------|-----------------|
| Busca | O(log n) | O(n) | O(log n) |
| Inserção | O(log n) | O(n) | O(log n) |
| Remoção | O(log n) | O(n) | O(log n) |

Cada rotação muda só algumas referências, em tempo constante, O(1).

## Referências

- ADELSON-VELSKY, G. M.; LANDIS, E. M. An algorithm for the organization of information. *Soviet Mathematics Doklady*, v. 3, p. 1259–1263, 1962.
- CORMEN, T. H. et al. *Algoritmos: teoria e prática*. 3. ed. Rio de Janeiro: Elsevier, 2012.
- SZWARCFITER, J. L.; MARKENZON, L. *Estruturas de dados e seus algoritmos*. 3. ed. Rio de Janeiro: LTC, 2010.
