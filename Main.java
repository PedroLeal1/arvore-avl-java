import java.util.Scanner;

public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in);

    public static void main(String[] args) {
        executarCaso("Caso LL", new int[]{30, 20, 10});
        pausar();
        executarCaso("Caso RR", new int[]{10, 20, 30});
        pausar();
        executarCaso("Caso LR", new int[]{30, 10, 20});
        pausar();
        executarCaso("Caso RL", new int[]{10, 30, 20});
        pausar();
        executarCaso("Caso LL com subárvore T2", new int[]{50, 30, 70, 20, 40, 10});

        System.out.println("\nDemonstração concluída.");
    }

    private static void executarCaso(String titulo, int[] valores) {
        System.out.println("\n========================================");
        System.out.println("=== " + titulo + " ===");
        System.out.println("========================================");

        ArvoreAVL arvore = new ArvoreAVL(true);
        for (int valor : valores) {
            System.out.println("\nInserindo " + valor + "...");
            arvore.inserir(valor);
        }

        System.out.println("\nÁrvore final do " + titulo + ":");
        arvore.imprimirArvoreAtual();
    }

    private static void pausar() {
        System.out.println("\nPressione Enter para o próximo caso...");
        ENTRADA.nextLine();
    }
}
