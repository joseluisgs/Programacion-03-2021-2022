import mosca.*

/**
 * Mosca en la Matriz
 */
fun main(args: Array<String>) {
    println("Mosca Matriz")

    var numCasillas = 10
    val MOSCA = 1

    var numIntentos = getNumeroIntentos()

    // Pedir tamaño de Matriz
    numCasillas = getNumeroCasillas();

    // Esto es quevalente a int [][], una matriz es una rray de filas, y cada filas es un array de enteros (son las columnas)
    val casillas = Array(numCasillas) { IntArray(numCasillas) }

    // Todas las casillas son 0 menos la de la mosca

    // Todas las casillas son 0 menos la de la mosca
    iniciarCasillas(casillas)

    // Situar la mosca en el vector

    // Situar la mosca en el vector
    situarMosca(casillas, MOSCA)
    imprimirCasillas(casillas)

    var estaMuerta = false
    var posMosca: IntArray

    println("Atrapa a la Mosca")

    do {
        // Pedirle la posición donde vas a dar un tortazo
        posMosca = posicionGolpear(casillas);

        // Anlizamos el Golpeo
        estaMuerta = analizarGolpeo(MOSCA, casillas, estaMuerta, posMosca);
        numIntentos --;
        println("Te quedan $numIntentos intentos");
    } while(!estaMuerta && numIntentos>0);

    if (estaMuerta)
        println("¡Has cazado a la maldita mosca!");
    else
        println("¡Has perdido!");

    posMosca = buscarMosca(MOSCA, casillas)
    if(posMosca[0]!=-1)
        println("La mosca está en la posición: {${posMosca[0]+1},${posMosca[1]+1}}")
    imprimirCasillas(casillas);

}

