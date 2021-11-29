package es.joseluisg.dam;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        boolean esMayorEdad;
        int edad;

        edad = getEdad();

        esMayorEdad = isEsMayorEdad(edad);

        printMayoriaEdad(esMayorEdad);

    }

    private static void printMayoriaEdad(boolean esMayorEdad) {
        String mensaje = (esMayorEdad) ? "Eres mayor de edad" : "Eres menor de Edad";
        System.out.println(mensaje);
    }

    private static boolean isEsMayorEdad(int edad) {
        return  edad >= 18;
    }

    private static int getEdad() {
        int edad;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la edad que tienes: ");
        edad = sc.nextInt();
        return edad;
    }
}
