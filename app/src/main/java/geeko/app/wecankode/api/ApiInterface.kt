package geeko.app.wecankode.api

import geeko.app.wecankode.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
//    @GET("api/v1/employees")
    @GET("api/alt-fuel-stations/v1.json")
//    fun getProjectList(): Call<List<DataModel>>
    fun getProjectList(@Query("api_key") api_key: String,
                       @Query("fuel_type") fuel_type: String,
                       @Query("state") state: String,
                       @Query("limit") limit: Int): Call<DataModel>


}