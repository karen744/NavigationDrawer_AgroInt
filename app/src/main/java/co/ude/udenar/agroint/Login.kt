package co.ude.udenar.agroint

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import co.ude.udenar.agroint.R
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout

    private val loginButton: Button by lazy {
        findViewById<Button>(R.id.buttonLogin)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        emailTextInputLayout = findViewById(R.id.textInputLayout2)
        passwordTextInputLayout = findViewById(R.id.textInputLayout)

        loginButton.setOnClickListener {
            val email = emailTextInputLayout.editText?.text.toString()
            val password = passwordTextInputLayout.editText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = mAuth.currentUser
                            if (user != null) {
                                Log.d(
                                    "LoginActivity",
                                    "Autenticación exitosa para ${user.email}"
                                )
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.e(
                                    "LoginActivity",
                                    "Error: El usuario es nulo después de la autenticación exitosa"
                                )
                            }
                        } else {
                            Log.e("LoginActivity", "Error de autenticación: ${task.exception}")
                            Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }
}