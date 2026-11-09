fun main() {
    println("====================================")
    println("ETIQUETAS EN KOTLIN")
    println("====================================")
    println("Una etiqueta se escribe nombre@ antes de una expresión.")

    ejemploBreakEtiquetado()
    ejemploBusquedaConBreakEtiquetado()
    ejemploBusquedaConBreakNormal()
    ejemploContinueEtiquetado()
    ejemploReturnEtiquetado()
    ejemploEtiquetasImplicitas()
    ejemploThisEtiquetado()
    ejemploReturnNoLocal()
}

private fun ejemploBreakEtiquetado() {
    println("\n1. break@etiqueta")
    println("Sale del bucle indicado, incluso si hay bucles anidados:")

    exterior@ for (fila in 1..3) {
        for (columna in 1..3) {
            if (fila == 2 && columna == 2) {
                println("Se encontró (2, 2); break@exterior termina los dos bucles.")
                break@exterior
            }
            println("fila=$fila, columna=$columna")
        }
    }
}

private fun ejemploBusquedaConBreakEtiquetado() {
    println("\nEjemplo: buscar el primer 7 con break@buscar")
    println("El bucle exterior recorre las filas y el interior recorre cada columna.")
    println("i y j son índices que empiezan en 0: i es la fila y j es la columna.")

    val matriz = listOf(
        listOf(1, 2, 3),
        listOf(4, 7, 6),
        listOf(7, 8, 9)
    )

    buscar@ for ((i, fila) in matriz.withIndex()) {
        for ((j, columna) in fila.withIndex()) {
            if (columna == 7) {
                println("Encontrado un 7 en fila=$i, columna=$j")
                break@buscar
            }
            println("Revisando ($i,$j) = $columna")
        }
    }

    println("Fin de la búsqueda: break@buscar terminó ambos bucles al encontrar el primer 7.")
}

private fun ejemploBusquedaConBreakNormal() {
    println("\nComparación: buscar los 7 con break normal")
    println("break sin etiqueta solo termina el bucle interno; el externo sigue con la fila siguiente.")

    val matriz = listOf(
        listOf(1, 2, 3),
        listOf(4, 7, 6),
        listOf(7, 8, 9)
    )

    for ((i, fila) in matriz.withIndex()) {
        for ((j, columna) in fila.withIndex()) {
            if (columna == 7) {
                println("Encontrado un 7 en fila=$i, columna=$j")
                break
            }
        }
    }

    println("Fin de la búsqueda: break normal permitió encontrar ambos 7.")
}

private fun ejemploContinueEtiquetado() {
    println("\n2. continue@etiqueta")
    println("Salta al siguiente ciclo del bucle indicado:")

    fila@ for (numeroFila in 1..3) {
        for (numeroColumna in 1..3) {
            if (numeroColumna == 2) {
                println("Desde columna 2, continue@fila pasa a la siguiente fila.")
                continue@fila
            }
            println("fila=$numeroFila, columna=$numeroColumna")
        }
    }
}

private fun ejemploReturnEtiquetado() {
    println("\n3. return@etiqueta")
    println("Termina la lambda actual, pero el forEach continúa con el siguiente elemento:")

    listOf(1, 2, 3, 4).forEach elemento@{ numero ->
        if (numero == 3) {
            println("Se omite el resto de la lambda para el 3.")
            return@elemento
        }
        println("Procesando $numero")
    }
    println("La función sigue ejecutándose después del forEach.")
}

private fun ejemploEtiquetasImplicitas() {
    println("\n4. Etiquetas implícitas de lambdas")
    println("Una lambda puede usar el nombre de la función que la recibe, como return@forEach:")

    listOf("Kotlin", "", "etiquetas").forEach { texto ->
        if (texto.isEmpty()) return@forEach
        println("Texto: $texto")
    }
}

private fun ejemploThisEtiquetado() {
    println("\n5. this@etiqueta")
    println("Permite nombrar explícitamente el receptor de una lambda:")

    val mensaje = with(StringBuilder()) contexto@{
        append("Receptor implícito; ")
        this@contexto.append("receptor nombrado con this@contexto.")
        toString()
    }
    println(mensaje)
}

private fun ejemploReturnNoLocal() {
    println("\n6. return sin etiqueta dentro de una lambda inline")
    println("A diferencia de return@forEach, este return sale de la función que contiene el forEach:")
    println(buscarSegundo())
    println("Este mensaje aparece porque buscarSegundo ya devolvió el control a main.")
}

private fun buscarSegundo(): String {
    listOf(1, 2, 3).forEach { numero ->
        if (numero == 2) return "return salió de buscarSegundo al encontrar $numero."
    }
    return "No se encontró el número buscado."
}
