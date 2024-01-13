package co.ude.udenar.agroint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.R
import com.bumptech.glide.Glide

class PlantasAdapter(var con : Context , var list : List<Data>) : RecyclerView.Adapter<PlantasAdapter.ViewHolder>() {

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        var img = v.findViewById<ImageView>(R.id.RV_Image)
        var tvName = v.findViewById<TextView>(R.id.RV_tv)
        var tvFamily = v.findViewById<TextView>(R.id.FV_tv)
        var tvGenus = v.findViewById<TextView>(R.id.GV_tv)
        var tvSlug = v.findViewById<TextView>(R.id.SV_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(con).load(list[position].image_url).into(holder.img)
        val currentItem = list[position]

        holder.tvName.text = "Name: ${currentItem.common_name}"
        holder.tvFamily.text = "Family: ${currentItem.family}"
        holder.tvGenus.text = "Genus: ${currentItem.genus}"
        holder.tvSlug.text = "Order: ${currentItem.slug}"

    }

    override fun getItemCount(): Int {
        return list.count()
    }

}