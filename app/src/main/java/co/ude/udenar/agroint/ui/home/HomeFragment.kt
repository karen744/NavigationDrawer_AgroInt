package co.ude.udenar.agroint.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.R
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var greetingTextView: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Dummy data for testing
        val itemList = listOf(
            ItemModel(R.drawable.wen, "Item 1"),
            ItemModel(R.drawable.uev, "Item 2"),
            ItemModel(R.drawable.es, "Item 3"),
            ItemModel(R.drawable.s, "Item 4"),
            ItemModel(R.drawable.d, "Item 5"),
            ItemModel(R.drawable.l, "Item 6"),
            ItemModel(R.drawable.ma, "Item 7"),
            // Add more items as needed
        )

        // Inicializar Firebase Authentication
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Obtén una referencia al TextView
        greetingTextView = view.findViewById(R.id.greetingTextView)

        // Verifica si el usuario está autenticado
        val user = mAuth.currentUser
        if (user != null) {
            // El usuario está autenticado, obtén su nombre desde Firestore
            obtenerNombreUsuarioDesdeFirestore(user.uid)
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.HORIZONTAL, false)
        itemAdapter = ItemAdapter(itemList)
        recyclerView.adapter = itemAdapter

        return view
    }

    private fun obtenerNombreUsuarioDesdeFirestore(userId: String) {
        val userDoc = db.collection("users").document(userId)
        userDoc.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val nombreUsuario = document.getString("displayName")
                if (nombreUsuario != null && nombreUsuario.isNotEmpty()) {
                    // Establece el saludo personalizado
                    val saludo = "Hola $nombreUsuario"
                    greetingTextView.text = saludo
                } else {
                    // Si no hay un nombre de usuario configurado, muestra un mensaje predeterminado
                    greetingTextView.text = "Hola Usuario"
                }
            } else {
                greetingTextView.text = "Hola Usuario"
            }
        }.addOnFailureListener {
            greetingTextView.text = "Hola Usuario"
            // Maneja el error al obtener el nombre de usuario desde Firestore
            // Log.d("FIRESTORE", "Error al obtener el nombre de usuario: ${it.message}")
        }
    }
}
