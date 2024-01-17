package co.ude.udenar.agroint.ui.cropsconsulte

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar

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

        fun sendNotification(cultivo: DocumentSnapshot) {
            // Obtener los datos del cultivo
            val nombreCultivo = cultivo.getString("nombreCultivo")
            val tiempoCosecha = cultivo.getString("tiempoCosecha")
            val fechaSiembra = cultivo.getString("fechaSiembra")

            // Calcular la fecha de cosecha
            val fechaCosecha = Calendar.getInstance()
            if (tiempoCosecha != null) {
                fechaCosecha.time = SimpleDateFormat("dd/MM/yyyy").parse(fechaSiembra)
                fechaCosecha.add(Calendar.MONTH, tiempoCosecha.toInt())
            }

            // Obtener la fecha actual
            val fechaActual = Calendar.getInstance()

            // Comparar las fechas
            val diferenciaEnMeses = -(fechaCosecha.timeInMillis - fechaActual.timeInMillis) / (1000 * 60 * 60 * 24 * 30)

            // Enviar la notificación
            val notificationManager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationBuilder = NotificationCompat.Builder(requireContext(), "my_channel_id")
                .setContentTitle("Cosecha próxima")
                .setContentText("Te falta ${diferenciaEnMeses} mes para la cosecha de $nombreCultivo")
                .setSmallIcon(R.drawable.ic_planta)
            notificationManager.notify(1, notificationBuilder.build())
        }

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
            holder.itemView.setOnClickListener {
                // Enviar notificación
                adapter.sendNotification(document)
            }

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