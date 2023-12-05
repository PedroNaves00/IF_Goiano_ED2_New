import java.util.Random;
public class ArvoreAVL {
    NoAVL raiz;

    int altura(NoAVL no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    int obterBalanceamento(NoAVL no) {
        if (no == null)
            return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    NoAVL inserir(NoAVL no, int valor) {
        if (no == null)
            return new NoAVL(valor);

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

    void imprimir(NoAVL no) {
        if (no != null) {
            imprimir(no.esquerda);
            System.out.print(no.valor + " ");
            imprimir(no.direita);
        }
    }

    void imprimir() {
        imprimir(raiz);
    }

    int contarOcorrencias(NoAVL no, int valor) {
        if (no == null)
            return 0;

        if (valor < no.valor)
            return contarOcorrencias(no.esquerda, valor);
        else if (valor > no.valor)
            return contarOcorrencias(no.direita, valor);
        else
            return 1 + contarOcorrencias(no.esquerda, valor) + contarOcorrencias(no.direita, valor);
    }
    private NoAVL obterMinimo(NoAVL no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
        public void remover(int valor) {
            raiz = remover(raiz, valor);
        }

        private NoAVL remover(NoAVL no, int valor) {
            if (no == null)
                return null;

            if (valor < no.valor)
                no.esquerda = remover(no.esquerda, valor);
            else if (valor > no.valor)
                no.direita = remover(no.direita, valor);
            else {
                if ((no.esquerda == null) || (no.direita == null)) {
                    NoAVL temp = null;
                    if (temp == no.esquerda)
                        temp = no.direita;
                    else
                        temp = no.esquerda;

                    if (temp == null) {
                        temp = no;
                        no = null;
                    } else
                        no = temp;
                } else {
                    NoAVL temp = obterMinimo(no.direita);
                    no.valor = temp.valor;
                    no.direita = remover(no.direita, temp.valor);
                }
            }

            if (no == null)
                return null;

            no.altura = maximo(altura(no.esquerda), altura(no.direita)) + 1;
            int balanceamento = obterBalanceamento(no);

            // Casos de desequilíbrio
            // Esquerda-Esquerda
            if (balanceamento > 1 && obterBalanceamento(no.esquerda) >= 0)
                return rotacaoDireita(no);

            // Direita-Direita
            if (balanceamento < -1 && obterBalanceamento(no.direita) <= 0)
                return rotacaoEsquerda(no);

            // Esquerda-Direita
            if (balanceamento > 1 && obterBalanceamento(no.esquerda) < 0) {
                no.esquerda = rotacaoEsquerda(no.esquerda);
                return rotacaoDireita(no);
            }

            // Direita-Esquerda
            if (balanceamento < -1 && obterBalanceamento(no.direita) > 0) {
                no.direita = rotacaoDireita(no.direita);
                return rotacaoEsquerda(no);
            }

            return no;
        }

    // Método para sortear números e aplicar operações na árvore
    void sortearEOperar(int quantidade) {
        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            int numeroSorteado = random.nextInt(19999) - 9999;

            if (numeroSorteado % 3 == 0) {
                inserir(numeroSorteado);
                System.out.println(numeroSorteado + "inserido");
            } else if (numeroSorteado % 5 == 0) {
                System.out.println(numeroSorteado + " removido");
                remover(numeroSorteado);
            } else {
                int ocorrencias = contarOcorrencias(raiz, numeroSorteado);
                System.out.println(numeroSorteado + " aparece na árvore " + ocorrencias + " vezes.");
            }
        }
    }
}

