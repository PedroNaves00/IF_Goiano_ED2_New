public class NoRN {
        int valor;
        boolean cor; // true para vermelho, false para preto
        NoRN esquerda, direita, pai;

        public NoRN(int valor) {
            this.valor = valor;
            this.cor = true; // Todos os nós são inicializados como vermelhos por padrão
        }
}
