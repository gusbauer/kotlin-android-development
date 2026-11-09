/*
 * ACTIVIDADES II
 *
 * Completa cada ejercicio siguiendo las instrucciones. No borres los datos
 * de prueba; puedes añadir otros para comprobar tus resultados.
 */

// EJERCICIO 1: Temperaturas de la semana
//
// Convierte las temperaturas de Celsius a Fahrenheit usando map.
// Después, filtra las temperaturas originales que superen los 20 °C.
// Usa it en una de las lambdas y un nombre explícito para el parámetro en otra.
//
// Fórmula: Fahrenheit = Celsius * 9 / 5 + 32
fun ejercicio1Temperaturas() {
    val temperaturasCelsius = listOf(12, 18, 21, 25, 30)

    // TODO: crea la lista de temperaturas Fahrenheit.
    // TODO: crea la lista de temperaturas superiores a 20 °C.
    // TODO: muestra ambas listas.
}


// EJERCICIO 2: Calculadora flexible
//
// Completa aplicarOperacion para que reciba dos números y una operación
// representada por una lambda.
// Usa la función para calcular la suma, la resta y el producto de 12 y 4.
// Pasa cada lambda como último argumento, fuera de los paréntesis.
fun aplicarOperacion(
    primero: Int,
    segundo: Int,
    operacion: (Int, Int) -> Int
): Int {
    TODO("Aplica la operación recibida a los dos números")
}

fun ejercicio2Calculadora() {
    // TODO: calcula la suma, la resta y el producto usando aplicarOperacion.
    // TODO: muestra los tres resultados.
}


// EJERCICIO 3: Buscar un asiento libre
//
// Cada elemento de la matriz indica si un asiento está ocupado:
// true = ocupado, false = libre.
// Busca y muestra la fila y la columna del primer asiento libre.
// Usa break etiquetado para salir de ambos bucles al encontrarlo.
// Si no hay asientos libres, muestra un mensaje.
fun ejercicio3BuscarAsiento() {
    val asientosOcupados = listOf(
        listOf(true, true, true),
        listOf(true, false, true),
        listOf(false, true, true)
    )

    // TODO: recorre la matriz con dos bucles.
    // TODO: encuentra el primer asiento libre y termina ambos bucles.
    // TODO: muestra su posición o indica que no hay asientos libres.
}


// EJERCICIO 4: Procesar categorías de productos
//
// Recorre las categorías y muestra sus productos.
// Si un producto no tiene existencias, omite el resto de esa categoría
// y continúa con la siguiente usando continue etiquetado.
data class ProductoInventario(val nombre: String, val existencias: Int)

fun ejercicio4Inventario() {
    val categorias = listOf(
        listOf(
            ProductoInventario("Cuaderno", 8),
            ProductoInventario("Bolígrafo", 0),
            ProductoInventario("Carpeta", 5)
        ),
        listOf(
            ProductoInventario("Lápiz", 12),
            ProductoInventario("Goma", 4)
        ),
        listOf(
            ProductoInventario("Regla", 0),
            ProductoInventario("Tijeras", 3)
        )
    )

    // TODO: recorre las categorías y sus productos con bucles anidados.
    // TODO: al encontrar un producto sin existencias, pasa a la siguiente
    //       categoría con continue etiquetado.
    // TODO: muestra los productos que se hayan procesado.
}


// EJERCICIO 5: Calcular el total de una compra
//
// Recorre los productos con forEach y suma los precios mayores que cero.
// Usa return@forEach para omitir los productos con precio inválido sin
// detener el recorrido. Actualiza desde la lambda una variable externa.
// Al final, muestra el total y cuántos productos se omitieron.
data class ProductoCompra(val nombre: String, val precio: Double)

fun ejercicio5TotalCompra() {
    val productos = listOf(
        ProductoCompra("Libro", 18.50),
        ProductoCompra("Cuaderno", 3.25),
        ProductoCompra("Producto sin precio", 0.0),
        ProductoCompra("Mochila", 29.99),
        ProductoCompra("Precio inválido", -2.0)
    )

    // TODO: declara las variables necesarias para el total y los omitidos.
    // TODO: recorre productos con forEach y omite los precios no válidos.
    // TODO: muestra el total final y el número de productos omitidos.
}
