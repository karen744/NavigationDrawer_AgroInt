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
        Cultivo("FRIJOL CAUPÍ", "Cultivos en San Juan de Pasto",
            "https://www.echocommunity.org/es/resources/d9f09c50-0194-41ea-9695-96fa96004706", R.drawable.frijol),
        Cultivo("Trigo y Sebada", "Cultivos en San Juan de Pasto",
            "https://agriperfiles.agri-d.net/display/AS-pub-877EBAE08A11DC8776A37598EB3831EC", R.drawable.trigo),
        Cultivo("Papa Pastusa", "Cultivos en San Juan de Pasto",
            "https://editorial.agrosavia.co/index.php/publicaciones/catalog/book/218", R.drawable.papa),
        Cultivo("Yuca", "Cultivos en San Juan de Pasto",
            "https://editorial.agrosavia.co/index.php/publicaciones/catalog/book/218", R.drawable.yuca),
        Cultivo("Hortalizas", "Cultivos en San Juan de Pasto",
            "https://pdtnarino.org/lineas/cadena-hortofruticola/", R.drawable.hortalizas),
        Cultivo("Fresas", "Cultivos en San Juan de Pasto",
            "https://sired.udenar.edu.co/7217/1/Biopredador%20Diego%20Quiroz%20y%20Andres%20Yela.pdf", R.drawable.fresas),
        Cultivo("Maiz", "Cultivos en San Juan de Pasto",
            "https://www.semillas.org.co/es/el-maz-en-la-cultura-campesina-nariense", R.drawable.maiz),
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
