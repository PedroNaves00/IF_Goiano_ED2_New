import java.util.Random;

public class ArvoreRN {
    private NoRN raiz;
    private final NoRN sentinela;

    private static final boolean PRETO = false;
    private static final boolean VERMELHO = true;

    // Definição do nó da árvore Rubro-Negra
    private static class NoRN {
        int valor;
        boolean cor;
        NoRN esquerda, direita, pai;

        public NoRN(int valor, boolean cor) {
            this.valor = valor;
            this.cor = cor;
        }
    }

    public ArvoreRN() {
        sentinela = new NoRN(0, PRETO);
        sentinela.esquerda = sentinela.direita = sentinela.pai = sentinela;
        raiz = sentinela;
    }

    // Método de inserção
    public void inserir(int valor) {
        NoRN no = new NoRN(valor, VERMELHO);
        inserir(no);
        ajustarInsercao(no);
    }

    private void inserir(NoRN z) {
        NoRN y = sentinela;
        NoRN x = raiz;

        while (x != sentinela) {
            y = x;
            if (z.valor < x.valor) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        z.pai = y;

        if (y == sentinela) {
            raiz = z;
        } else if (z.valor < y.valor) {
            y.esquerda = z;
        } else {
            y.direita = z;
        }

        z.esquerda = sentinela;
        z.direita = sentinela;
        z.cor = VERMELHO;
    }

    private void ajustarInsercao(NoRN z) {
        while (z.pai.cor == VERMELHO) {
            if (z.pai == z.pai.pai.esquerda) {
                NoRN tio = z.pai.pai.direita;

                if (tio.cor == VERMELHO) {
                    z.pai.cor = PRETO;
                    tio.cor = PRETO;
                    z.pai.pai.cor = VERMELHO;
                    z = z.pai.pai;
                } else {
                    if (z == z.pai.direita) {
                        z = z.pai;
                        rotacaoEsquerda(z);
                    }

                    z.pai.cor = PRETO;
                    z.pai.pai.cor = VERMELHO;
                    rotacaoDireita(z.pai.pai);
                }
            } else {
                NoRN tio = z.pai.pai.esquerda;

                if (tio.cor == VERMELHO) {
                    z.pai.cor = PRETO;
                    tio.cor = PRETO;
                    z.pai.pai.cor = VERMELHO;
                    z = z.pai.pai;
                } else {
                    if (z == z.pai.esquerda) {
                        z = z.pai;
                        rotacaoDireita(z);
                    }

                    z.pai.cor = PRETO;
                    z.pai.pai.cor = VERMELHO;
                    rotacaoEsquerda(z.pai.pai);
                }
            }
        }

        raiz.cor = PRETO;
    }

    // Método de rotação à esquerda
    private void rotacaoEsquerda(NoRN x) {
        NoRN y = x.direita;
        x.direita = y.esquerda;

        if (y.esquerda != sentinela) {
            y.esquerda.pai = x;
        }

        y.pai = x.pai;

        if (x.pai == sentinela) {
            raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }

        y.esquerda = x;
        x.pai = y;
    }

    // Método de rotação à direita
    private void rotacaoDireita(NoRN y) {
        NoRN x = y.esquerda;
        y.esquerda = x.direita;

        if (x.direita != sentinela) {
            x.direita.pai = y;
        }

        x.pai = y.pai;

        if (y.pai == sentinela) {
            raiz = x;
        } else if (y == y.pai.esquerda) {
            y.pai.esquerda = x;
        } else {
            y.pai.direita = x;
        }

        x.direita = y;
        y.pai = x;
    }

    public void remover(int valor) {
        NoRN z = buscarNo(valor);
        if (z != sentinela) {
            remover(z);
        }
    }

    public void remover(NoRN z) {
        NoRN y = z;
        NoRN x;
        boolean corOriginalY = y.cor;

        if (z.esquerda == sentinela) {
            x = z.direita;
            transplantar(z, z.direita);
        } else if (z.direita == sentinela) {
            x = z.esquerda;
            transplantar(z, z.esquerda);
        } else {
            y = obterMinimo(z.direita);
            corOriginalY = y.cor;
            x = y.direita;

            if (y.pai == z) {
                x.pai = y;
            } else {
                transplantar(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            transplantar(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }

        if (corOriginalY == PRETO) {
            ajustarRemocao(x);
        }
    }

    // Método para ajustar a árvore após a remoção
    private void ajustarRemocao(NoRN x) {
        while (x != raiz && x.cor == PRETO) {
            if (x == x.pai.esquerda) {
                NoRN irmao = x.pai.direita;

                if (irmao.cor == VERMELHO) {
                    irmao.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacaoEsquerda(x.pai);
                    irmao = x.pai.direita;
                }

                if (irmao.esquerda.cor == PRETO && irmao.direita.cor == PRETO) {
                    irmao.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (irmao.direita.cor == PRETO) {
                        irmao.esquerda.cor = PRETO;
                        irmao.cor = VERMELHO;
                        rotacaoDireita(irmao);
                        irmao = x.pai.direita;
                    }

                    irmao.cor = x.pai.cor;
                    x.pai.cor = PRETO;
                    irmao.direita.cor = PRETO;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                NoRN irmao = x.pai.esquerda;

                if (irmao.cor == VERMELHO) {
                    irmao.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacaoDireita(x.pai);
                    irmao = x.pai.esquerda;
                }

                if (irmao.direita.cor == PRETO && irmao.esquerda.cor == PRETO) {
                    irmao.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (irmao.esquerda.cor == PRETO) {
                        irmao.direita.cor = PRETO;
                        irmao.cor = VERMELHO;
                        rotacaoEsquerda(irmao);
                        irmao = x.pai.esquerda;
                    }

                    irmao.cor = x.pai.cor;
                    x.pai.cor = PRETO;
                    irmao.esquerda.cor = PRETO;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }

        x.cor = PRETO;
    }

    // Método auxiliar para buscar um nó na árvore
    private NoRN buscarNo(int valor) {
        return buscarNo(raiz, valor);
    }

    private NoRN buscarNo(NoRN no, int valor) {
        while (no != sentinela && valor != no.valor) {
            if (valor < no.valor) {
                no = no.esquerda;
            } else {
                no = no.direita;
            }
        }

        return no;
    }

    // Método auxiliar para obter o nó mínimo a partir de um nó dado
    private NoRN obterMinimo(NoRN no) {
        while (no.esquerda != sentinela) {
            no = no.esquerda;
        }
        return no;
    }

    public int contarOcorrencias(int valor) {
        return contarOcorrencias(raiz, valor);
    }

    private int contarOcorrencias(NoRN no, int valor) {
        if (no == sentinela) {
            return 0;
        }

        int resultado = 0;

        if (valor < no.valor) {
            resultado += contarOcorrencias(no.esquerda, valor);
        } else if (valor > no.valor) {
            resultado += contarOcorrencias(no.direita, valor);
        } else {
            resultado = 1;
        }

        return resultado;
    }

    // Método auxiliar para substituir um subárvore por outra
    private void transplantar(NoRN u, NoRN v) {
        if (u.pai == sentinela) {
            raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }

        v.pai = u.pai;
    }

    public void sortearEoperar(int qtd) {
        Random random = new Random();

        // Sorteio aleatório de 50.000 números
        for (int i = 0; i < qtd; i++) {
            int numeroSorteado = random.nextInt(19999) - 9999;

            if (numeroSorteado % 3 == 0) {
                inserir(numeroSorteado);
                System.out.println(numeroSorteado + " inserido na árvore.");
            } else if (numeroSorteado % 5 == 0) {
                remover(numeroSorteado);
                System.out.println(numeroSorteado + " removido da árvore.");
            } else {
                int ocorrencias = contarOcorrencias(numeroSorteado);
                System.out.println(numeroSorteado + " aparece na árvore " + ocorrencias + " vezes.");
            }
        }

    }

}