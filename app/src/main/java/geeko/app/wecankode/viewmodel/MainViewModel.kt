package geeko.app.wecankode.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.SearchView
import geeko.app.wecankode.model.DataModel
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import geeko.app.wecankode.api.APIClient
import geeko.app.wecankode.api.ApiInterface
import geeko.app.wecankode.model.DataInfo
import geeko.app.wecankode.view.CustomAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {
//    var dataList: List<DataModel> = ArrayList()
lateinit var dataList:ArrayList<DataInfo>
    lateinit var dataInfo: DataInfo
//    var dataList:ArrayList<DataInfo>?=null
    private val mutablePostList: MutableLiveData<List<DataInfo>> = MutableLiveData()



    fun getProjectList(): LiveData<List<DataInfo>>? {
        dataList = ArrayList()
        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getProjectList("DEMO_KEY","E85,ELEC","CA",100)

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                val jsonResponse = response.body()
                if (jsonResponse != null) {
                    for(i in jsonResponse.fuel_stations!!.indices){
                        dataInfo = DataInfo()
                        dataInfo.stationName = jsonResponse.fuel_stations[i].station_name
                        Log.v("getProjectList", jsonResponse.fuel_stations[i].station_name)
                        dataList!!.add(dataInfo)
                    }

                    mutablePostList.postValue(dataList)
                }else{
                    Log.d("TAG","dataList = null")
                }

            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                Log.d("ERROR : ", t.toString())

            }
        })

        return mutablePostList
    }

    fun filterList(term: String, adapter: CustomAdapter) {
        if (term != "") {
            val list = adapter.originalList.filter { it.stationName.contains(term, true) }
            adapter.filterList = list
            adapter.notifyDataSetChanged()
            Log.d("filterList : ", list.toString())

        } else {
            adapter.filterList = adapter.originalList
            adapter.notifyDataSetChanged()
        }

    }

    fun getOnQueryTextChange(adapter: CustomAdapter) : SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(term: String?): Boolean {
            if (term != null) { filterList(term, adapter) }
            return false
        }
        override fun onQueryTextSubmit(term: String?): Boolean {
            if (term != null) { filterList(term, adapter) }
            return false
        }
    }

}