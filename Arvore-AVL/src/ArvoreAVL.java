import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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

    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

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

            }
            // Fechar o BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Realizar operações da árvore AVL
        if (arvore != null) {

            System.out.println("Imprimindo os números pela arvoreAVL:");

            // Início da contagem de tempo de impressão (Árvore AVL)
            long inicioImpressaoArvore = System.currentTimeMillis();

            arvore.imprimir();

            long fimImpressaoArvore = System.currentTimeMillis();

            System.out.println("\nTempo total para impressão: " + (fimImpressaoArvore - inicioImpressaoArvore) + " milissegundos");
        }
    }
}
