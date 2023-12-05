import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class MainRN {
    public static void main(String[] args) {
        ArvoreRN arvoreRN = new ArvoreRN();
        Random random = new Random();

        try {
            // Leitura do arquivo
            BufferedReader br = new BufferedReader(new FileReader("/caminho/do/seu/arquivo.txt"));
            String linha = br.readLine();

            // Remover colchetes e dividir a linha em números
            if (linha != null) {
                String[] numerosStr = linha.replaceAll("\\[|\\]", "").split(",\\s*");

                // Início da contagem de tempo de execução (Árvore Rubro-Negra)
                long inicioExecucaoRN = System.currentTimeMillis();

                // Converter e inserir cada número na árvore Rubro-Negra
                Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .forEach(arvoreRN::inserir);

                long fimExecucaoRN = System.currentTimeMillis();

                System.out.println("\nTempo total para execução na Árvore Rubro-Negra: " + (fimExecucaoRN - inicioExecucaoRN) + " milissegundos");
            }

            // Sorteio aleatório de 50.000 números
            for (int i = 0; i < 50000; i++) {
                int numeroSorteado = random.nextInt(19999) - 9999; // Números entre -9999 e 9999

                if (numeroSorteado % 3 == 0) {
                    arvoreRN.inserir(numeroSorteado);
                    int ocorrencias = arvoreRN.contarOcorrencias(numeroSorteado);
                    System.out.println("Número " + numeroSorteado + " inserido na árvore. Aparece na árvore " + ocorrencias + " vez(es).");
                } else if (numeroSorteado % 5 == 0) {
                    arvoreRN.remover(numeroSorteado);
                    System.out.println("Número " + numeroSorteado + " removido da árvore.");
                } else {
                    int ocorrencias = arvoreRN.contarOcorrencias(numeroSorteado);
                    System.out.println("Número sorteado (" + numeroSorteado + ") não é múltiplo de 3 ou 5. Contando ocorrências na árvore.");
                    System.out.println("Número " + numeroSorteado + " aparece na árvore " + ocorrencias + " vez(es).");
                }
            }

            // Fechar o BufferedReader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Impressão da árvore após as operações
        arvoreRN.imprimir();
    }
}

