package android.nni.com.theredotcomandroid.entities

/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Address : GsonEntity() {

    val id: Long? = null

    var address: String? = null

    var city: String? = null

    var state: String? = null

    var country: String? = null

    var zipcode: String? = null
}