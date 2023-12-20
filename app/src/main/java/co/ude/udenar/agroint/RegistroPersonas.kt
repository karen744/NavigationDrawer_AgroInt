package co.ude.udenar.agroint

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import co.ude.udenar.agroint.databinding.ActivityRegistroPersonasBinding

class RegistroPersonas : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroPersonasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroPersonasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.button4.setOnClickListener {
            val nombre = binding.editTextDatenombes.text.toString()
            val edad = binding.editTextNumberedad.text.toString()
            val correo = binding.editTextTextEmail.text.toString()
            val contrasena = binding.editTextTextPassword.text.toString()
            binding.button4.setOnClickListener {
                val nombre = binding.editTextDatenombes.text.toString()
                val edad = binding.editTextNumberedad.text.toString()
                val correo = binding.editTextTextEmail.text.toString()
                val contrasena = binding.editTextTextPassword.text.toString()

                if (nombre.isNotEmpty() && edad.isNotEmpty() && correo.isNotEmpty() && contrasena.isNotEmpty()) {
                    // Verificar si el correo electrónico tiene un formato válido
                    if (isEmailValid(correo)) {
                        registrarUsuario(correo, contrasena, nombre, edad.toInt())
                    } else {
                        Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }

    private fun registrarUsuario(correo: String, contraseña: String, nombre: String, edad: Int) {
        auth.createUserWithEmailAndPassword(correo, contraseña)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir al usuario o realizar otras acciones después del registro.
                } else {
                    Toast.makeText(this, "Error en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}
