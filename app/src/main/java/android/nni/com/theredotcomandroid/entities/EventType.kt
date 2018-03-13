package android.nni.com.theredotcomandroid.entities

/**
* Created by Marcus Garmon on 3/10/2018.
*/
class EventType(var name: String? = null) : GsonEntity() {
    var id: Long? = null

    override fun toString(): String {
        return this!!.name!!
    }

}