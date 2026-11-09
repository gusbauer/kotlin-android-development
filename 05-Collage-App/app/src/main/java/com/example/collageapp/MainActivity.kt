package com.example.collageapp

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Referencias a las 4 imágenes
    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView

    private lateinit var imagenes: List<ImageView>
    private var imagenSeleccionada: ImageView? = null

    // Lanzador del Photo Picker (no requiere permisos en Android 13+)
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                imagenSeleccionada?.setImageURI(uri)
            } else {
                Toast.makeText(this, R.string.msg_sin_seleccion, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Enlazar vistas
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)

        imagenes = listOf(img1, img2, img3, img4)

        // 2. Al tocar cualquier imagen, abrir galería
        imagenes.forEach { imageView ->
            imageView.setOnClickListener {
                imagenSeleccionada = imageView
                abrirGaleria()
            }
        }

        // 3. Botón limpiar
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnLimpiar.setOnClickListener {
            imagenes.forEach { it.setImageResource(android.R.drawable.ic_menu_gallery) }
            Toast.makeText(this, R.string.msg_limpio, Toast.LENGTH_SHORT).show()
        }
    }

    /** Abre el selector de fotos del sistema */
    private fun abrirGaleria() {
        pickImageLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }
}