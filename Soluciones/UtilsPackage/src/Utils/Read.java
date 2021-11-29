package Utils;

import java.util.Scanner;

public class Read {
    public static int typeInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int num = 0;
        do {
            try {
                System.out.println(mensaje);
                num = sc.nextInt();
                ok = true;
            } catch (Exception ex) {
                System.err.println("Error: no has introducido un número entero");
                System.out.println("Introduzca un entero");
                ok = false;
                sc.next();
            }
        } while(!ok);
        return num;
    }

    public static float typeFloat(String mensaje) {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        float num = 0;
        do {
            try {
                System.out.println(mensaje);
                num = sc.nextFloat();
                ok = true;
            } catch (Exception ex) {
                System.err.println("Error: no has introducido un número entero");
                System.out.println("Introduzca un entero");
                ok = false;
                sc.next();
            }
        } while(!ok);
        return num;
    }
}
