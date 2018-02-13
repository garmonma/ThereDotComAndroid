package android.nni.com.theredotcomandroid

import android.nni.com.theredotcomandroid.entities.AdventureEntity
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorInitFragment
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorStep
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorTitleFragment
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorTravelFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by Marcus Garmon on 2/6/2018.
 */


class CalculatorActivity : AppCompatActivity(),
        CalculatorTitleFragment.OnOptionClicked,
        CalculatorInitFragment.OnInitNextClicked{

    private var adventure : AdventureEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setSupportActionBar(toolbar)

        adventure = AdventureEntity()

        if (findViewById<FrameLayout>(R.id.calculator_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            // Create a new Fragment to be placed in the activity layout
            val firstFragment = CalculatorTitleFragment()

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
            CalculatorStep.STEP_SEVEN_RESULTS_PAGE ->
                System.out.println("Step Seven " + adventure)
        }

        fragment.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.calculator_fragment_container, fragment)
        transaction.addToBackStack(null)

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

    override fun onNextClicked(groupSize: Int, address: String, state: String, city: String ) {
        val fragment = CalculatorTravelFragment()
        adventure!!.groupSize = groupSize
        adventure!!.address = address
        adventure!!.state = state
        adventure!!.city = city

        proceed(fragment, CalculatorStep.STEP_THREE_TRANSPORTATION_SELECT)
    }
}