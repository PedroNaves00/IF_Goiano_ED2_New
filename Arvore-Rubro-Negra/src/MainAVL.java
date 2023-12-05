import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MainAVL {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        try {
            // Leitura do arquivo
            BufferedReader br = new BufferedReader(new FileReader("/Users/pedronaves/Desktop/IF/4 Período /ED2/dados100_mil.txt"));
            String linha = br.readLine();

            // Remover colchetes e dividir a linha em números
            if (linha != null) {
                String[] numerosStr = linha.replaceAll("\\[|\\]", "").split(",\\s*");

                // Início da contagem de tempo de execução (Árvore AVL)
                long iniciExecucaoArvore = System.currentTimeMillis();

                // Converter e inserir cada número na árvore
                Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .forEach(arvore::inserir);

                // Sortear 50.000 números e operar na árvore
                arvore.sortearEOperar(50000);

                long fimExecucaoArvore = System.currentTimeMillis();
                System.out.println("\nTempo total para preencher a arvore: " + (fimExecucaoArvore - iniciExecucaoArvore) + " milissegundos \n");
            }
            // Fechar o BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
