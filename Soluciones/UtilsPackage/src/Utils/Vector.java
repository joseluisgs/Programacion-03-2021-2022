package Utils;

public class Vector {
    /**
     * Algoritmo de ordenación Burbuja: O(n2)
     * @param V
     */
    public static void burbuja(int[] V){
        int i, j;
        int aux;
        for(i=0;i<V.length-1;i++)
            for(j=0;j<V.length-i-1;j++)
                if(V[j+1]<V[j]){
                    aux=V[j+1];
                    V[j+1]=V[j];
                    V[j]=aux;
                }
    }

    /**
     * Algoritmo de ordenación Selección: O(n2)
     * @param V
     */
    public static void seleccion(int[] V) {
        int i, j, menor, pos, tmp;
        for (i = 0; i < V.length - 1; i++) {
            menor = V[i];
            pos = i;
            for (j = i + 1; j < V.length; j++){
                if (V[j] < menor) {
                    menor = V[j];
                    pos = j;
                }
            }
            if (pos != i){
                tmp = V[i];
                V[i] = V[pos];
                V[pos] = tmp;
            }
        }
    }

    /**
     * Algoritmo de ordenación insercción Mejor caso O(n) y cuanto más desordenada, más se acerca a O(n2).
     * @param V
     */
    public static void insercion(int[] V){
        int p, j;
        int aux;
        for (p = 1; p < V.length; p++){
            aux = V[p];
            j = p - 1;
            while ((j >= 0) && (aux < V[j])){
                V[j + 1] = V[j];
                j--;
            }
            V[j + 1] = aux;
        }
    }

    /**
     * Algoritmo de ordenación shell sort: tiempo de ejecución promedio es de O(n2/3) para la mayoría de las secuencias de salto.
     * @param V
     */
    public static void shell(int[] V) {
        int salto, aux, i;
        boolean cambios;
        for (salto = V.length / 2; salto != 0; salto /= 2) {
            cambios = true;
            while (cambios) {
                cambios = false;
                for (i = salto; i < V.length; i++)
                {
                    if (V[i - salto] > V[i]) {
                        aux = V[i];
                        V[i] = V[i - salto];
                        V[i - salto] = aux;
                        cambios = true;
                    }
                }
            }
        }
    }

    public static void quicksort(int A[], int izq, int der) {

        int pivote=A[izq];
        int i=izq;
        int j=der;
        int aux;

        // Buscamos el pivote
        while(i < j){
            while(A[i] <= pivote && i < j) i++;
            while(A[j] > pivote) j--;
            if (i < j) {
                aux= A[i];
                A[i]=A[j];
                A[j]=aux;
            }
        }

        // Ya lo tenemsos en el lugar correcto, lo colocamos en el pivote
        A[izq]=A[j];
        A[j]=pivote;

        if(izq < j-1)
            quicksort(A,izq,j-1);          // ordenamos subarray izquierdo
        if(j+1 < der)
            quicksort(A,j+1,der);          // ordenamos subarray derecho

    }

    // secuencial search
    public static int busquedaSecuencial(int[] A, int x){
        int i;
        for(i=0;i<A.length;i++)
            if(A[i]==x)
                return i;
        return -1;
    }

    // busqueda binaria recursiva
    public static int busquedaBinaria(int[] A, int x, int izq, int der){
        int centro;
        if(izq>der)
            return -1;
        centro=(izq+der)/2;
        if(A[centro]==x)
            return centro;
        if(A[centro]>x)
            return busquedaBinaria(A,x,izq,centro-1);
        else
            return busquedaBinaria(A,x,centro+1,der);
    }
}
