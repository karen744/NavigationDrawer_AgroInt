package co.ude.udenar.agroint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Plantas : AppCompatActivity() {

    lateinit var rvMain: RecyclerView
    lateinit var myAdapter: PlantasAdapter

    var BASE_URL = "https://trefle.io/api/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plantas)

        rvMain = findViewById(R.id.recycler_view)

        rvMain.layoutManager = LinearLayoutManager(this)

        myAdapter = PlantasAdapter(baseContext, emptyList()) // Inicializa con una lista vacía
        rvMain.adapter = myAdapter

        getAllData()
    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfacePlantas::class.java)


        var retroData = retrofit.getData("uujyalzcwWsJ9gerYFMSk-k3n1n9QpkG4ALcxKdT3nM")
        Log.d("aja",retroData.toString())

        retroData.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>

            ) {
                var data = response.body()!!
                Log.d("aja1",data.toString())
                myAdapter = PlantasAdapter(baseContext, data.data)

                rvMain.adapter = myAdapter

                Log.d("data", data.toString())
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("aja",t.toString())
            }

        })
    }
}