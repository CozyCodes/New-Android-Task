package geeko.app.wecankode.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class DataModel {
    @SerializedName("total_results")
    @Expose
    var total_results = ""
    @SerializedName("id")
    @Expose
    var id = ""
    @SerializedName("employee_name")
    @Expose
    var employee_name = ""
    @SerializedName("employee_salary")
    @Expose
    var employee_salary = ""
    @SerializedName("employee_age")
    @Expose
    var employee_age = ""
    @SerializedName("profile_image")
    @Expose
    var profile_image = ""

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