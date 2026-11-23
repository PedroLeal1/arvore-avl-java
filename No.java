/** Representa um nó da árvore AVL. */
public class No {
    int valor;
    int altura;
    No esquerda;
    No direita;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1; // uma folha tem altura 1
    }
}
