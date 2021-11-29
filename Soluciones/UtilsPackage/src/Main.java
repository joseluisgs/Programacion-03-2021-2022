import Utils.Probabilidad;
import Utils.Read;
import Utils.Vector;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        System.out.println("HOLA");
        float n1 = Read.typeFloat("Dime el dividendo");
        float n2 = Read.typeFloat("Dime el divisor");

        if(n2!=0) {
            System.out.println((n1/n2));
            System.out.println(Probabilidad.sorteo(10));
        } else {
            System.err.println("No se puede dividor por 0");
        }

        int[] v = {1,2,4,5,6,7,11,89,45,34};
        Vector.quicksort(v,0, v.length-1);
    }


}
