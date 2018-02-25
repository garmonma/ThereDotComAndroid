package android.nni.com.theredotcomandroid.entities

import java.sql.Date

/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Event: GsonEntity() {

    private val id: Long? = null

    private val name: String? = null

    private val address: Address? = null

    private val startDate: Date? = null

    private val endDate: Date? = null

    private val minCost: Double = 0.toDouble()

    private val maxCost: Double = 0.toDouble()

    private val type: String? = null
}