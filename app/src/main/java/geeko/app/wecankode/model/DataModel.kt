package geeko.app.wecankode.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class DataModel {
    @SerializedName("total_results")
    @Expose
    var total_results = ""

    @SerializedName("station_name")
    @Expose
    var station_name = ""

    @SerializedName("fuel_stations")
    @Expose
    var fuel_stations =
        ArrayList<DataModel>() //    public ArrayList<DataModel> getAllStations() {
//        return fuel_stations;
//    }
}