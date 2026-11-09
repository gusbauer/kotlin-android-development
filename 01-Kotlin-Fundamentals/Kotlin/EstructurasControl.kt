fun main() {
    var opcion: Int

    do {
        mostrarMenu()
        opcion = leerEntero("Elige una opción: ")

        when (opcion) {
            1 -> ejemploIf()
            2 -> ejemploWhen()
            3 -> ejemploFor()
            4 -> ejemploWhile()
            5 -> ejemploDoWhile()
            6 -> ejemploBreakYContinue()
            7 -> ejemploEtiquetas()
            8 -> ejemploReturn()
            9 -> ejemploExcepciones()
            0 -> println("¡Hasta luego!")
            else -> println("Opción no válida. Inténtalo de nuevo.")
        }

        if (opcion != 0) {
            println("\nPulsa Enter para volver al menú.")
            readlnOrNull()
        }
    } while (opcion != 0)
}

private fun mostrarMenu() {
    println(
        """
        
        ==========================================
        ESTRUCTURAS DE CONTROL Y FLUJO EN KOTLIN
        ==========================================
        1. if / else (también como expresión)
        2. when (selección múltiple)
        3. for y rangos
        4. while
        5. do-while
        6. break y continue
        7. Etiquetas en bucles y lambdas
        8. return
        9. try, catch, finally y throw
        0. Salir
        """.trimIndent()
    )
}

private fun leerEntero(mensaje: String): Int {
    while (true) {
        print(mensaje)
        val valor = readlnOrNull()?.toIntOrNull()
        if (valor != null) return valor
        println("Introduce un número entero válido.")
    }
}

private fun ejemploIf() {
    println("\n--- if / else ---")
    val edad = leerEntero("Introduce tu edad: ")

    if (edad >= 18) {
        println("Eres mayor de edad.")
    } else {
        println("Eres menor de edad.")
    }

    // En Kotlin, if también puede devolver un valor.
    val categoria = if (edad >= 18) "adulto" else "menor"
    println("Como expresión, if asignó la categoría: $categoria.")
}

private fun ejemploWhen() {
    println("\n--- when ---")
    val dia = leerEntero("Introduce un número del 1 al 7: ")

    val nombreDia = when (dia) {
        1 -> "lunes"
        2 -> "martes"
        3 -> "miércoles"
        4 -> "jueves"
        5 -> "viernes"
        6, 7 -> "fin de semana"
        else -> "número fuera del rango"
    }
    println("Resultado: $nombreDia.")

    val nota = leerEntero("Introduce una nota del 0 al 10: ")
    val resultado = when {
        nota !in 0..10 -> "La nota no está entre 0 y 10."
        nota >= 9 -> "Sobresaliente"
        nota >= 7 -> "Notable"
        nota >= 5 -> "Aprobado"
        else -> "Suspenso"
    }
    println("Selección con condiciones: $resultado")
}

private fun ejemploFor() {
    println("\n--- for y rangos ---")

    print("Rango ascendente (1..5): ")
    for (numero in 1..5) print("$numero ")

    print("\nRango descendente (5 downTo 1): ")
    for (numero in 5 downTo 1) print("$numero ")

    print("\nCon saltos de 2 (0..10 step 2): ")
    for (numero in 0..10 step 2) print("$numero ")

    val frutas = listOf("manzana", "pera", "naranja")
    println("\nRecorrido de una lista:")
    for ((indice, fruta) in frutas.withIndex()) {
        println("$indice: $fruta")
    }
}

private fun ejemploWhile() {
    println("\n--- while ---")
    var cuenta = leerEntero("¿Desde qué número quieres contar hasta 1? ")

    while (cuenta > 0) {
        print("$cuenta ")
        cuenta--
    }
    println("\nEl bucle while comprueba la condición antes de cada iteración.")
}

private fun ejemploDoWhile() {
    println("\n--- do-while ---")
    var respuesta: String

    do {
        print("Escribe 'salir' para terminar este ejemplo: ")
        respuesta = readlnOrNull()?.trim().orEmpty()
        println("El bloque se ejecutó; escribiste: ${respuesta.ifEmpty { "(vacío)" }}")
    } while (!respuesta.equals("salir", ignoreCase = true))

    println("do-while ejecuta el bloque al menos una vez.")
}

private fun ejemploBreakYContinue() {
    println("\n--- break y continue ---")
    print("continue omite el 3 y break termina al llegar al 6: ")

    for (numero in 1..10) {
        if (numero == 3) continue
        if (numero == 6) break
        print("$numero ")
    }
    println()
}

private fun ejemploEtiquetas() {
    println("\n--- Etiquetas ---")
    println("break@exterior termina el bucle exterior desde un bucle anidado:")

    exterior@ for (fila in 1..3) {
        for (columna in 1..3) {
            if (fila == 2 && columna == 2) break@exterior
            println("fila=$fila, columna=$columna")
        }
    }

    println("return@elemento termina solo la iteración actual de forEach:")
    listOf(1, 2, 3, 4).forEach elemento@{ numero ->
        if (numero == 3) return@elemento
        println("Procesando $numero")
    }
}

private fun ejemploReturn() {
    println("\n--- return ---")
    val numero = leerEntero("Introduce un número: ")
    println("La función devuelve: ${describirParidad(numero)}")
    println("return termina la función y entrega un valor al punto de llamada.")
}

private fun describirParidad(numero: Int): String {
    if (numero % 2 == 0) return "$numero es par"
    return "$numero es impar"
}

private fun ejemploExcepciones() {
    println("\n--- try, catch, finally y throw ---")
    val numerador = leerEntero("Introduce el numerador: ")
    val denominador = leerEntero("Introduce el denominador: ")

    try {
        val resultado = dividir(numerador, denominador)
        println("Resultado: $resultado")
    } catch (error: IllegalArgumentException) {
        println("No se pudo realizar la operación: ${error.message}")
    } finally {
        println("El bloque finally se ejecuta tanto si hubo error como si no.")
    }

    // try también puede usarse como expresión.
    val texto = "42"
    val numero = try {
        texto.toInt()
    } catch (_: NumberFormatException) {
        0
    }
    println("Valor obtenido usando try como expresión: $numero")
}

private fun dividir(numerador: Int, denominador: Int): Int {
    if (denominador == 0) {
        throw IllegalArgumentException("el divisor no puede ser cero")
    }
    return numerador / denominador
}
