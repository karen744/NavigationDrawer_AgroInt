package co.ude.udenar.agroint.ui.ingenieros

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.ui.ingenieros.Ingeniero
import co.ude.udenar.agroint.ui.ingenieros.IngeAdapter
import co.ude.udenar.agroint.R

class IngeFragment : Fragment() {

    private val ingenieros = listOf(
        Ingeniero("Ingeniera Agronoma Marly Sofia", "Ingeniera Agronoma de la Universidad de Nariño,\n" +
                " Experiencia en Cultivos de clima Frio, en el cuidado de las plantas\n" +
                " 2 Años de experiencia, cuento con varios locales\n" +
                " ubicados en los municipios de La Cruz , La Union y en la ciduad de Pasto",
            "https://www.echocommunity.org/es/resources/d9f09c50-0194-41ea-9695-96fa96004706", R.drawable.ingeniera1),

        Ingeniero("Luis A.jurado - Ingeniero Agronomo",
            "Dirección : Cra 21 Nº 3-16 Los Balcones De Villa Lucia\n" +
                    "Ciudad o Municipio : Pasto, Nariño. Colombia\n"+"Contacto: (602) 736-6433",
            "https://www.nexdu.com/co/pasto-nar/empresa/luis-ajurado-ingeniero-agronomo-431667#telefonos-y-direccion", R.drawable.ing1),

        Ingeniero("Ingeniera Agronoma Jennyfer Moncayo Araujo", "Zona: Sur\n" +
                "Correo Electrónico: jmoncayoa@galagro.com.co\n" +
                "Celular: 3128393886",
            "https://www.camporigen.com/narino/", R.drawable.ingeniera2),

        Ingeniero("Ingeniero Agronomo Jhon Fredy Tarapuez Guerrero", "Zona: Norte\n" +
                "Correo Electrónico:  jtarapuesg@galagro.com.co\n" +
                "Celular: 3235775397",
            "https://www.camporigen.com/narino/", R.drawable.ingeniero2),
                // Agrega más cultivos según sea necesario
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingenieros, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = IngeAdapter(ingenieros) { abrirNavegador(it) }
        recyclerView.adapter = adapter

        return view
    }

    private fun abrirNavegador(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}