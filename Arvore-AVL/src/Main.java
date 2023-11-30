import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        int[] numerosForQuicksort = null;

        try {
            // Leitura do arquivo
            BufferedReader br = new BufferedReader(new FileReader("/Users/pedronaves/Desktop/IF/4 Período /ED2/dados100_mil.txt"));
            String linha = br.readLine();

            // Remover colchetes e dividir a linha em números
            if (linha != null) {
                String[] numerosStr = linha.replaceAll("\\[|\\]", "").split(",\\s*");

                // Converter e inserir cada número na árvore
                Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .forEach(arvore::inserir);

                // Converter e armazenar cada número para o QuickSort
                numerosForQuicksort = Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            // Fechar o BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Realizar operações da árvore AVL
        if (arvore != null) {

            System.out.println("Aplicando o algoritmo arvoreAVL:");
            // Início da contagem de tempo de impressão (Árvore AVL)
            long inicioImpressaoArvore = System.currentTimeMillis();

            // Realizar operações de impressão
            arvore.imprimir();

            // Fim da contagem de tempo de impressão (Árvore AVL)
            long fimImpressaoArvore = System.currentTimeMillis();

            // Imprimir o tempo de impressão (Árvore AVL)
            System.out.println("\nTempo total para impressão (Árvore AVL): " + (fimImpressaoArvore - inicioImpressaoArvore) + " milissegundos");
        }

        // Realizar operações do QuickSort
        if (numerosForQuicksort != null) {

            System.out.println("Aplicando o algoritmo quicksort:");
            // Início da contagem de tempo de ordenação (QuickSort)
            long inicioOrdenacao = System.currentTimeMillis();

            // Aplicar o algoritmo QuickSort
            QuickSort.quickSort(numerosForQuicksort, 0, numerosForQuicksort.length - 1);

            // Imprimir os valores ordenados
            System.out.println("Valores ordenados (QuickSort):");
            QuickSort.imprimirArray(numerosForQuicksort);

            // Fim da contagem de tempo de ordenação (QuickSort)
            long fimOrdenacao = System.currentTimeMillis();
            // Imprimir o tempo de ordenação (QuickSort)
            System.out.println("\nTempo total para ordenação (QuickSort): " + (fimOrdenacao - inicioOrdenacao) + " milissegundos");

        }
    }
}
