package android.nni.com.theredotcomandroid.entities

import android.nni.com.theredotcomandroid.dtos.AdventureDto
import java.sql.Date


/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Adventure: GsonEntity() {
    var id: Long? = null

    var account: Account? = null

    var name: String? = null

    var event: Event? = null

    var date: Date? = null

    var startAddress: Address? = null

    var groupSize: Int = 0

    var foodBudget: Double = 0.toDouble()

    var transportationMethod: String? = null

    var flightCost: Double = 0.toDouble()

    var drivingCost: Double = 0.toDouble()

    var trainCost: Double = 0.toDouble()

    var railBudget: Double = 0.toDouble()

    var taxiBudget: Double = 0.toDouble()

    // The name of the place
    var lodging: String? = null

    var lodgingAddress: Address? = null

    var lodgingPerNight: Double = 0.toDouble()

    var lodgingNights: Int = 0

    var rentalCar: String? = null

    var rentalCarBudget: Double = 0.toDouble()

    var funMoney: Double = 0.toDouble()

    var emergencyBudget: Double = 0.toDouble()

    fun convertAdventureBean(adventureDto: AdventureDto){
        name = adventureDto.adventureName

        startAddress?.address = adventureDto.address
        startAddress?.city = adventureDto.city
        startAddress?.state = adventureDto.state

        groupSize = adventureDto.groupSize


        foodBudget = adventureDto.foodBudget

        flightCost = adventureDto.planeBudget
        drivingCost = adventureDto.drivingBudget
        trainCost = adventureDto.trainBudget
        railBudget = adventureDto.railBudget
        taxiBudget = adventureDto.taxiBudget

        //lodging = adventureDto.

        lodgingPerNight = adventureDto.lodgingCostPerNight
        lodgingNights = adventureDto.lodgingNumOfNights

        funMoney = adventureDto.funMoney
    }

    fun getTotalCost():Double{
        var totalBudget = 0.0

        val totalFoodBudget = groupSize * (foodBudget * lodgingNights * 3)

        val totalTransportationBudget = groupSize * (flightCost)

        val totalLodgingBudget = lodgingPerNight * lodgingNights

        totalBudget += totalLodgingBudget + totalFoodBudget + totalTransportationBudget

        return totalBudget
    }

}