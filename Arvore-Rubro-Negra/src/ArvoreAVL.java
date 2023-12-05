import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
public class ArvoreAVL {
    No raiz;

    int altura(No no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    int obterBalanceamento(No no) {
        if (no == null)
            return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    No inserir(No no, int valor) {
        if (no == null)
            return new No(valor);

        if (valor < no.valor)
            no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
        else
            return no;

        no.altura = 1 + maximo(altura(no.esquerda), altura(no.direita));

        int balanceamento = obterBalanceamento(no);

        // Casos de desequilíbrio
        // Esquerda-Esquerda
        if (balanceamento > 1 && valor < no.esquerda.valor)
            return rotacaoDireita(no);

        // Direita-Direita
        if (balanceamento < -1 && valor > no.direita.valor)
            return rotacaoEsquerda(no);

        // Esquerda-Direita
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Direita-Esquerda
        if (balanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    void imprimir(No no) {
        if (no != null) {
            imprimir(no.esquerda);
            System.out.print(no.valor + " ");
            imprimir(no.direita);
        }
    }

    void imprimir() {
        imprimir(raiz);
    }

    int contarOcorrencias(No no, int valor) {
        if (no == null)
            return 0;

        if (valor < no.valor)
            return contarOcorrencias(no.esquerda, valor);
        else if (valor > no.valor)
            return contarOcorrencias(no.direita, valor);
        else
            return 1 + contarOcorrencias(no.esquerda, valor) + contarOcorrencias(no.direita, valor);
    }

    // Método para sortear números e aplicar operações na árvore
    void sortearEOperar(int quantidade) {
        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            int numeroSorteado = random.nextInt(19999) - 9999;

            if (numeroSorteado % 3 == 0) {
                System.out.println("Número sorteado (" + numeroSorteado + ") é múltiplo de 3. Inserindo na árvore.");
                inserir(numeroSorteado);
            } else if (numeroSorteado % 5 == 0) {
                System.out.println("Número sorteado (" + numeroSorteado + ") é múltiplo de 5. Removendo da árvore.");
                // Supondo que você já tenha implementado o método de remoção na sua classe
                // Se não, você precisará implementá-lo
                // remover(numeroSorteado);
            } else {
                System.out.println("Número sorteado (" + numeroSorteado + ") não é múltiplo de 3 ou 5. Contando ocorrências na árvore.");
                int ocorrencias = contarOcorrencias(raiz, numeroSorteado);
                System.out.println("Número " + numeroSorteado + " aparece na árvore " + ocorrencias + " vezes.");
            }
        }
    }
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
                long inicioExecucaoArvore = System.currentTimeMillis();

                // Converter e inserir cada número na árvore
                Arrays.stream(numerosStr)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .forEach(arvore::inserir);

                // Sortear 50.000 números e operar na árvore
                arvore.sortearEOperar(50000);

                long fimExecucaoArvore = System.currentTimeMillis();

                System.out.println("\nTempo total para execução: " + (fimExecucaoArvore - inicioExecucaoArvore) + " milissegundos");

            }
            // Fechar o BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

