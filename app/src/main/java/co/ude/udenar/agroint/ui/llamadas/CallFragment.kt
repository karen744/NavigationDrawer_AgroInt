package co.ude.udenar.agroint.ui.llamadas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import co.ude.udenar.agroint.R

class CallFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val rootView = inflater.inflate(R.layout.fragment_call, container, false)

        // Obtener la referencia al ListView desde el diseño inflado
        val listView: ListView = rootView.findViewById(R.id.listViewContacts)

        // Crear una lista de contactos (reemplaza con tu propia lógica)
        val contacts = listOf(
            Contact("Marly Sofia", "3218101445"),
            Contact("Luis A. Jurado", "3006930440"),
            // Agrega más contactos según sea necesario
        )

        // Crear el adaptador personalizado y asignarlo al ListView
        val adapter = ContacAdapter(requireContext(), contacts)
        listView.adapter = adapter

        // Manejar clics en los elementos de la lista
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            // Realizar acciones según el contacto seleccionado, por ejemplo, iniciar una llamada
            // Puedes abrir una nueva interfaz aquí o iniciar una llamada como se mencionó antes
        }

        return rootView
    }
}
