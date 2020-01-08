package geeko.app.wecankode.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class SearchResponse {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("cid")
    var cid: String? = null
    @SerializedName("category_name")
    var category_name: String? = null

    @SerializedName("is_active")
    var is_active: String? = null
    @SerializedName("res_msg")
    var res_msg: String? = null
    @SerializedName("res_code")
    var res_code: String? = null
    @SerializedName("data")
    @Expose
    val category: ArrayList<SearchResponse> = ArrayList<SearchResponse>()

    inner class spinnerObj {
        var id: String? = null
        var spinnervalue: String? = null
    }
}