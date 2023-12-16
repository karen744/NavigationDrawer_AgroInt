// CultivoAdapter.kt
package co.ude.udenar.agroint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CultivoAdapter(private val cultivos: List<Cultivo>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<CultivoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
        val imagenCultivo: ImageView = itemView.findViewById(R.id.imagenCultivo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cultivo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cultivo = cultivos[position]
        holder.nombreTextView.text = cultivo.nombre
        holder.descripcionTextView.text = cultivo.descripcion

        // Cargar la imagen desde recursos locales
        holder.imagenCultivo.setImageResource(cultivo.imagenResId)

        holder.itemView.setOnClickListener {
            onItemClick(cultivo.linkMasInfo)
        }
    }

    override fun getItemCount(): Int = cultivos.size
}
