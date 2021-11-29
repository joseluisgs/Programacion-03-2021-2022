package es.joseluisg.dam;

public class Main {

    public static void main(String[] args) {
	    int[][] res = new int[10][11];
        // Recorro las filas
        for (int i = 0; i < res.length; i++) {
           // recorro las columnas
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = (i+1) * j;
            }
        }

        // Muestro los resultados
        for (int i = 0; i < res.length; i++) {
            System.out.println("Tabla Multiplicar de: " + (i+1));
            for (int j = 0; j < res[i].length; j++) {
                System.out.println("\t" + (i+1) + " x "
                        + j + " = " +res[i][j]);
            }
            System.out.println();
        }

    }
}
