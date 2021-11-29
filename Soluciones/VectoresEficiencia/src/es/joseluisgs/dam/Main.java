package es.joseluisgs.dam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    // Variables globales. Solo en este caso es necesario
    private static int MAX_SIZE = 50000; // Usaremos este para no tardar mucho
    private static int MAX_STEP = 1000; // lo que vamos incrementando
    private static int MAX_ITER = 3;// numero de intentos que tomaremos para calcular la mediaAritmetica

    private static int SEARCH_VALUE = (int) (Math.random() * MAX_SIZE); // Número a Buscar

    private static float[][] tMedio = new float[MAX_SIZE / MAX_STEP][2]; // Almacenamos la cantidad y el tiempo medio
    private static long[] tiempos = new long[MAX_ITER];

    private static long tiempoInicial, tiempoFinal, tiempoTotal; // Tiempos para cornometro
    private static int timeIndex = 0; // tiempo a almacenar

    public static void main(String[] args) {
        testBurbuja();
        testSeleccion();
        testInserccion();
        testShell();
        testQuicksort();

        // Debemos subir el número de elementos....
        testBusquedaLineal();
        testBusquedaBinaria();
    }


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
        timeIndex = 0;
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



    /**
     * Crea un Vector con números aleatorios
     * @param tam
     * @return
     */
    public static int[] crearVector(int tam) {
        int[] v = new int[tam];
        // Creo el vector con números aleatorios hasta tam
        for (int i = 0; i< v.length;i++){
            v[i] = (int) (Math.random()*tam);
        }
        // Para la busqueda voy a meter un valor propio para las búsquedas, siempre al final
        v[(int)(Math.random()*tam)] = SEARCH_VALUE;
        return v;
    }

    /**
     * Calcula la media aritmética de un array de longs
     * @param v
     * @return
     */
    public static float mediaAritmetica (long v[]) {
        long sum = 0; // variable que almacena las suma parcial.
        for(int i=0; i<v.length;i++){
            sum = sum + v[i];
        }
        return (sum/v.length);

    }

    /**
     * Realiza la prueba de Ordenación de Burbuja
     */
    private static void testBurbuja() {
        System.out.println("ORDENACION POR BURBUJA - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/ord_burbuja.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime();; // Inicio de cronómetro

                    burbuja(v); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }
            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ORDENACION POR BURBUJA - FIN");
    }

    /**
     * Realiza la prueba de Ordenación de Selección
     */
    private static void testSeleccion() {
        System.out.println("ORDENACION POR SELECCION - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/ord_seleccion.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    seleccion(v); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ORDENACION POR SELECCION - FIN");
    }


    /**
     * Realiza el test de insercción
     */
    private static void testInserccion() {
        System.out.println("ORDENACION POR INSERCION - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/ord_inserccion.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    insercion(v); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ORDENACION POR INSERCION - FIN");
    }

    /**
     * Realiza el test de shell
     */
    private static void testShell() {
        System.out.println("ORDENACION POR SHELL - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/ord_shell.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    shell(v); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ORDENACION POR SHELL - FIN");
    }

    /**
     * Realiza el test de quicksort
     */
    private static void testQuicksort() {
        System.out.println("ORDENACION POR QUICKSORT - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/ord_quicksort.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    quicksort(v, 0, v.length-1); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ORDENACION POR QUICKSORT - FIN");
    }

    /**
     * Realiza el test de Busqueda lineal
     */
    private static void testBusquedaLineal() {
        System.out.println("BUSQUEDA LINEAL - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/bus_secuencial.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro
                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    int pos = busquedaSecuencial(v, SEARCH_VALUE); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //System.out.println(pos);

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BUSQUEDA LINEAL - FIN");
    }

    /**
     * Realiza el test de Busqueda lineal
     */
    private static void testBusquedaBinaria() {
        System.out.println("BUSQUEDA BINARIA - INICIO");
        timeIndex = 0;
        // Para sacarlo por ficheros
        try {
            FileWriter fstream = new FileWriter("data/bus_binaria.csv");
            PrintWriter out = new PrintWriter(fstream);
            out.println("TAMAÑO;TIEMPO(NS)");

            // Desde 100 al maximo, en inrementos de paso
            for (int tam = 1000; tam <= MAX_SIZE; tam = tam + MAX_STEP) {
                // Repetimos i veces, siendo veces el nº de intentos para calcular la mediaAritmetica
                for (int i = 0; i < MAX_ITER; i++) {

                    // Creamos el vector
                    int[] v = crearVector(tam);

                    //Codigo a medir con el cornometro

                    // Debemos ordenar el vector
                    quicksort(v, 0, v.length-1);

                    tiempoInicial = System.nanoTime(); // Inicio de cronómetro

                    int pos = busquedaBinaria(v, SEARCH_VALUE, 0, v.length-1); // Codigo a ejecutar

                    tiempoFinal = System.nanoTime(); // Fin de cronómetro

                    //System.out.println(pos);

                    //Fin medicion
                    tiempoTotal = tiempoFinal - tiempoInicial; // Tiempo total
                    tiempos[i] = tiempoTotal; // Almaceno en la posición

                }
                tMedio[timeIndex][0] = tam; // Almaceno el tamañp
                tMedio[timeIndex][1] = mediaAritmetica(tiempos); // y el tiempo medio

                // Sacamos por pantalla: tamaño del vector - tiempo medio
                System.out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));
                out.println(((int) tMedio[timeIndex][0]) + ";" + ((int) tMedio[timeIndex][1]));

                timeIndex++; // Siguiente
            }

            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BUSQUEDA BINARIA - FIN");
    }

}
