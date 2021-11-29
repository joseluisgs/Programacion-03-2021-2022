package es.joseluisgs.dam;

public class Main {

    public static void main(String[] args) {
        // Variables
        // Mejoras, solo un empate, que el empate sea un punto para los dos, etc..
        int numeroVictoriasRondasJugador1 = 0;
        int numeroEmpatesRondasJugador1 = 0;
        int numeroDerrotasRondasJugador1 = 0;
        int numeroVictoriasRondasJugador2 = 0;
        int numeroEmpatesRondasJugador2 = 0;
        int numeroDerrotasRondasJugador2 = 0;

        final int NUMERO_RONDAS = 3;
        final int NUMERO_TIRADAS = 5;

        final int PROBABILIDAD_JUGADOR1 = 50;
        final int PROBABILIDAD_JUGADOR2 = 33;

        final String JUGADOR1 = "Llul";
        final String JUGADOR2 = "Gasol";


        System.out.println("Bienvenidos al pique entre " + JUGADOR1 + " y " + JUGADOR2);

        // Lo que sea hay que jugarselo al mejor de 3
        for (int i = 1; i <= NUMERO_RONDAS; i++) {

            System.out.println("Ronda: " + i);
            int rondaJugador1 = obtenerResultadoRonda(JUGADOR1, NUMERO_TIRADAS, PROBABILIDAD_JUGADOR1);
            int rondaJugador2 = obtenerResultadoRonda(JUGADOR2, NUMERO_TIRADAS, PROBABILIDAD_JUGADOR2);

            System.out.println("Puntuación Ronda " + i + " de " + JUGADOR1 + ": " + rondaJugador1);
            System.out.println("Puntuación Ronda " + i + " de " + JUGADOR2 + ": " + rondaJugador2);

            // Informe de resultados
            if (rondaJugador1 == rondaJugador2) {
                numeroEmpatesRondasJugador1++;
                numeroEmpatesRondasJugador2++;
                System.out.println(JUGADOR1 + " y " + JUGADOR2 + " han empatado la Ronda: " + i + " con: " + rondaJugador1);
            } else if (rondaJugador1 > rondaJugador2) {
                numeroVictoriasRondasJugador1++;
                numeroDerrotasRondasJugador2++;
                System.out.println(JUGADOR1 + " ha ganado a " + JUGADOR2 + " en la Ronda: " + i + " con: " + rondaJugador1);
            } else {
                numeroVictoriasRondasJugador2++;
                numeroDerrotasRondasJugador1++;
                System.out.println(JUGADOR2 + " ha ganado a " + JUGADOR1 + " en la Ronda: " + i + " con: " + rondaJugador2);
            }
        }

        // Ahora vamos con los resultados de la partida
        if (numeroVictoriasRondasJugador1 > numeroVictoriasRondasJugador2) {
            System.out.println(JUGADOR1 + " ha ganado a " + JUGADOR2 + " en la partida por "
                    + numeroVictoriasRondasJugador1 + " a " + numeroVictoriasRondasJugador2 + " y empates: " + numeroEmpatesRondasJugador1);
        } else if (numeroVictoriasRondasJugador2 > numeroVictoriasRondasJugador1)
            System.out.println(JUGADOR2 + " ha ganado a " + JUGADOR1 + " en la partida por "
                    + numeroVictoriasRondasJugador2 + " a " + numeroVictoriasRondasJugador1 + " y empates: " + numeroEmpatesRondasJugador1);
        else
            System.out.println(JUGADOR2 + " y " + JUGADOR1 + " han empatado todo con: " + numeroEmpatesRondasJugador2);
    }

    /**
     * Realiza la ronda de tiradas de un jugador
     *
     * @param jugador             Nombre del jugador
     * @param numeroTiradas       Número de tiradas del jugador
     * @param probabilidadAcierto de acierto del jugador
     * @return numero de puntos
     */
    private static int obtenerResultadoRonda(String jugador, int numeroTiradas, int probabilidadAcierto) {
        // Se tira 5 balones, pero el ultimo vale doble
        int puntosRonda = 0;
        for (int i = 1; i <= numeroTiradas; i++) {
            System.out.println("Tirada de " + jugador + ": " + i);
            if (realizarSorteo(probabilidadAcierto) && i < numeroTiradas) {
                System.out.println(jugador + " Acierta la tirada: " + i);
                puntosRonda++;
            } else if (realizarSorteo(probabilidadAcierto) && i == probabilidadAcierto) {
                System.out.println(jugador + " Acierta la tirada y vale doble: " + i);
                puntosRonda += 2;
            } else {
                System.out.println(jugador + " Falla la tirada: " + i);
            }

        }
        System.out.println("Puntos de " + jugador + " en esta Ronda: " + puntosRonda);
        return puntosRonda;
    }

    /**
     * Calcula un sorteo de un número en base al limite de probabilidad
     *
     * @param probabilidadAcierto límite de probabilidad, por ejemplo 33%
     * @return Verdadero si el valor está dentro del límite
     */
    private static boolean realizarSorteo(int probabilidadAcierto) {
        boolean salida = false;
        double sorteo = Math.random() * 100;
        if (sorteo < probabilidadAcierto)
            salida = true;
        return salida;
    }
}
