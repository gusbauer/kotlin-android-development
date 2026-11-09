package com.example.estructurascontrol

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estructurascontrol.ui.theme.EstructurasControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ejecutarEjemplos()

        setContent {
            EstructurasControlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaEjemplos(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun ejecutarEjemplos() {
    val TAG = "MI_APP"

    Log.d(TAG, "===== INICIO DE EJEMPLOS =====")

    // 1) IF / ELSE
    Log.d(TAG, "--- 1) IF / ELSE ---")
    val edad = 18
    if (edad >= 18) {
        Log.d(TAG, "Edad $edad: mayor de edad")
    } else {
        Log.d(TAG, "Edad $edad: menor de edad")
    }
    val categoria = if (edad >= 18) "adulto" else "menor"
    Log.d(TAG, "if como expresión: $categoria")

    // 2) WHEN
    Log.d(TAG, "--- 2) WHEN ---")
    val dia = 3
    val nombreDia = when (dia) {
        1 -> "Lunes"
        2 -> "Martes"
        3 -> "Miércoles"
        4 -> "Jueves"
        5 -> "Viernes"
        6, 7 -> "Fin de semana"
        else -> "Día inválido"
    }
    Log.d(TAG, "Día $dia es: $nombreDia")

    val nota = 7
    val calificacion = when (nota) {
        in 0..4 -> "Suspenso"
        in 5..6 -> "Aprobado"
        in 7..8 -> "Notable"
        in 9..10 -> "Sobresaliente"
        else -> "Nota inválida"
    }
    Log.d(TAG, "Nota $nota: $calificacion")

    // 3) FOR
    Log.d(TAG, "--- 3) FOR ---")
    for (i in 1..5) {
        Log.d(TAG, "for 1..5 → i = $i")
    }
    for (i in 10 downTo 0 step 2) {
        Log.d(TAG, "cuenta atrás: $i")
    }
    val frutas = listOf("manzana", "pera", "plátano")
    for ((indice, fruta) in frutas.withIndex()) {
        Log.d(TAG, "Fruta $indice: $fruta")
    }

    // 4) WHILE
    Log.d(TAG, "--- 4) WHILE ---")
    var contador = 0
    while (contador < 3) {
        Log.d(TAG, "while → contador = $contador")
        contador++
    }
    var intentos = 0
    do {
        Log.d(TAG, "do-while → intento $intentos")
        intentos++
    } while (intentos < 2)

    // 5) BREAK Y CONTINUE
    Log.d(TAG, "--- 5) BREAK Y CONTINUE ---")
    for (i in 1..10) {
        if (i == 3) {
            Log.d(TAG, "continue: me salto el $i")
            continue
        }
        if (i == 7) {
            Log.d(TAG, "break: corto el bucle en $i")
            break
        }
        Log.d(TAG, "número procesado: $i")
    }

    // 6) SUMA 1..100
    var suma = 0
    for (i in 1..100) suma += i
    Log.d(TAG, "Suma 1..100 = $suma")

    // 7) TABLA DE MULTIPLICAR DEL 7
    val num = 7
    for (i in 1..10) {
        Log.d(TAG, "$num x $i = ${num * i}")
    }

    Log.d(TAG, "===== FIN DE EJEMPLOS =====")
}

@Composable
fun PantallaEjemplos(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Mira el Logcat con el filtro: MI_APP")
        Text("Estructuras de control ejecutadas ✅")
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaEjemplosPreview() {
    EstructurasControlTheme {
        PantallaEjemplos()
    }
}