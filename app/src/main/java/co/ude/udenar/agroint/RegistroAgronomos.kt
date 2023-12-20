package co.ude.udenar.agroint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.ude.udenar.agroint.R
import android.content.Intent
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import co.ude.udenar.agroint.databinding.ActivityRegistroAgronomosBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroAgronomos : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroAgronomosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroAgronomosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnGoToLogin.setOnClickListener { goToLogin() }
        binding.btnRegister.setOnClickListener { register() }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun register() {
        val name = binding.textFieldName.text.toString()
        val lastName = binding.textFieldLastName.text.toString()
        val id = binding.textFieldId.text.toString()
        val phone = binding.textFieldPhone.text.toString()
        val email = binding.textFieldEmail.text.toString()
        val password = binding.textFieldPassword.text.toString()

        if (isValidForm(name, lastName, id, phone, email, password)) {
            /* Toast.makeText(this, "Formulario válido", Toast.LENGTH_SHORT).show() */
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this@RegistroAgronomos, "Registro exitoso", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this@RegistroAgronomos,
                        "Registro fallido ${it.exception.toString()}", Toast.LENGTH_LONG
                    ).show()
                    Log.d("FIREBASE", "Error: ${it.exception.toString()}")
                }
            }
        }
    }

    private fun isValidForm(
        name: String, lastName: String,
        phone: String, email: String,
        password: String, confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            Toast.makeText(this, "Debes ingresar tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if (lastName.isEmpty()) {
            Toast.makeText(this, "Debes ingresar tu apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, "Debes ingresar tu teléfono", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Debes ingresar tu correo electrónico", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmPassword.isEmpty()) {
            Toast.makeText(
                this,
                "Debes ingresar la confirmación de la contraseña",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener mínimo 6 carácteres", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun goToLogin() {
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }
}