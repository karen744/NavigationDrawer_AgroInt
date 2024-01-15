package co.ude.udenar.agroint.ui.ingenieros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.ui.ingenieros.Ingeniero
import co.ude.udenar.agroint.R

class IngeAdapter (private val ingenieros: List<Ingeniero>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<IngeAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
            val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
            val imagenCultivo: ImageView = itemView.findViewById(R.id.imagenCultivo)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingenieros, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val ingenieros = ingenieros[position]
            holder.nombreTextView.text = ingenieros.nombre1
            holder.descripcionTextView.text = ingenieros.descripcion1

            // Cargar la imagen desde recursos locales
            holder.imagenCultivo.setImageResource(ingenieros.imagenResId1)

            holder.itemView.setOnClickListener {
                onItemClick(ingenieros.linkMasInfo1)
            }
        }

        override fun getItemCount(): Int = ingenieros.size
    }
