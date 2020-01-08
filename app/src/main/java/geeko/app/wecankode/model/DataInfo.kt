package geeko.app.wecankode.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class DataInfo:Observable() {

    var stationName:String = ""
        set(value) {
            field = value
            setChangedAndNotify("stationName")
        }
/*    var mJobDescribe:String = ""
        set(value) {
            field = value
            setChangedAndNotify("JobDescribe")
        }
    var mId:String=""
        set(value){
            field = value
            setChangedAndNotify("JobId")
        }*/

    private fun setChangedAndNotify(field:Any){
        setChanged()
        notifyObservers(field)
    }
}