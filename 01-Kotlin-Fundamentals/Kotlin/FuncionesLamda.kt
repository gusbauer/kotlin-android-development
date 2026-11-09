fun main() {
    println("====================================")
    println("FUNCIONES LAMBDA EN KOTLIN")
    println("====================================")
    println("Una lambda es una función que se puede guardar en una variable o pasar como argumento.")

    ejemploSintaxisYTipos()
    ejemploParametroImplicito()
    ejemploFuncionesDeOrdenSuperior()
    ejemploColecciones()
    ejemploLambdaComoUltimoParametro()
    ejemploCapturaDeVariables()
}

private fun ejemploSintaxisYTipos() {
    println("\n1. Sintaxis y tipos de una lambda")
    println("Forma general: { parametros -> cuerpo }")

    val sumar: (Int, Int) -> Int = { primero, segundo ->
        primero + segundo
    }

    println("sumar(3, 4) = ${sumar(3, 4)}")
    println("El tipo (Int, Int) -> Int indica dos parámetros Int y un resultado Int.")

    val cuadrado: (Int) -> Int = { numero -> numero * numero }
    println("cuadrado(5) = ${cuadrado(5)}")
}

private fun ejemploParametroImplicito() {
    println("\n2. Parámetro implícito it")
    println("Si la lambda recibe un único parámetro, puedes llamarlo it:")

    val duplicar: (Int) -> Int = { it * 2 }
    println("duplicar(6) = ${duplicar(6)}")

    val palabras = listOf("sol", "montaña", "mar")
    val palabrasLargas = palabras.filter { palabra -> palabra.length > 3 }
    println("Con un nombre explícito: $palabrasLargas")

    val palabrasCortas = palabras.filter { it.length <= 3 }
    println("Usando it: $palabrasCortas")
}

private fun ejemploFuncionesDeOrdenSuperior() {
    println("\n3. Funciones de orden superior")
    println("Una función de orden superior recibe una función como parámetro o la devuelve.")

    val suma = aplicarOperacion(8, 3) { a, b -> a + b }
    val producto = aplicarOperacion(8, 3) { a, b -> a * b }

    println("Suma calculada con una lambda: $suma")
    println("Producto calculado con otra lambda: $producto")
}

private fun aplicarOperacion(
    primero: Int,
    segundo: Int,
    operacion: (Int, Int) -> Int
): Int {
    return operacion(primero, segundo)
}

private fun ejemploColecciones() {
    println("\n4. Lambdas con colecciones")
    val numeros = listOf(1, 2, 3, 4, 5)

    val dobles = numeros.map { it * 2 }
    val pares = numeros.filter { it % 2 == 0 }

    println("Lista original: $numeros")
    println("map transforma cada elemento: $dobles")
    println("filter conserva los elementos que cumplen la condición: $pares")

    print("forEach ejecuta una acción por elemento: ")
    numeros.forEach { numero -> print("$numero ") }
    println()
}

private fun ejemploLambdaComoUltimoParametro() {
    println("\n5. Lambda como último parámetro")
    println("Si el último parámetro de una función es una lambda, puede escribirse fuera de los paréntesis:")

    val saludo = construirMensaje("Ana") { nombre ->
        "¡Hola, $nombre!"
    }
    println(saludo)
}

private fun construirMensaje(nombre: String, crear: (String) -> String): String {
    return crear(nombre)
}

private fun ejemploCapturaDeVariables() {
    println("\n6. Captura de variables")
    println("Una lambda puede leer y modificar una variable declarada fuera de ella:")

    var total = 0
    val agregarAlTotal: (Int) -> Unit = { cantidad ->
        total += cantidad
    }

    agregarAlTotal(5)
    agregarAlTotal(7)
    println("Total después de agregar 5 y 7: $total")
}
