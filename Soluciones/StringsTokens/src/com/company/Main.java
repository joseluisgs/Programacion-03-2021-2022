package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        String cad = "   Caca de la Vaca de Leganes   ";
        cad = cad.trim();
        System.out.println(cad);

        imprimirPorToken(cad, ' ');

        String cad2 = "Pepito. de los palotes. Estudia DAM.";
        imprimirPorToken(cad2, '.');
    }

    private static void imprimirPorToken(String cad, char token) {
        int index = 0;
        int indexEnd = 0;
        String subCad;
        do {
            indexEnd = cad.indexOf(token, index);
            if(indexEnd!=-1) {
                subCad = cad.substring(index, indexEnd+1);
                System.out.println(subCad.trim());
                index = indexEnd + 1;
            } else {
                subCad = cad.substring(index, cad.length());
                System.out.println(subCad.trim());
                index = cad.length();
            }
        } while (index < cad.length());
    }


}
