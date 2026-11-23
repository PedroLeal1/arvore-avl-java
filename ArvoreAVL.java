/**
 * Árvore AVL com a convenção da disciplina:
 * FB = altura(direita) - altura(esquerda).
 */
public class ArvoreAVL {
    private No raiz;
    private final boolean usarCores;

    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String VERMELHO = "\u001B[31m";

    public ArvoreAVL(boolean usarCores) {
        this.usarCores = usarCores;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    // Insere como uma BST e, na volta da recursão, atualiza e balanceia.
    private No inserir(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        } else {
            System.out.println("Valor " + valor + " ignorado: valores repetidos não são inseridos.");
            return no;
        }

        atualizarAltura(no);
        return balancear(no);
    }

    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    // Convenção da disciplina: direita - esquerda.
    private int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.direita) - altura(no.esquerda);
    }

    private No balancear(No no) {
        int fb = fatorBalanceamento(no);

        if (fb < -1) {
            int fbFilho = fatorBalanceamento(no.esquerda);
            if (fbFilho <= 0) {
                return casoLL(no);
            }
            return casoLR(no);
        }

        if (fb > 1) {
            int fbFilho = fatorBalanceamento(no.direita);
            if (fbFilho >= 0) {
                return casoRR(no);
            }
            return casoRL(no);
        }

        return no;
    }

    private No casoLL(No no) {
        int antes = contarNos(no);
        mostrarAntes(no, "LL", "rotação simples à direita", no.esquerda);
        System.out.println("\n[ROTAÇÃO] simples à direita no nó " + no.valor);
        No novaRaiz = rotacaoDireita(no);
        mostrarDepois(novaRaiz, antes);
        return novaRaiz;
    }

    private No casoRR(No no) {
        int antes = contarNos(no);
        mostrarAntes(no, "RR", "rotação simples à esquerda", no.direita);
        System.out.println("\n[ROTAÇÃO] simples à esquerda no nó " + no.valor);
        No novaRaiz = rotacaoEsquerda(no);
        mostrarDepois(novaRaiz, antes);
        return novaRaiz;
    }

    private No casoLR(No no) {
        int antes = contarNos(no);
        mostrarAntes(no, "LR", "rotação dupla esquerda-direita", no.esquerda);

        System.out.println("\n[PASSO 1] rotação à esquerda no nó " + no.esquerda.valor);
        no.esquerda = rotacaoEsquerda(no.esquerda);
        atualizarAltura(no);
        imprimirArvore(no);
        System.out.println("(agora é um caso LL)\n");

        System.out.println("[PASSO 2] rotação à direita no nó " + no.valor);
        No novaRaiz = rotacaoDireita(no);
        mostrarDepois(novaRaiz, antes);
        return novaRaiz;
    }

    private No casoRL(No no) {
        int antes = contarNos(no);
        mostrarAntes(no, "RL", "rotação dupla direita-esquerda", no.direita);

        System.out.println("\n[PASSO 1] rotação à direita no nó " + no.direita.valor);
        no.direita = rotacaoDireita(no.direita);
        atualizarAltura(no);
        imprimirArvore(no);
        System.out.println("(agora é um caso RR)\n");

        System.out.println("[PASSO 2] rotação à esquerda no nó " + no.valor);
        No novaRaiz = rotacaoEsquerda(no);
        mostrarDepois(novaRaiz, antes);
        return novaRaiz;
    }

    // p desce para a direita; u sobe. T2 não é perdida.
    private No rotacaoDireita(No p) {
        No u = p.esquerda;
        No t2 = u.direita;

        u.direita = p;
        p.esquerda = t2;

        atualizarAltura(p);
        atualizarAltura(u);
        if (t2 != null) {
            System.out.println("T2 preservada: nó " + t2.valor
                    + " passou para a esquerda do nó " + p.valor + ".");
        }
        return u;
    }

    // p desce para a esquerda; u sobe. T2 não é perdida.
    private No rotacaoEsquerda(No p) {
        No u = p.direita;
        No t2 = u.esquerda;

        u.esquerda = p;
        p.direita = t2;

        atualizarAltura(p);
        atualizarAltura(u);
        if (t2 != null) {
            System.out.println("T2 preservada: nó " + t2.valor
                    + " passou para a direita do nó " + p.valor + ".");
        }
        return u;
    }

    private void mostrarAntes(No no, String caso, String rotacao, No filho) {
        System.out.println("\n[ANTES DA ROTAÇÃO]");
        System.out.println("Nó desbalanceado: " + no.valor + " (FB " + formatarFB(fatorBalanceamento(no)) + ")"
                + " | filho decisivo: " + filho.valor + " (FB " + formatarFB(fatorBalanceamento(filho)) + ")");
        System.out.println("Caso identificado: " + caso + " -> " + rotacao);
        imprimirArvore(no);
    }

    private void mostrarDepois(No no, int antes) {
        System.out.println("\n[DEPOIS DA ROTAÇÃO]");
        imprimirArvore(no);
        System.out.println("Nós antes: " + antes + " | Nós depois: " + contarNos(no));
    }

    public void imprimirArvoreAtual() {
        imprimirArvore(raiz);
    }

    // Imprime a raiz no alto, com E (esquerda) e D (direita).
    private void imprimirArvore(No no) {
        if (no == null) {
            System.out.println("(árvore vazia)");
            return;
        }
        System.out.println(descreverNo(no));
        imprimirFilhos(no, "", "");
    }

    private void imprimirFilhos(No no, String prefixo, String marcador) {
        No[] filhos = {no.esquerda, no.direita};
        String[] lados = {"E", "D"};
        int existentes = 0;
        for (No filho : filhos) if (filho != null) existentes++;

        int mostrados = 0;
        for (int i = 0; i < filhos.length; i++) {
            No filho = filhos[i];
            if (filho == null) continue;
            mostrados++;
            boolean ultimo = mostrados == existentes;
            System.out.println(prefixo + marcador + (ultimo ? "└── " : "├── ") + lados[i] + ": " + descreverNo(filho));
            imprimirFilhos(filho, prefixo + marcador + (ultimo ? "    " : "│   "), "");
        }
    }

    private String descreverNo(No no) {
        int fb = fatorBalanceamento(no);
        String texto = no.valor + " [FB " + formatarFB(fb) + "]";
        if (!usarCores) return texto;
        String cor = fb == 0 ? VERDE : (Math.abs(fb) == 1 ? AMARELO : VERMELHO);
        return cor + texto + RESET;
    }

    private String formatarFB(int fb) {
        return fb > 0 ? "+" + fb : String.valueOf(fb);
    }

    private int contarNos(No no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }
}
