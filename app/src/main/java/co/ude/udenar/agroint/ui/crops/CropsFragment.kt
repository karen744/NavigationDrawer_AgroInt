package co.ude.udenar.agroint.ui.crops

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.ude.udenar.agroint.databinding.FragmentCropsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar
import android.widget.Toast
import android.util.Log

class CropsFragment : Fragment() {

    private lateinit var binding: FragmentCropsBinding
    private var selectedDate: Calendar = Calendar.getInstance()
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCropsBinding.inflate(inflater, container, false)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.numberPickerHarvestTime.minValue = 1
        binding.numberPickerHarvestTime.maxValue = 15
        binding.numberPickerHarvestTime.wrapSelectorWheel = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el OnClickListener para el botón de selección de fecha
        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }

        // Configurar el OnClickListener para el botón de registro de cultivos
        binding.btnRegisterCrops.setOnClickListener {
            registerCrops()
        }
        // Otro código para el registro de cultivos
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                // Actualizar la fecha seleccionada
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Actualizar el TextView con la fecha seleccionada
                updateDateTextView()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )

        // Muestra el DatePickerDialog
        datePickerDialog.show()
    }

    private fun updateDateTextView() {
        // Formatear la fecha y mostrarla en el TextView
        val formattedDate =
            android.text.format.DateFormat.format("dd/MM/yyyy", selectedDate)
        binding.tvSelectedDate.text = formattedDate
    }

    private fun registerCrops() {
        // Datos del cultivo
        val nombreCultivo = binding.textFieldNombreCultivo.text.toString()
        val tipoCultivo = binding.textFieldTipoCultivo.text.toString()
        val ubicacion = binding.textFieldUbicacion.text.toString()

        // Formatear la fecha seleccionada
        val formattedDate = android.text.format.DateFormat.format("dd/MM/yyyy", selectedDate)

        // UID del usuario actual
        val userId = mAuth.currentUser?.uid

        // Obtén una referencia a la colección de cultivos
        val cultivosCollection = db.collection("cultivos")

        // Guardar el cultivo en Firestore
        val cultivoData = hashMapOf(
            "userId" to userId,
            "nombreCultivo" to nombreCultivo,
            "tipoCultivo" to tipoCultivo,
            "ubicacion" to ubicacion,
            "fechaSiembra" to formattedDate.toString() // Agregar la fecha al mapa
        )

        // Obtener el tiempo de cosecha seleccionado
        val harvestTime = binding.numberPickerHarvestTime.value

        // Agregar el tiempo de cosecha al mapa
        cultivoData["tiempoCosecha"] = harvestTime.toString()

        // Agregar un nuevo documento a la colección de cultivos
        cultivosCollection.add(cultivoData)
            .addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Cultivo registrado con éxito",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("CropsFragment", "Cultivo registrado con éxito")
                // Puedes realizar alguna acción adicional después de registrar el cultivo
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Error al registrar el cultivo",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("FIRESTORE", "Error: ${it.message}")
            }
    }
}