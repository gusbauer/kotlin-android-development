fun main() {
    println("====================================")
    println("MANEJO DE EXCEPCIONES EN KOTLIN")
    println("====================================")
    println("Una excepción representa un error ocurrido durante la ejecución.")

    ejemploTryCatch()
    ejemploVariosCatch()
    ejemploFinally()
    ejemploTryComoExpresion()
    ejemploThrow()
    ejemploExcepcionPersonalizada()
}

private fun ejemploTryCatch() {
    println("\n1. try y catch")
    println("El bloque try contiene el código que podría producir una excepción.")
    println("El bloque catch permite manejar el tipo de error indicado.")

    val texto = "123"
    try {
        val numero = texto.toInt()
        println("La conversión fue correcta: $numero")
    } catch (error: NumberFormatException) {
        println("No se pudo convertir el texto: ${error.message}")
    }
}

private fun ejemploVariosCatch() {
    println("\n2. Varios bloques catch")
    println("Puedes manejar distintos tipos de excepción por separado.")

    val textos = listOf("10", "no es un número")
    val indices = listOf(0, 5)

    for (indice in indices) {
        try {
            val texto = textos[indice]
            println("Elemento $indice convertido: ${texto.toInt()}")
        } catch (error: IndexOutOfBoundsException) {
            println("Índice fuera de los límites de la lista: ${error.message}")
        } catch (error: NumberFormatException) {
            println("El elemento no contiene un entero válido: ${error.message}")
        }
    }

    println("Coloca primero los catch más específicos y después los más generales.")
}

private fun ejemploFinally() {
    println("\n3. finally")
    println("finally se ejecuta al salir del try/catch, haya ocurrido un error o no.")

    try {
        println("Operación completada dentro de try.")
    } catch (error: Exception) {
        println("Se manejó un error: ${error.message}")
    } finally {
        println("Este bloque se ejecuta al finalizar el manejo.")
    }
}

private fun ejemploTryComoExpresion() {
    println("\n4. try como expresión")
    println("El resultado de try o catch se puede asignar a una variable.")

    val texto = "42"
    val numero = try {
        texto.toInt()
    } catch (error: NumberFormatException) {
        println("Conversión fallida: ${error.message}")
        0
    }
    println("Valor obtenido: $numero")
}

private fun ejemploThrow() {
    println("\n5. Lanzar una excepción con throw")
    println("throw permite señalar que una operación no se puede completar.")

    try {
        val resultado = dividir(12, 0)
        println("Resultado: $resultado")
    } catch (error: IllegalArgumentException) {
        println("No se pudo dividir: ${error.message}")
    }
}

private fun dividir(numerador: Int, denominador: Int): Int {
    if (denominador == 0) {
        throw IllegalArgumentException("el divisor no puede ser cero")
    }
    return numerador / denominador
}

private fun ejemploExcepcionPersonalizada() {
    println("\n6. Excepciones personalizadas")
    println("Puedes definir una excepción para describir errores propios de tu programa.")

    try {
        validarEdad(-2)
        println("La edad es válida.")
    } catch (error: EdadInvalidaException) {
        println("Error de validación: ${error.message}")
    }
}

private fun validarEdad(edad: Int) {
    if (edad < 0) {
        throw EdadInvalidaException("la edad no puede ser negativa")
    }
}

private class EdadInvalidaException(mensaje: String) : Exception(mensaje)
