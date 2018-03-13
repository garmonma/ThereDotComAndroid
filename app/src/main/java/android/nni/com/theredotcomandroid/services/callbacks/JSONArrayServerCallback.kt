package android.nni.com.theredotcomandroid.services.callbacks

import org.json.JSONArray

/**
 * Created by Marcus Garmon on 2/25/2018.
 */
interface JSONArrayServerCallback {
    fun onSuccess(results: JSONArray);
}