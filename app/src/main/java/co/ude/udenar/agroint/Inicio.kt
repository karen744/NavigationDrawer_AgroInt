package co.ude.udenar.agroint


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var buttonRegistroAgronomos: Button
    private lateinit var buttonRegistroPersonas: Button
    private lateinit var buttonIniciarSesion: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        imageButton = findViewById(R.id.imageButton)
        buttonRegistroAgronomos = findViewById(R.id.button)
        buttonRegistroPersonas = findViewById(R.id.button2)
        buttonIniciarSesion = findViewById(R.id.button3)

        imageButton.setOnClickListener {
            showToast("Karen Mutis, David Guerrero, Felipe Arteaga, AGROINT")
        }

        buttonRegistroAgronomos.setOnClickListener {
            // Iniciar la actividad de registro de agrónomos
            val intent = Intent(this, RegistroAgronomos::class.java)
            startActivity(intent)
        }

        buttonRegistroPersonas.setOnClickListener {
            // Iniciar la actividad de registro de personas
            val intent = Intent(this, RegistroPersonas::class.java)
            startActivity(intent)
        }
        buttonIniciarSesion.setOnClickListener {
            // Iniciar la actividad de registro de personas
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }



    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
