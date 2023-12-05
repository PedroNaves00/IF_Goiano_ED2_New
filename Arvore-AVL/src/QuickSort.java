import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class QuickSort {
    public static void quickSort(int[] array, int baixo, int alto) {
        if (baixo < alto) {
            int indiceParticao = particionar(array, baixo, alto);
            quickSort(array, baixo, indiceParticao - 1);
            quickSort(array, indiceParticao + 1, alto);
        }
    }

    static int particionar(int[] array, int baixo, int alto) {
        int pivo = array[alto];
        int i = (baixo - 1);

        for (int j = baixo; j < alto; j++) {
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }

        trocar(array, i + 1, alto);
        return i + 1;
    }

    static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void imprimirArray(int[] array) {
        for (int valor : array) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numerosForQuicksort = null;

        try {
            // Leitura do arquivo
            BufferedReader br = new BufferedReader(new FileReader("/Users/pedronaves/Desktop/IF/4 Período /ED2/dados100_mil.txt"));
            String linha = br.readLine();

            // Remover colchetes e dividir a linha em números
            if (linha != null) {
                String[] numerosStr = linha.replaceAll("\\[|\\]", "").split(",\\s*");

                // Converter e armazenar cada número para o QuickSort
                numerosForQuicksort = Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Aplicar o algoritmo QuickSort
                QuickSort.quickSort(numerosForQuicksort, 0, numerosForQuicksort.length - 1);
            }
            // Fechar o BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Realizar operações do QuickSort
        if (numerosForQuicksort != null) {

            System.out.println("Imprimindo os números pelo quicksort:");

            long inicioOrdenacao = System.currentTimeMillis();

            QuickSort.imprimirArray(numerosForQuicksort);

            long fimOrdenacao = System.currentTimeMillis();
            // Imprimir o tempo de ordenação (QuickSort)
            System.out.println("\nTempo total para impressão: " + (fimOrdenacao - inicioOrdenacao) + " milissegundos");

        }
    }
}