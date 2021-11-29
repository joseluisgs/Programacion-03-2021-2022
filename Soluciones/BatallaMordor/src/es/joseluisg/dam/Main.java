package es.joseluisg.dam;

public class Main {

    public static void main(String[] args)  {
        final int NUM_SOLDADOS = 2_000;
        final int SOLDADOS_COTIGENCIA = 20;
        int almacen = 39_000;
        int LIMITE_ALMACEN = 3_900;
        final int TIEMPO_MAXIMO = 7_200;
        final int TIEMPO_CHECK = 200;
        final int TOTAL_CONTINGENCIAS = NUM_SOLDADOS/SOLDADOS_COTIGENCIA;
        int contigenciasVivas = NUM_SOLDADOS/SOLDADOS_COTIGENCIA;
        int[] contigencias = new int[contigenciasVivas];
        boolean isUrukHai = false;
        int URUK_PROBABILIDAD = 20;
        int TIEMPO_ATAQUE = 50;

        int temporizador = 0;
        System.out.println("Batalla de Mordor");

        initContengicas(contigencias);
        printContigencias(contigencias);

        do {
            // Al final
            // temporizador ++; // No lo necesito porque ya espero una oleada
            if(temporizador % TIEMPO_CHECK == 0) {
                almacen = abastecerTropas(almacen, contigencias);
            }
            // Simulando batalla
            // ¿Que tropa esta siendo atacada?
            contigenciasVivas = ataqueEnemigo(contigenciasVivas, contigencias, URUK_PROBABILIDAD);

            // Espero otra oleada, esto opcional, comentar si no se usa
            esperar(TIEMPO_ATAQUE);
            temporizador += TIEMPO_ATAQUE;
        } while (temporizador < TIEMPO_MAXIMO && almacen > LIMITE_ALMACEN);

        printReport(almacen, LIMITE_ALMACEN, contigenciasVivas, TOTAL_CONTINGENCIAS, contigencias);

    }



    private static int abastecerTropas(int almacen, int[] contigencias) {
        System.out.println(" --> Merry y Pippin Comprueban estado de contiengecias");
        for (int i = 0; i < contigencias.length; i++) {
            if (contigencias[i] != 400 && contigencias[i] != -1) {
                almacen = restablcerContingencia(contigencias, i, almacen);
            }
        }
        return almacen;
    }

    private static void printReport(int almacen, int limite_almacen, int contigenciasVivas, int total_contingencias, int[] contigencias) {
        if(almacen< limite_almacen) {
            System.out.println("Has perdido la batalla, te has quedado sin suministros");
        } else {
            System.out.println("Se ha terminado el programa porque se ha alcanzado el tiempo máximo");
        }
        System.out.println("Almacen: " + almacen);
        System.out.println("Contingencias vivas: " + contigenciasVivas);
        System.out.println("Contingencias muertas: " + (total_contingencias - contigenciasVivas));
        System.out.println("Contingencias: ");
        printContigencias(contigencias);
    }

    private static int ataqueEnemigo(int contigenciasVivas, int[] contigencias, int URUK_PROBABILIDAD) {
        boolean isUrukHai;
        int numContingencia = alerta(contigenciasVivas, contigencias);
        int enemigos = tropasEnemigas();
        isUrukHai = isUruk(URUK_PROBABILIDAD);
        System.out.println("Contiencia: " + numContingencia + " esta siendo atacada");
        System.out.println("Tropas enemigas que nos atacan: " + enemigos + " y tienen UrukHai: " + isUrukHai);
        boolean exito = true;

        if(contigencias[numContingencia] > enemigos) {
            contigencias[numContingencia] -= enemigos;
            if(isUrukHai){
                exito = sorteo(75);
            } else {
                exito = sorteo(85);
            }
        } else if(contigencias[numContingencia] < enemigos && enemigos < 10) {
            contigencias[numContingencia] -= enemigos;
            if(isUrukHai){
                exito = sorteo(35);
            } else {
                exito = sorteo(45);
            }
        } else if(contigencias[numContingencia] < enemigos && enemigos >= 10) {
            contigencias[numContingencia] -= enemigos;
            exito = false;
        }

        // Ha caido la contingencia
        if(!exito) {
            contigenciasVivas--;
            contigencias[numContingencia] = -1;
        }
        return contigenciasVivas;
    }

    private static void initContengicas(int[] contigencias) {
        for (int i = 0; i < contigencias.length; i++) {
            contigencias[i] = 400;
        }
    }

    private static void printContigencias(int[] contigencia) {
        System.out.print("contingencia: { ");
        for (int j : contigencia) {
            System.out.print(j + " ");
        }
        System.out.println("}");

    }

    private static int restablcerContingencia(int[] contigencia, int numContingencia, int ALMACEN) {
        ALMACEN -= (400 - contigencia[numContingencia]);
        contigencia[numContingencia] = 400;
        return ALMACEN;
    }

    private static boolean sorteo(int probabilidad) {
        return (int)(Math.random() * 100) < probabilidad;
    }

    private static int alerta(int totalContingencias, int[] contigencias){
        int contingencia = 0;
        do {
            contingencia =(int) (Math.random() * totalContingencias);
            if (estadoContingencia(contigencias, contingencia) == -1) {
                contingencia = -1;
            }
        } while(contingencia==-1);
        return contingencia;
    }

    private static int estadoContingencia(int[] contigencias, int numContingencia) {
        return contigencias[numContingencia];
    }

    private static int tropasEnemigas() {
        return ((int)(Math.random() * 15)+1);
    }

    private static boolean isUruk(int probabilidad) {
        return (int)(Math.random() * 100) < probabilidad;
    }

    private static void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
           System.err.println("Error en temporizador");
        }
    }



}
