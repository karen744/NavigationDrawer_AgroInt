package co.ude.udenar.agroint.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.R

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Dummy data for testing
        val itemList = listOf(
            ItemModel(R.drawable.m, "Item 1"),
            ItemModel(R.drawable.j, "Item 2"),
            ItemModel(R.drawable.v, "Item 3")
            // Add more items as needed
        )

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.HORIZONTAL, false)
        itemAdapter = ItemAdapter(itemList)
        recyclerView.adapter = itemAdapter

        return view
    }
}
