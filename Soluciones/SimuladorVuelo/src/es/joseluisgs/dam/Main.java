package es.joseluisgs.dam;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Voy a poner unas constantes
	    System.out.println("Simulador de Vuelo");
        int velocidadCrucero = 0;
        int altitudCrucero = 0;
        int velocidadActual = 0;
        int altitudActual = 0;
        final int TIEMPO_MAX = 300; // no voy a poner los 5 minutos
        int temporizador = 0;
        boolean hayFalloSistema = false;
        final int TIEMPO_ESPERA = 20;

        // Para leer de teclado
        Scanner scanner = new Scanner(System.in);

        // Pedimos la altitud y la velocidad
        System.out.println("Introduzca la Velocidad de Crucero: ");
        velocidadCrucero = scanner.nextInt();
        System.out.println("Introduzca la Altura de Crucero de Crucero: ");
        altitudCrucero = scanner.nextInt();

        // voy a hacer que al principio la velocidad actual y la
        // altura actual sean las de crucero
        parametrosVuelo[2] = parametrosVuelo[0];
        parametrosVuelo[3] = parametrosVuelo[1];

        System.out.println("Comenzando el vuelo");
        System.out.println("Velocidad Actual: " + parametrosVuelo[2]);
        System.out.println("Altitud Actual: " + parametrosVuelo[3]);
        System.out.println("Velocidad Crucero: " + parametrosVuelo[0]);
        System.out.println("Altitud Crucero: " + parametrosVuelo[1]);

        // Repetimos durante 5 min o que no haya fallo
        do {
            System.out.println("Obteniendo datos");
            // Obtenemos velocidad y la altura
            getDatosActuales(parametrosVuelo);
            System.out.println("Velocidad Actual: " + parametrosVuelo[2]);
            System.out.println("Altitud Actual: " + parametrosVuelo[1]);

            System.out.println("Aplicando correcciones");
            if(parametrosVuelo[2]!=parametrosVuelo[1])
                disminuir('v');
            else if
            // Corregimos la velocidad y altitud, como no puedo usar paso por referencia
            // uso una funcion que pando la variable la sobre escriba
            velocidadActual = correccionVelocidad(velocidadCrucero, velocidadActual);
            altitudActual = correccionAltitud(altitudCrucero, altitudActual);

            System.out.println("Velocidad Actual: " + velocidadActual);
            System.out.println("Altitud Actual: " + altitudActual);

            // hay fallo de sistema
            hayFalloSistema = probabilidad(1);
            // Ahora nos toca aumentar el temporizador y esperar par la siguiente medicion
            temporizador += 10;
            esperar(TIEMPO_ESPERA);
            System.out.println("Tiempo de Vuelo: " + temporizador);
        } while(temporizador<=TIEMPO_MAX && !hayFalloSistema);

        // Si hemos salido es porque o hemos llegado al final o porque hay fallo
        if (hayFalloSistema) {
            System.out.println("Volviendo a control manual porque ha habido un fallo en el sistema");
        } else {
            System.out.println("Volviendo a control manual porque ha terminado el tiempo");
        }
    }

    private static void getDatosActuales(int[] parametrosVuelo) {
        parametrosVuelo[2] = parametrosVuelo[2]+ getVelocidadActual();
        parametrosVuelo[3] = parametrosVuelo[3]+ getAltitudActual();
    }

    private static int correccionVelocidad(int velocidadCrucero, int velocidadActual) {
        if (velocidadActual < velocidadCrucero)
            velocidadActual++;
        else
            velocidadActual--;

        return velocidadActual;
    }

    private static int correccionAltitud(int altitudCrucero, int altitudActual) {
        if (altitudActual < altitudCrucero)
            altitudActual++;
        else
            altitudActual--;

        return altitudActual;
    }


    private static boolean probabilidad(int limite) {
        return Math.random()*100 < limite;
    }

    private static void esperar(int tiempo) throws InterruptedException {
        Thread.sleep(tiempo);
    }

    private static int getVelocidadActual() {
        return (int) (Math.random()*20) - 10;
    }

    private static int getAltitudActual() {
        return (int) (Math.random()*20) - 10;
    }


}
