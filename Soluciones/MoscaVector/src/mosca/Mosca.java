package mosca;

import java.util.Scanner;

public class Mosca {
    public static void main(String[] args){
        System.out.println("La Mosca Vector");
        int numCasillas = 10;
        final int MOSCA = 1;


        // Pedir tamaño de vector
        numCasillas = pedirNumeroCasillas();

        // Crear el vector
        int[] casillas = new int[numCasillas];

        // Situar la mosca en el vector
        situarMosca(casillas, MOSCA);
        imprimirCasillas(casillas);

        boolean estaMuerta = false;
        int posMosca = 0;
        do {
            // Pedirle la posición donde vas a dar un tortazo
            posMosca = posicionGolpear(casillas);
            // Anlizamos el Golpeo
            estaMuerta = analizarGolpeo(MOSCA, casillas, estaMuerta, posMosca);
        } while(!estaMuerta);
        System.out.println("¡¡Has cazado a la maldita mosca!!!");
        System.out.println("Estaba en: " + (posMosca+1));

    }

    private static boolean analizarGolpeo(int MOSCA, int[] casillas, boolean estaMuerta, int posMosca) {
        // Logica del juego
        // Acertamos
        if(casillas[posMosca] == MOSCA) {
            estaMuerta = true;

            // Analizamos los limites
        } else {
            boolean checkIzda = posMosca != 0 && casillas[posMosca -1]== MOSCA;
            boolean checkDcha = posMosca != casillas.length-1 && casillas[posMosca +1]== MOSCA;

            if(checkIzda || checkDcha) {
                // Revoloteamos
                System.out.println("¡CASI!");
                situarMosca(casillas, MOSCA);
            }
        }

        if(!estaMuerta) {
            System.out.println("¡Has fallado!");
        }
        imprimirCasillas(casillas);
        return estaMuerta;
    }

    private static int posicionGolpear(int[] casillas) {
        Scanner sc = new Scanner(System.in);
        int posMosca;
        do {
            System.out.println("Introduce la posición a atacar: ");
            posMosca = sc.nextInt();
            System.out.println("La posición elegida es: " + posMosca);
        } while (posMosca<0 || posMosca>= casillas.length);
        return (posMosca-1);
    }

    private static void situarMosca(int[] casillas, int mosca) {
        int pos = 0;
        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = 0;
        }
        do {
            pos = (int) (Math.random() * casillas.length);
            System.out.println("Posicion de la mosca: " + (pos+1));
        } while (pos<0 || pos>=casillas.length);
        casillas[pos] = mosca;
    }

    /**
     *
     * @return
     */
    private static int pedirNumeroCasillas() {
        Scanner sc = new Scanner(System.in);
        int numCasillas;
        do {
            System.out.println("Dime el número de casillas, siempre mayor a 5");
            numCasillas = sc.nextInt();
            System.out.println("Tamaño de vector es:" + numCasillas);
        } while (numCasillas<5);
        return numCasillas;
    }

    static void imprimirCasillas(int[] casillas) {
        System.out.print("{ ");
        for(int casilla: casillas){
            System.out.print(casilla + " ");
        }
        System.out.println("}");
    }
}
