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
            BufferedReader br = new BufferedReader(new FileReader("/Users/pedronaves/Desktop/IF/4 Período /ED2/dados100_mil.txt"));
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

                arvoreRN.sortearEoperar(50000);

                long fimExecucaoRN = System.currentTimeMillis();
                System.out.println("\nTempo total para execução na Árvore Rubro-Negra: " + (fimExecucaoRN - inicioExecucaoRN) + " milissegundos");
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
}

