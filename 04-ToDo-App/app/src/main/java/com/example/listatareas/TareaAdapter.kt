package com.example.listatareas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    private val tareas: MutableList<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    // ViewHolder: guarda las referencias a las vistas de una fila
    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTarea: TextView = itemView.findViewById(R.id.txtItemTarea)
    }

    // Crea la vista de cada fila inflando el XML item_tarea
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    // Rellena los datos de cada fila
    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.txtTarea.text = tarea

        // Detecta el clic en la fila → llama al callback
        holder.itemView.setOnClickListener {
            onItemClick(holder.adapterPosition)
        }
    }

    // Cuántas filas hay
    override fun getItemCount(): Int = tareas.size
}