package com.example.listatareas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Lista de tareas en memoria
    private val listaTareas = mutableListOf<String>()
    private lateinit var adapter: TareaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Referencias a las vistas
        val etTarea: EditText = findViewById(R.id.etTarea)
        val btnAnadir: Button = findViewById(R.id.btnAnadir)
        val btnBorrarTodo: Button = findViewById(R.id.btnBorrarTodo)
        val txtContador: TextView = findViewById(R.id.txtContador)
        val recycler: RecyclerView = findViewById(R.id.recyclerTareas)

        // 2. Configurar el RecyclerView
        adapter = TareaAdapter(listaTareas) { posicion ->
            // Al pulsar una tarea, la borramos
            val tareaBorrada = listaTareas.removeAt(posicion)
            adapter.notifyItemRemoved(posicion)
            actualizarContador(txtContador)
            Toast.makeText(this, "Borrada: $tareaBorrada", Toast.LENGTH_SHORT).show()
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // 3. Botón AÑADIR
        btnAnadir.setOnClickListener {
            val texto = etTarea.text.toString().trim()

            if (texto.isEmpty()) {
                Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            listaTareas.add(texto)
            adapter.notifyItemInserted(listaTareas.size - 1)
            etTarea.text.clear()
            actualizarContador(txtContador)
            Toast.makeText(this, "Añadida: $texto", Toast.LENGTH_SHORT).show()
        }

        // 4. Botón BORRAR TODO
        btnBorrarTodo.setOnClickListener {
            if (listaTareas.isEmpty()) {
                Toast.makeText(this, "No hay tareas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listaTareas.clear()
            adapter.notifyDataSetChanged()
            actualizarContador(txtContador)
            Toast.makeText(this, "Lista vaciada", Toast.LENGTH_SHORT).show()
        }
    }

    // Método auxiliar para actualizar el contador
    private fun actualizarContador(txtContador: TextView) {
        txtContador.text = "Tareas: ${listaTareas.size}"
    }
}