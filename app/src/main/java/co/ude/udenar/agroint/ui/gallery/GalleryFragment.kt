package co.ude.udenar.agroint.ui.gallery

// GalleryFragment.kt
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.Cultivo
import co.ude.udenar.agroint.CultivoAdapter
import co.ude.udenar.agroint.R


class GalleryFragment : Fragment() {

    private val cultivos = listOf(
        Cultivo("FRIJOL CAUPÍ", "Cultivos en San Juan de Pasto", "https://www.echocommunity.org/es/resources/d9f09c50-0194-41ea-9695-96fa96004706", R.drawable.frijol),
        Cultivo("ALMORTA (ALVERJÓN, ARVEJA, GARBANZO DE YERBA, GUIJA)", "Cultivos en San Juan de Pasto", "https://www.echocommunity.org/es/resources/fe83b0ce-21bf-4dbc-a02a-4472897fe71c", R.drawable.almorta),
        // Agrega más cultivos según sea necesario
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = CultivoAdapter(cultivos) { abrirNavegador(it) }
        recyclerView.adapter = adapter

        return view
    }

    private fun abrirNavegador(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}
