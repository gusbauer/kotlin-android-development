/*
 * ============================================================
 * APUNTES DE KOTLIN: SCOPE (ALCANCE)
 * ============================================================
 *
 * El scope indica desde qué partes del código se puede acceder
 * a una declaración. También se usa "scope functions" para
 * nombrar let, run, with, apply y also.
 *
 * Este archivo contiene ejemplos ejecutables para consola.
 */

// ============================================================
// 1. SCOPE DE BLOQUE
// ============================================================
/*
 * Una variable declarada dentro de un bloque { } solo puede
 * usarse desde ese bloque y sus bloques interiores.
 */
private fun ejemploScopeBloque() {
    val exterior = "Visible en el bloque de la función"

    if (true) {
        val interior = "Visible solo dentro del if"
        println(exterior)
        println(interior)
    }

    println(exterior)
    // println(interior) // Error: interior no está en este scope.
}

// ============================================================
// 2. SCOPE DE FUNCIÓN
// ============================================================
/*
 * Los parámetros y las variables locales pertenecen a la
 * función en la que se declaran.
 */
private fun ejemploScopeFuncion() {
    fun saludar(nombre: String) {
        val saludo = "Hola, $nombre"
        println(saludo)
    }

    saludar("Ana")
    // println(nombre) // Error: nombre solo existe en saludar().
}

// ============================================================
// 3. SCOPE DE CLASE
// ============================================================
/*
 * Los miembros de una clase pueden utilizarse desde sus métodos.
 * La visibilidad controla además desde dónde se permite acceder
 * a ellos. Por ejemplo, private limita el acceso a la clase.
 */
private class Persona(val nombre: String) {
    private val edad = 25

    fun presentarse() {
        println("$nombre tiene $edad años")
    }
}

private fun ejemploScopeClase() {
    val persona = Persona("Luis")
    persona.presentarse()
    // println(persona.edad) // Error: edad es private.
}

// ============================================================
// 4. SHADOWING Y THIS
// ============================================================
/*
 * Si un parámetro tiene el mismo nombre que una propiedad,
 * el parámetro más cercano oculta a la propiedad. this.nombre
 * permite referirse explícitamente a la propiedad de la instancia.
 */
private class Usuario {
    var nombre = "Nombre inicial"

    fun cambiarNombre(nombre: String) {
        println("Parámetro: $nombre")
        println("Propiedad actual: ${this.nombre}")
        this.nombre = nombre
    }
}

private fun ejemploThisYShadowing() {
    val usuario = Usuario()
    usuario.cambiarNombre("Marta")
    println("Nuevo nombre: ${usuario.nombre}")
}

// ============================================================
// 5. SCOPE FUNCTIONS
// ============================================================
/*
 * Las scope functions ejecutan un bloque sobre un objeto.
 *
 * +---------+----------------+------------------+----------------------+
 * | Función | Referencia     | Retorno          | Uso habitual         |
 * +---------+----------------+------------------+----------------------+
 * | let     | it             | Resultado lambda | Nulos y transformación|
 * | run     | this           | Resultado lambda | Calcular un resultado|
 * | with    | this           | Resultado lambda | Operar sobre objeto  |
 * | apply   | this           | Objeto original  | Configurar objeto    |
 * | also    | it             | Objeto original  | Efectos secundarios  |
 * +---------+----------------+------------------+----------------------+
 *
 * it significa que el objeto se nombra como parámetro.
 * this significa que el objeto es el receptor del bloque.
 */

// let: referencia it y devuelve lo que produce la lambda.
private fun ejemploLet() {
    val nombre: String? = "Kotlin"

    nombre?.let {
        println("Longitud del nombre: ${it.length}")
    }

    val longitud = nombre?.let { it.length } ?: 0
    println("Longitud calculada: $longitud")
}

// run: usa this cuando se aplica a un objeto y devuelve el resultado.
private fun ejemploRun() {
    val resultado = run {
        val primero = 10
        val segundo = 20
        primero + segundo
    }
    println("Resultado de run: $resultado")

    val tamaño = Persona("Ana").run {
        println("Nombre dentro de run: $nombre")
        nombre.length
    }
    println("Tamaño del nombre: $tamaño")
}

// with: recibe el objeto como argumento, usa this y devuelve el resultado.
private fun ejemploWith() {
    val persona = Persona("Luis")
    val descripción = with(persona) {
        println("Nombre dentro de with: $nombre")
        "Persona procesada: $nombre"
    }
    println(descripción)
}

// apply: usa this y devuelve el mismo objeto; resulta útil para configurarlo.
private fun ejemploApply() {
    val configuración = Configuración().apply {
        host = "localhost"
        puerto = 8080
    }

    println("Configuración: ${configuración.host}:${configuración.puerto}")
}

// also: usa it y devuelve el mismo objeto; útil para acciones adicionales.
private fun ejemploAlso() {
    val números = mutableListOf(1, 2, 3).also {
        println("Lista creada: $it")
    }

    println("La lista sigue disponible: $números")
}

private class Configuración {
    var host: String = ""
    var puerto: Int = 0
}

// ============================================================
// 6. FUNCIÓN DE SCOPE PERSONALIZADA
// ============================================================
/*
 * Una lambda con receiver (T.() -> Unit) permite usar miembros
 * de T dentro del bloque como si fueran propiedades o funciones
 * locales. Esta función devuelve el objeto original.
 */
private inline fun <T> T.miScope(block: T.() -> Unit): T {
    block()
    return this
}

private fun ejemploMiScope() {
    val persona = Persona("Marta").miScope {
        println("Dentro de miScope: $nombre")
    }

    println("Fuera de miScope: ${persona.nombre}")
}

// ============================================================
// 7. LAMBDA CON RECEIVER
// ============================================================
/*
 * StringBuilder.() -> Unit es una lambda con receiver.
 * Dentro del bloque, append() se puede llamar directamente.
 */
private fun construirHtml(block: StringBuilder.() -> Unit): String {
    val resultado = StringBuilder()
    resultado.block()
    return resultado.toString()
}

private fun ejemploLambdaConReceiver() {
    val página = construirHtml {
        append("<h1>Hola</h1>")
        append("<p>Mundo</p>")
    }

    println("HTML generado: $página")
}

// ============================================================
// 8. BUENAS PRÁCTICAS
// ============================================================
private fun ejemploBuenasPracticas() {
    // apply: configura un objeto y devuelve ese mismo objeto.
    val configuración = Configuración().apply {
        host = "127.0.0.1"
        puerto = 3000
    }

    // let: ejecuta el bloque solo si el valor no es nulo.
    val texto: String? = null
    texto?.let { println("Texto no nulo: $it") }

    // run: agrupa cálculos y devuelve el resultado de la última expresión.
    val total = run {
        val primero = 5
        val segundo = 7
        primero * segundo
    }

    // also: permite observar el objeto, por ejemplo, para mostrarlo.
    val resultado = (1..3).toList().also {
        println("Lista creada: $it")
    }

    println("${configuración.host}:${configuración.puerto}")
    println("Total: $total")
    println("Resultado: $resultado")

    // Evita encadenar demasiadas scope functions: puede ocultar
    // qué objeto representa it o this y hacer el código difícil de leer.
}

// ============================================================
// 9. EJEMPLO DE REPASO: QUÉ DEVUELVE CADA FUNCIÓN
// ============================================================
private fun ejemploRepaso() {
    val conLet = "Kotlin".let { it.uppercase() }
    val conApply = "Kotlin".apply { uppercase() }

    println("let devuelve el resultado de la lambda: $conLet")
    println("apply devuelve el objeto original: $conApply")
}

// ============================================================
// MAIN: EJECUCIÓN EN CONSOLA
// ============================================================
fun main() {
    println("========================================")
    println("APUNTES DE KOTLIN: SCOPE")
    println("========================================")

    println("\n1. Scope de bloque")
    ejemploScopeBloque()

    println("\n2. Scope de función")
    ejemploScopeFuncion()

    println("\n3. Scope de clase")
    ejemploScopeClase()

    println("\n4. this y shadowing")
    ejemploThisYShadowing()

    println("\n5. Scope functions")
    println("- let")
    ejemploLet()
    println("- run")
    ejemploRun()
    println("- with")
    ejemploWith()
    println("- apply")
    ejemploApply()
    println("- also")
    ejemploAlso()

    println("\n6. Scope personalizado")
    ejemploMiScope()

    println("\n7. Lambda con receiver")
    ejemploLambdaConReceiver()

    println("\n8. Buenas prácticas")
    ejemploBuenasPracticas()

    println("\n9. Repaso")
    ejemploRepaso()
}
