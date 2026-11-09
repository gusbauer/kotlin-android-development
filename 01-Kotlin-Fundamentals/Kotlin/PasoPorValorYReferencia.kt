/*
 * ============================================================
 * APUNTES DE KOTLIN: PASO POR VALOR Y POR REFERENCIA
 * ============================================================
 *
 * IDEA PRINCIPAL:
 * Kotlin siempre pasa los argumentos por valor.
 *
 * - En un tipo básico, se copia el valor (por ejemplo, el número 10).
 * - En un objeto, se copia el valor de la referencia al objeto.
 *
 * Por eso, una función puede modificar un objeto mutable recibido
 * y el cambio se observa desde fuera. Sin embargo, reasignar el
 * parámetro no cambia la variable que se utilizó al llamar la función.
 *
 * En Kotlin no hay parámetros declarados como "pasados por referencia".
 */

// ============================================================
// 1. PASO POR VALOR CON TIPOS BÁSICOS
// ============================================================
/*
 * Al llamar incrementar, el parámetro recibe una copia del valor
 * de numero. Cambiar copia no modifica numero.
 *
 * Int es inmutable: las operaciones producen un valor nuevo, no
 * alteran el Int existente.
 */
private fun incrementar(numero: Int) {
    // numero = numero + 1
    // No compila: los parámetros de una función son de solo lectura.
    println("Dentro de incrementar, numero vale $numero")
}

private fun ejemploTipoBasico() {
    val original = 10
    incrementar(original)
    println("Fuera de la función, original sigue valiendo $original")

    // Para obtener otro valor, la función puede devolverlo.
    val aumentado = incrementarYDevolver(original)
    println("Valor devuelto: $aumentado")
    println("El original no cambió: $original")
}

private fun incrementarYDevolver(numero: Int): Int {
    return numero + 1
}

// ============================================================
// 2. PASO POR VALOR DE UNA REFERENCIA
// ============================================================
/*
 * Una variable que contiene un objeto contiene una referencia a ese
 * objeto. Al pasarla a una función, Kotlin copia esa referencia.
 *
 * La variable externa y el parámetro son dos referencias distintas
 * que, inicialmente, apuntan al mismo objeto.
 *
 * Si el objeto es mutable, modificarlo a través del parámetro afecta
 * al mismo objeto que ve la variable externa.
 */
private class Contador(var valor: Int)

private fun aumentarContador(contador: Contador) {
    contador.valor += 1
    println("Dentro de aumentarContador: ${contador.valor}")
}

private fun ejemploMutarObjeto() {
    val contador = Contador(10)

    aumentarContador(contador)

    println("Fuera de la función: ${contador.valor}")
}

// ============================================================
// 3. MUTAR EL OBJETO NO ES REASIGNAR EL PARÁMETRO
// ============================================================
/*
 * En este ejemplo, dentro de la función se asigna otra referencia
 * al parámetro local. La variable contador de quien llama sigue
 * apuntando al objeto original.
 *
 * Los parámetros son de solo lectura, pero se puede declarar otra
 * variable local con var y reasignarla.
 */
private fun reasignarReferenciaLocal(contador: Contador) {
    var referenciaLocal = contador
    referenciaLocal = Contador(999)

    println("Objeto de la referencia local: ${referenciaLocal.valor}")
}

private fun ejemploReasignarParametro() {
    val contadorOriginal = Contador(10)

    reasignarReferenciaLocal(contadorOriginal)

    println("El objeto original conserva su valor: ${contadorOriginal.valor}")
}

// ============================================================
// 4. LISTAS MUTABLES E INMUTABLES
// ============================================================
/*
 * List<T> ofrece una interfaz de solo lectura: desde esa referencia
 * no se pueden añadir ni quitar elementos.
 *
 * MutableList<T> permite cambiar el contenido del objeto. Si una
 * función recibe una MutableList y la modifica, quien la llamó ve
 * los cambios porque ambos acceden a la misma lista.
 */
private fun agregarElemento(lista: MutableList<String>, elemento: String) {
    lista.add(elemento)
}

private fun ejemploListas() {
    val frutas = mutableListOf("manzana", "pera")

    agregarElemento(frutas, "uva")

    println("La lista modificada desde la función: $frutas")

    val soloLectura: List<String> = frutas
    println("Vista de solo lectura: $soloLectura")

    // soloLectura.add("naranja")
    // No compila: List no ofrece la operación add.
    //
    // La lista subyacente puede seguir siendo mutable si otra
    // referencia la expone como MutableList. List no garantiza que
    // el objeto sea inmutable; solo limita las operaciones disponibles
    // a través de esa referencia.
}

// ============================================================
// 5. DEVOLVER UNA REFERENCIA NUEVA
// ============================================================
/*
 * Una función puede crear y devolver otra lista. La lista recibida
 * no cambia; el resultado es un objeto distinto.
 */
private fun conElemento(
    elementos: List<String>,
    nuevo: String
): List<String> {
    return elementos + nuevo
}

private fun ejemploDevolverObjetoNuevo() {
    val original = listOf("rojo", "azul")
    val resultado = conElemento(original, "verde")

    println("Lista original: $original")
    println("Lista devuelta: $resultado")
    println("¿Son la misma instancia? ${original === resultado}")
}

// ============================================================
// 6. VAL NO SIGNIFICA OBJETO INMUTABLE
// ============================================================
/*
 * val impide reasignar la variable a otra referencia. No impide
 * modificar el estado de un objeto mutable al que apunta.
 */
private fun ejemploValYMutabilidad() {
    val lista = mutableListOf(1, 2)
    lista.add(3) // Válido: se modifica el contenido de la lista.
    println("Contenido de lista: $lista")

    // lista = mutableListOf(4, 5)
    // No compila: una variable val no puede apuntar a otra lista.

    var otraLista = mutableListOf("a")
    otraLista = mutableListOf("b")
    println("otraLista ahora apunta a: $otraLista")
}

// ============================================================
// 7. DOS VARIABLES QUE COMPARTEN EL MISMO OBJETO
// ============================================================
/*
 * Asignar una variable de objeto a otra copia la referencia, no clona
 * el objeto. Las dos variables pueden observar las mismas mutaciones.
 */
private fun ejemploAlias() {
    val primeraReferencia = mutableListOf("uno")
    val segundaReferencia = primeraReferencia

    segundaReferencia.add("dos")

    println("Primera referencia: $primeraReferencia")
    println("Segunda referencia: $segundaReferencia")
    println("¿Apuntan al mismo objeto? ${primeraReferencia === segundaReferencia}")
}

// ============================================================
// 8. IGUALDAD DE CONTENIDO Y DE IDENTIDAD
// ============================================================
/*
 * == compara igualdad estructural: equivale a llamar equals().
 * === comprueba identidad referencial: pregunta si es la misma instancia.
 */
private fun ejemploIgualdadEIdentidad() {
    val primera = mutableListOf(1, 2, 3)
    val alias = primera
    val copia = mutableListOf(1, 2, 3)

    println("primera == alias: ${primera == alias}") // true
    println("primera === alias: ${primera === alias}") // true
    println("primera == copia: ${primera == copia}") // true
    println("primera === copia: ${primera === copia}") // false
}

// ============================================================
// 9. COPIA SUPERFICIAL Y COPIA PROFUNDA
// ============================================================
/*
 * En una data class, copy() crea una instancia nueva, pero es una
 * copia superficial: las propiedades que son objetos siguen
 * compartiendo sus referencias.
 *
 * Para obtener una copia profunda, hay que copiar también los objetos
 * mutables que se desea separar.
 */
private data class Equipo(
    val nombre: String,
    val miembros: MutableList<String>
)

private fun ejemploCopias() {
    val original = Equipo("Equipo A", mutableListOf("Ana", "Luis"))
    val copiaSuperficial = original.copy()

    copiaSuperficial.miembros.add("Marta")

    println("Original tras mutar la lista compartida: $original")
    println("Copia superficial: $copiaSuperficial")
    println(
        "¿Comparten la lista? " +
            "${original.miembros === copiaSuperficial.miembros}"
    )

    val copiaProfunda = original.copy(miembros = original.miembros.toMutableList())
    copiaProfunda.miembros.add("Nora")

    println("Original tras modificar la copia profunda: $original")
    println("Copia profunda: $copiaProfunda")
    println("¿Comparten la lista? ${original.miembros === copiaProfunda.miembros}")
}

// ============================================================
// 10. CÓMO CAMBIAR UNA VARIABLE DEL LLAMADOR
// ============================================================
/*
 * Una función no puede reasignar directamente la variable local del
 * llamador mediante un parámetro normal. Dos alternativas habituales:
 *
 * 1. Devolver el nuevo valor y asignarlo en quien llama.
 * 2. Guardar el valor en un objeto mutable y modificar una propiedad.
 */
private fun reemplazarContador(contador: Contador): Contador {
    return Contador(contador.valor + 100)
}

private class Contenedor<T>(var valor: T)

private fun reemplazarDentroDelContenedor(contenedor: Contenedor<Contador>) {
    contenedor.valor = Contador(contenedor.valor.valor + 100)
}

private fun ejemploCambiarVariable() {
    var contador = Contador(1)
    contador = reemplazarContador(contador)
    println("Nuevo contador devuelto: ${contador.valor}")

    val contenedor = Contenedor(Contador(1))
    reemplazarDentroDelContenedor(contenedor)
    println("Contador reemplazado dentro del contenedor: ${contenedor.valor.valor}")
}

// ============================================================
// 11. EJEMPLO CLÁSICO: INTERCAMBIAR DOS VARIABLES
// ============================================================
/*
 * Una función que recibe dos Int no puede intercambiar las variables
 * del llamador modificando solamente sus parámetros.
 *
 * Puede devolver el resultado como Pair y quien llama reasigna las
 * variables. Otra posibilidad es usar un contenedor mutable.
 */
private fun intercambiar(a: Int, b: Int): Pair<Int, Int> {
    return b to a
}

private fun ejemploIntercambio() {
    var primero = 10
    var segundo = 20

    val intercambiados = intercambiar(primero, segundo)
    primero = intercambiados.first
    segundo = intercambiados.second

    println("primero = $primero, segundo = $segundo")
}

// ============================================================
// 12. TABLA RESUMEN
// ============================================================
/*
 * CASO                         | Qué recibe la función       | Efecto
 * -----------------------------|-----------------------------|-----------------------
 * Int, Boolean, Double, etc.   | Una copia del valor         | El original no cambia
 * Objeto inmutable             | Una copia de la referencia  | No se cambia su estado
 * Objeto mutable               | Una copia de la referencia  | Se puede mutar el objeto
 * Reasignar un var local       | Una referencia local nueva | No reasigna la variable externa
 * Devolver un valor u objeto   | Un resultado                | El llamador puede reasignarlo
 * Modificar una propiedad      | Estado de objeto compartido | El cambio se ve desde fuera
 */

// ============================================================
// 13. IDEAS PARA RECORDAR
// ============================================================
/*
 * - Kotlin siempre pasa argumentos por valor.
 * - Para los objetos, el valor copiado es una referencia.
 * - Mutar el objeto y reasignar una referencia son operaciones distintas.
 * - val protege la referencia contra reasignaciones, no hace inmutable
 *   el objeto al que apunta.
 * - List es una interfaz de solo lectura; no siempre implica que la
 *   instancia subyacente sea inmutable.
 * - == compara contenido; === compara si es la misma instancia.
 * - copy() en una data class es superficial si sus propiedades contienen
 *   objetos; para aislarlos, copia también esos objetos.
 * - Para comunicar un resultado nuevo, normalmente devuelve ese resultado.
 */

// ============================================================
// MAIN: EJECUCIÓN EN CONSOLA
// ============================================================
fun main() {
    println("==============================================")
    println("PASO POR VALOR Y POR REFERENCIA EN KOTLIN")
    println("==============================================")

    println("\n1. Tipos básicos")
    ejemploTipoBasico()

    println("\n2. Mutar un objeto")
    ejemploMutarObjeto()

    println("\n3. Reasignar una referencia local")
    ejemploReasignarParametro()

    println("\n4. Listas")
    ejemploListas()

    println("\n5. Devolver un objeto nuevo")
    ejemploDevolverObjetoNuevo()

    println("\n6. val y mutabilidad")
    ejemploValYMutabilidad()

    println("\n7. Referencias compartidas")
    ejemploAlias()

    println("\n8. Igualdad e identidad")
    ejemploIgualdadEIdentidad()

    println("\n9. Copias superficiales y profundas")
    ejemploCopias()

    println("\n10. Cambiar el valor desde el llamador")
    ejemploCambiarVariable()

    println("\n11. Intercambiar valores")
    ejemploIntercambio()
}
