package android.nni.com.theredotcomandroid.activities

import android.nni.com.theredotcomandroid.CalculatorPersistService
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.ServerCallback
import android.nni.com.theredotcomandroid.beans.AdventureBean
import android.nni.com.theredotcomandroid.beans.LodgingFragmentBean
import android.nni.com.theredotcomandroid.beans.TravelFragmentBean
import android.nni.com.theredotcomandroid.entities.Adventure
import android.nni.com.theredotcomandroid.fragments.calculator.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.app_bar_main.*
import org.json.JSONObject

/**
* Created by Marcus Garmon on 2/6/2018.
*/

class CalculatorActivity : AppCompatActivity(),
        CalculatorTitleFragment.OnOptionClicked,
        CalculatorInitFragment.OnInitNextClicked,
        CalculatorTravelFragment.OnTravelNextClicked,
        CalculatorLodgingFragment.OnLodgingNextClicked,
        CalculatorFoodFragment.OnCalculateClicked,
        CalculatorResultsFragment.OnButtonClicked{

    private var adventure : AdventureBean? = null

    private var calculatorService : CalculatorPersistService? = null

    private var currentFragment : Fragment? = null

    private var hasInternet : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setSupportActionBar(toolbar)

        adventure = AdventureBean()
        calculatorService = CalculatorPersistService(this)

        if (findViewById<FrameLayout>(R.id.calculator_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            // Create a new Fragment to be placed in the activity layout
            val firstFragment = CalculatorTitleFragment()
            currentFragment = firstFragment
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.arguments = intent.extras

            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction()
                    .add(R.id.calculator_fragment_container, firstFragment).commit()
        }
    }

    private fun proceed(fragment: Fragment, step: Int) {
        val args = Bundle()

        when (step) {
            CalculatorStep.STEP_TWO_GET_STARTED ->
                System.out.println("Step Two " + adventure)
            CalculatorStep.STEP_THREE_TRANSPORTATION_SELECT ->
                System.out.println("Step Three " + adventure)
            CalculatorStep.STEP_FOUR_LODGING_BUDGET ->
                System.out.println("Step Four " + adventure)
            CalculatorStep.STEP_FIVE_RENTAL_CAR_BUDGET ->
                System.out.println("Step Five " + adventure)
            CalculatorStep.STEP_SIX_FOOD_BUDGET ->
                System.out.println("Step Six " + adventure)
            CalculatorStep.STEP_SEVEN_RESULTS_PAGE -> {
                args.putDouble("TotalBudget", calculateTotalBudget())
                System.out.println("Step Seven " + adventure)
            }
        }

        fragment.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.calculator_fragment_container, fragment)
        transaction.addToBackStack(null)

        currentFragment = fragment
        transaction.commit()
    }

    override fun onOptionClicked(option: String) {
        val fragment: Fragment

        if(option.equals("newadventure", true)){
            fragment = CalculatorInitFragment()
            adventure!!.adventureName = "New Adventure"
        } else {
            TODO("Create flow for planned excursion")
            //fragment =
        }

        proceed(fragment, CalculatorStep.STEP_TWO_GET_STARTED)
    }

    override fun onInitNextClicked(groupSize: Int, address: String, state: String, city: String ) {
        val fragment = CalculatorTravelFragment()
        adventure!!.groupSize = groupSize
        adventure!!.address = address
        adventure!!.state = state
        adventure!!.city = city

        proceed(fragment, CalculatorStep.STEP_THREE_TRANSPORTATION_SELECT)
    }

    override fun onTravelNextClicked(travelData: TravelFragmentBean) {
        val fragment = CalculatorLodgingFragment()
        adventure!!.drivingBudget = travelData.drivingBudget
        adventure!!.planeBudget = travelData.planeBudget
        adventure!!.railBudget = travelData.railBudget
        adventure!!.taxiBudget = travelData.taxiBudget
        adventure!!.trainBudget = travelData.trainBudget

        proceed(fragment, CalculatorStep.STEP_FOUR_LODGING_BUDGET)
    }

    override fun onLodgingNextClicked(lodgingData: LodgingFragmentBean) {
        val fragment = CalculatorFoodFragment()
        adventure!!.lodging = lodgingData.lodging
        adventure!!.lodgingNumOfNights = lodgingData.numberOfNights
        adventure!!.lodgingCostPerNight = lodgingData.costPerNight

        proceed(fragment, CalculatorStep.STEP_SIX_FOOD_BUDGET)
    }

    override fun onCalculateClicked(foodBudget: Double) {
        val fragment = CalculatorResultsFragment()
        System.out.println("foodBudget" + foodBudget)
        adventure!!.foodBudget = foodBudget

        proceed(fragment, CalculatorStep.STEP_SEVEN_RESULTS_PAGE)
    }

    override fun onSaveClicked() {


        if(!hasInternet)
            calculatorService?.writeAdventureToFile(adventure)
        else {
            calculatorService?.createAdventure(adventure, object : ServerCallback {
                override fun onSuccess(result: JSONObject) {
                    System.out.println("Calculator Activity : " + result.toString())
                }
            } )
        }

    }

    override fun onEditClicked() {
        val fragment = CalculatorTitleFragment()

        TODO("Grab the fun money and save it to the adventure")
        proceed(fragment, CalculatorStep.STEP_TWO_GET_STARTED)
    }

    override fun onBreakdownClicked() {
        TODO("Show popup for breakdown results")
    }

    fun onCheckboxClicked(view: View){
        if(currentFragment is CalculatorTravelFragment){
            (currentFragment as CalculatorTravelFragment).onCheckboxClicked(view)
        } else if(currentFragment is CalculatorResultsFragment){
            (currentFragment as CalculatorResultsFragment).onCheckboxClicked(view)
        }
    }

    private fun calculateTotalBudget(): Double {
        var totalBudget = 0.0

        val totalFoodBudget =
                adventure!!.groupSize * (adventure!!.foodBudget * adventure!!.lodgingNumOfNights * 3)

        System.out.println("Total Food Budget = " + totalFoodBudget)

        val totalTransportationBudget =
                adventure!!.groupSize * (adventure!!.planeBudget)

        System.out.println("Total Transportation Budget = " + totalTransportationBudget)

        val totalLodgingBudget = adventure!!.lodgingCostPerNight * adventure!!.lodgingNumOfNights

        System.out.println("Total Lodging Budget = " + totalLodgingBudget)

        totalBudget += totalLodgingBudget + totalFoodBudget + totalTransportationBudget

        return totalBudget
    }
}