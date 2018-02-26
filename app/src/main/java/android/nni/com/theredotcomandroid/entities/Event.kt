package android.nni.com.theredotcomandroid.entities

import java.sql.Date

/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Event: GsonEntity() {

    var id: Long? = null

    var name: String? = null

    var address: Address? = null

    var startDate: Date? = null

    var endDate: Date? = null

    var minCost: Double = 0.toDouble()

    var maxCost: Double = 0.toDouble()

    var type: String? = null
}