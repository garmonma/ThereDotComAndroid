package android.nni.com.theredotcomandroid.entities

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Marcus Garmon on 2/24/2018.
 */
abstract class GsonEntity: java.io.Serializable {

    fun toJSON(): JSONObject? {
        val gson = Gson()
        val json = gson.toJson(this)

        var `object`: JSONObject? = null
        try {
            `object` = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return `object`
    }

    override fun toString(): String {
        return toJSON()!!.toString()
    }
}