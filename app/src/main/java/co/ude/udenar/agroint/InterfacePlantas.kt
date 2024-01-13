package co.ude.udenar.agroint

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface InterfacePlantas {

    //    @GET("plants")
//    fun getData(@Query("token") token: String?): Call<List<UsersItem>>
    @GET("plants?token=")
    fun getData(
        @Header("Authorization") token: String
    ): Call<ApiResponse>

    //@GET("species-list")
    //fun getSpeciesList(
    //    @Query("key") apiKey: String,
    //    @Query("q") query: String
    //): Call<List<Data>>


}