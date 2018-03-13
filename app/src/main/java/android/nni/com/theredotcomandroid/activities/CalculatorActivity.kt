package android.nni.com.theredotcomandroid.activities

import android.content.Intent
import android.nni.com.theredotcomandroid.services.AdventureService
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.services.callbacks.JSONObjectServerCallback
import android.nni.com.theredotcomandroid.dtos.AdventureDTO
import android.nni.com.theredotcomandroid.dtos.LodgingFragmentDTO
import android.nni.com.theredotcomandroid.dtos.TravelFragmentDTO
import android.nni.com.theredotcomandroid.entities.Account
import android.nni.com.theredotcomandroid.fragments.calculator.*
import android.nni.com.theredotcomandroid.services.AccountService
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*
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
    private val TAG = "Calculator Activity"

    private var adventure : AdventureDTO? = null

    private var adventureService : AdventureService? = null
    private var accountService: AccountService? = null

    private var currentFragment : Fragment? = null

    private var hasInternet : Boolean = true

    private var account : Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setSupportActionBar(toolbar)

        adventure = AdventureDTO()
        adventureService = AdventureService(this)
        accountService = AccountService(this)
        account = Account()

        if(intent.hasExtra("account")){
            Log.i(TAG, "Has Account Extra!")
            getAccount(true)
        }
        else {
            Log.i(TAG, "Does Not Have Account Extra!")
            getAccount(false)
        }


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
                Log.i(TAG, "Step Two : " + adventure)
            CalculatorStep.STEP_THREE_TRANSPORTATION_SELECT ->
                Log.i(TAG,"Step Three : " + adventure)
            CalculatorStep.STEP_FOUR_LODGING_BUDGET ->
                Log.i(TAG,"Step Four : " + adventure)
            CalculatorStep.STEP_FIVE_RENTAL_CAR_BUDGET ->
                Log.i(TAG,"Step Five : " + adventure)
            CalculatorStep.STEP_SIX_FOOD_BUDGET ->
                Log.i(TAG,"Step Six : " + adventure)
            CalculatorStep.STEP_SEVEN_RESULTS_PAGE -> {
                args.putDouble("TotalBudget", calculateTotalBudget())
                Log.i(TAG,"Step Seven : " + adventure)
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

            if(account != null){
                adventure!!.account = account!!
            }

            proceed(fragment, CalculatorStep.STEP_TWO_GET_STARTED)
        } else if(option.equals("signin", true)) {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        } else if(option.equals("plannedexcursion", true)){
            if(account?.id == null){
                Toast.makeText(this, "Please Sign In or create an account!", Toast.LENGTH_SHORT).show()
                return
            }
            val intent = Intent(this, PlannedExcursionActivity::class.java)
            intent.putExtra("account", account)
            startActivity(intent)
        }
    }

    override fun onInitNextClicked(groupSize: Int, address: String, state: String, city: String ) {
        val fragment = CalculatorTravelFragment()
        adventure!!.groupSize = groupSize
        adventure!!.address = address
        adventure!!.state = state
        adventure!!.city = city

        proceed(fragment, CalculatorStep.STEP_THREE_TRANSPORTATION_SELECT)
    }

    override fun onTravelNextClicked(travelData: TravelFragmentDTO) {
        val fragment = CalculatorLodgingFragment()
        adventure!!.drivingBudget = travelData.drivingBudget
        adventure!!.planeBudget = travelData.planeBudget
        adventure!!.railBudget = travelData.railBudget
        adventure!!.taxiBudget = travelData.taxiBudget
        adventure!!.trainBudget = travelData.trainBudget

        proceed(fragment, CalculatorStep.STEP_FOUR_LODGING_BUDGET)
    }

    override fun onLodgingNextClicked(lodgingData: LodgingFragmentDTO) {
        val fragment = CalculatorFoodFragment()
        adventure!!.lodging = lodgingData.lodging
        adventure!!.lodgingNumOfNights = lodgingData.numberOfNights
        adventure!!.lodgingCostPerNight = lodgingData.costPerNight

        proceed(fragment, CalculatorStep.STEP_SIX_FOOD_BUDGET)
    }

    override fun onCalculateClicked(foodBudget: Double) {
        val fragment = CalculatorResultsFragment()
        Log.i(TAG,"foodBudget" + foodBudget)
        adventure!!.foodBudget = foodBudget

        proceed(fragment, CalculatorStep.STEP_SEVEN_RESULTS_PAGE)
    }

    override fun onSaveClicked() {
        if(!hasInternet)
            adventureService?.writeAdventureToFile(adventure)
        else {
            adventureService?.createAdventure(adventure, object : JSONObjectServerCallback {
                override fun onSuccess(result: JSONObject) {
                    Log.i(TAG,"Calculator Activity : " + result.toString())
                }
            } )

            Toast.makeText(this, "A new adventure as been made!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, PlannedExcursionActivity::class.java)
            intent.putExtra("account", account)
            startActivity(intent)
        }
    }

    override fun onEditClicked() {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show()
        return
    }

    override fun onBreakdownClicked() {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show()
        return
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

        Log.i(TAG,"Total Food Budget = " + totalFoodBudget)

        val totalTransportationBudget =
                adventure!!.groupSize * (adventure!!.planeBudget)

        Log.i(TAG,"Total Transportation Budget = " + totalTransportationBudget)

        val totalLodgingBudget = adventure!!.lodgingCostPerNight * adventure!!.lodgingNumOfNights

        Log.i(TAG,"Total Lodging Budget = " + totalLodgingBudget)

        totalBudget += totalLodgingBudget + totalFoodBudget + totalTransportationBudget

        return totalBudget
    }

    private fun autoLogAccount(firstTimeIn: Boolean){

    }

    private fun getAccount(hasAccountExtra: Boolean){
        if(!hasAccountExtra) {
            Log.i(TAG, "In Get Account, Does not have account extra!")
            autoLogAccount(false)
            return
        }

        account = intent.getSerializableExtra("account") as Account
    }

}