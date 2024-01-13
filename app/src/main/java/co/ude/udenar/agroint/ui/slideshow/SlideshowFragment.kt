package co.ude.udenar.agroint.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ude.udenar.agroint.InterfacePlantas
import co.ude.udenar.agroint.PlantasAdapter
import co.ude.udenar.agroint.R
import co.ude.udenar.agroint.ApiResponse
import co.ude.udenar.agroint.databinding.FragmentSlideshowBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var rvMain: RecyclerView
    private lateinit var myAdapter: PlantasAdapter

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow
        //slideshowViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}

        rvMain = root.findViewById(R.id.recycler_view)
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        myAdapter = PlantasAdapter(requireContext(), emptyList()) // Inicializa con una lista vacía
        rvMain.adapter = myAdapter

        getAllData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllData() {
        val BASE_URL = "https://trefle.io/api/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfacePlantas::class.java)

        val retroData = retrofit.getData("uujyalzcwWsJ9gerYFMSk-k3n1n9QpkG4ALcxKdT3nM")
        Log.d("aja", retroData.toString())

        retroData.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>

            ) {
                val data = response.body()!!
                Log.d("aja1", data.toString())
                myAdapter = PlantasAdapter(requireContext(), data.data)
                rvMain.adapter = myAdapter
                Log.d("data", data.toString())
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("aja", t.toString())
            }
        })
    }
}
