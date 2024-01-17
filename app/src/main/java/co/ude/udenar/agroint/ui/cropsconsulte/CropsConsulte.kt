package co.ude.udenar.agroint.ui.cropsconsulte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class CropsConsulte : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CropsAdapter // Asegúrate de reemplazar CropsAdapter con el nombre real de tu adaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crops_consulte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val cultivosCollection = db.collection("cultivos")
        val userId = auth.currentUser?.uid

        cultivosCollection.whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                // Crear lista de documentos para el adaptador
                val documentList = documents.documents

                // Crear e inicializar el adaptador
                adapter = CropsAdapter(documentList)

                // Configurar el RecyclerView con el adaptador
                recyclerView.adapter = adapter
            }
            .addOnFailureListener {
                // Manejar el error
                // Puedes agregar un mensaje de error en caso de que la consulta falle
            }
    }
// ...

    inner class CropsAdapter(private val cultivos: List<DocumentSnapshot>) :
        RecyclerView.Adapter<CropsAdapter.CultivoViewHolder>() {

        inner class CultivoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nombreCultivoTextView: TextView = itemView.findViewById(R.id.nombreCultivoTextView)
            val tipoCultivoTextView: TextView = itemView.findViewById(R.id.tipoCultivoTextView)
            val ubicacionTextView: TextView = itemView.findViewById(R.id.ubicacionTextView)
            val fechaSiembraTextView: TextView = itemView.findViewById(R.id.fechaSiembraTextView)
            val tiempoCosechaTextView: TextView = itemView.findViewById(R.id.tiempoCosechaTextView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CultivoViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_crop, parent, false)
            return CultivoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CultivoViewHolder, position: Int) {
            val document = cultivos[position]

            // Acceder a los datos de cada cultivo
            val nombreCultivo = document.getString("nombreCultivo")
            val tipoCultivo = document.getString("tipoCultivo")
            val ubicacion = document.getString("ubicacion")

            // Nuevos datos a obtener
            val fechaSiembra = document.getString("fechaSiembra")
            val tiempoCosecha = document.getString("tiempoCosecha")

            // Mostrar los datos en los TextViews en lugar de Toast
            holder.nombreCultivoTextView.text = "Nombre: $nombreCultivo"
            holder.tipoCultivoTextView.text = "Tipo: $tipoCultivo"
            holder.ubicacionTextView.text = "Ubicación: $ubicacion"
            holder.fechaSiembraTextView.text = "Fecha de siembra: $fechaSiembra"

            val tiempoCosechaTexto = if (tiempoCosecha == "1") {
                "mes"
            } else {
                "meses"
            }
            holder.tiempoCosechaTextView.text = "Tiempo de cosecha: $tiempoCosecha $tiempoCosechaTexto"
        }

        override fun getItemCount(): Int {
            return cultivos.size
        }
    }

}