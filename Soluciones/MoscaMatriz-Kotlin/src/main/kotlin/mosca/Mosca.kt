package mosca

import java.lang.Math.random


/**
 * Obtiene el número de Intentos
 */
fun getNumeroIntentos(): Int {
    var intentos = 0
    do {
        println("Introduce el número de intentos máximos, mayor a 1")
        // Si no lo puede comveretir da null
        intentos = readLine()?.toIntOrNull() ?: 0
    } while (intentos < 1)
    return intentos
}

fun getNumeroCasillas(): Int {
    var numCasillas: Int
    do {
        println("Dime el número de casillas, siempre mayor a 5")
        numCasillas =readLine()?.toIntOrNull() ?: 0
        println("Tamaño de vector es:$numCasillas")
    } while (numCasillas < 5)
    return numCasillas
}

fun imprimirCasillas(casillas: Array<IntArray>) {
    for (fila in casillas) {
        print("{ ")
        for (columna in fila) {
            print("$columna ")
        }
        println("} ")
    }
}

fun iniciarCasillas(casillas: Array<IntArray>) {
    // i recorre todos los indices
    for (i in casillas.indices) {
        // j recorre todos los elementos de cada indice
        for (j in casillas[i].indices) {
            // Se inicializa el valor de cada casilla a 0
            casillas[i][j] = 0
        }
        //for (j in 0 until casillas[i].size) {
        //    casillas[i][j] = 0
        //}
    }
}

fun getPosicion(casillas: Array<IntArray>): Int {
    var fila: Int
    do {
        fila = (random() * casillas.size).toInt()
        //System.out.println(s + (fila + 1));
    } while (fila < 0 || fila >= casillas.size)
    return fila
}

fun situarMosca(casillas: Array<IntArray>, mosca: Int) {
    val fila: Int = getPosicion(casillas)
    val columna: Int = getPosicion(casillas)
    casillas[fila][columna] = mosca
}

fun posicionGolpear(casillas: Array<IntArray>): IntArray {
    val posMosca = intArrayOf(0, 0)
    do {
        println("Introduce la posición de la Fila a atacar: ")
        posMosca[0] = (readLine()?.toIntOrNull() ?: 0 ) - 1
        println("La posición Fila elegida es: " + (posMosca[0] + 1))
    } while (posMosca[0] < 0 || posMosca[0] >= casillas.size)
    do {
        println("Introduce la posición de la Columna a atacar: ")
        posMosca[1] = (readLine()?.toIntOrNull() ?: 0 ) - 1
        println("La posición Columna elegida es: " + (posMosca[1] + 1))
    } while (posMosca[1] < 0 || posMosca[1] >= casillas.size)
    return posMosca
}

fun analizarGolpeo(MOSCA: Int, casillas: Array<IntArray>, estaMuerta: Boolean, posMosca: IntArray): Boolean {
    // Logica del juego
    // Acertamos
    var estaMuerta = estaMuerta
    if (casillas[posMosca[0]][posMosca[1]] == MOSCA) {
        estaMuerta = true

        // Analizamos los limites
    }
    for (i in -1..1) {
        for (j in -1..1) {
            if (posMosca[0] + i >= 0 && posMosca[0] + i < casillas.size && posMosca[1] + j >= 0 && posMosca[0] + j < casillas.size) {
                if (casillas[posMosca[0] + i][posMosca[1] + j] == MOSCA) {
                    println("¡CASI!")
                    iniciarCasillas(casillas)
                    situarMosca(casillas, MOSCA)
                }
            }
        }
    }
    if (!estaMuerta) {
        println("¡Has fallado!")
    }
    imprimirCasillas(casillas)
    return estaMuerta
}

// Vamos a buscar la mosca
fun buscarMosca(mosca: Int = 1, casillas: Array<IntArray>): IntArray {
    for (i in casillas.indices) {
        for (j in casillas[i].indices) {
            if (casillas[i][j] == mosca) {
                //println("La mosca está en la posición: " + (i + 1) + "," + (j + 1))
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf(-1, -1)
}

