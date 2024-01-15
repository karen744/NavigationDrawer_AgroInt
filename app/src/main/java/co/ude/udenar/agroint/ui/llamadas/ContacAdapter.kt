package co.ude.udenar.agroint.ui.llamadas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import co.ude.udenar.agroint.R

class ContacAdapter(private val context: Context, private val contacts: List<Contact>) : BaseAdapter() {

    override fun getCount(): Int {
        return contacts.size
    }

    override fun getItem(position: Int): Any {
        return contacts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.list_item_contact, parent, false)

        val contact = getItem(position) as Contact

        val nameTextView: TextView = view.findViewById(R.id.textViewContactName)
        val numberTextView: TextView = view.findViewById(R.id.textViewContactNumber)

        nameTextView.text = contact.name
        numberTextView.text = contact.phoneNumber

        // Puedes personalizar la vista más según tus necesidades

        return view
    }
}