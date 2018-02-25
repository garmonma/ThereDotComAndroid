package android.nni.com.theredotcomandroid.beans

/**
 * Created by Marcus Garmon on 2/21/2018.
 */
data class LodgingFragmentBean(
        var numberOfNights: Int = 0,
        var costPerNight: Double = 0.0,
        var lodging: Boolean = false)
        {
}