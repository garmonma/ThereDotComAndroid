package android.nni.com.theredotcomandroid.dtos

/**
 * Created by Marcus Garmon on 2/6/2018.
 */
data class AdventureDTO(
        var adventureName: String = "",
        var transportationMethods: List<String> = emptyList(),
        var groupSize: Int = 0,
        var address: String = "",
        var city: String = "",
        var state: String = "",
        var lodging: Boolean = false,
        var lodgingCostPerNight: Double = 0.0,
        var lodgingNumOfNights: Int = 0,
        var lodgingBudget: Double = 0.0,
        var rentalCarBudge: Double = 0.0,
        var foodBudget: Double = 0.0,
        var funMoney: Double = 0.0,
        var emergencyBudget: Double = 0.0,
        var planeBudget: Double = 0.0,
        var drivingBudget: Double = 0.0,
        var trainBudget: Double = 0.0,
        var railBudget: Double = 0.0,
        var taxiBudget: Double = 0.0){

    }