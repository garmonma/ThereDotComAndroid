package android.nni.com.theredotcomandroid.entities

import java.sql.Date


/**
 * Created by Marcus Garmon on 3/12/2018.
 */
class Hotel : GsonEntity() {

    var id: Long? = null

    var name: String? = null

    var address: Address? = null

    var fromDate: Date? = null

    var toDate: Date? = null

    var minCost: Double = 0.toDouble()

    var maxCost: Double = 0.toDouble()

    var type: String? = null
}