package android.nni.com.theredotcomandroid.entities

import android.nni.com.theredotcomandroid.beans.AdventureBean


/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Adventure: GsonEntity() {
    var id: Long? = null

    var account: Account? = null

    var name: String? = null

    var event: Event? = null

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

    fun convertAdventureBean(adventureBean: AdventureBean){
        name = adventureBean.adventureName

        startAddress?.address = adventureBean.address
        startAddress?.city = adventureBean.city
        startAddress?.state = adventureBean.state

        groupSize = adventureBean.groupSize


        foodBudget = adventureBean.foodBudget

        flightCost = adventureBean.planeBudget
        drivingCost = adventureBean.drivingBudget
        trainCost = adventureBean.trainBudget
        railBudget = adventureBean.railBudget
        taxiBudget = adventureBean.taxiBudget

        //lodging = adventureBean.

        lodgingPerNight = adventureBean.lodgingCostPerNight
        lodgingNights = adventureBean.lodgingNumOfNights

        funMoney = adventureBean.funMoney
    }
}