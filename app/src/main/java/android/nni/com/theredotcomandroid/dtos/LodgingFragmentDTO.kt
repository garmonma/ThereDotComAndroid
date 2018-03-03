package android.nni.com.theredotcomandroid.dtos

/**
 * Created by Marcus Garmon on 2/21/2018.
 */
data class LodgingFragmentDTO(
        var numberOfNights: Int = 0,
        var costPerNight: Double = 0.0,
        var lodging: Boolean = false)
        {
}