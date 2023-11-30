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
}