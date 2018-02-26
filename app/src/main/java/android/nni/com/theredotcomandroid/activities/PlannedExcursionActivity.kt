package android.nni.com.theredotcomandroid.activities

import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.entities.Account
import android.nni.com.theredotcomandroid.entities.Adventure
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorStep
import android.nni.com.theredotcomandroid.fragments.calculator.CalculatorTitleFragment
import android.nni.com.theredotcomandroid.fragments.pe.PlannedExcursionMainFragment
import android.nni.com.theredotcomandroid.services.CalculatorPersistService
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_planned_excursion.*
import org.json.JSONArray
import org.json.JSONObject



/**
* Created by Marcus Garmon on 2/21/2018.
*/
class PlannedExcursionActivity : AppCompatActivity() {

    private var calculatorService : CalculatorPersistService? = null
    private var currentFragment : Fragment? = null

    private var plannedExcursions : ArrayList<Adventure>? = null

    private var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planned_excursion)
        setSupportActionBar(toolbar)

        calculatorService = CalculatorPersistService(this)

        // Account should come from the intent from the calculator activity
        account = Account()

        calculatorService?.getAdventures(account?.id, object : JSONArrayServerCallback{
            override fun onSuccess(result: JSONArray) {
                processPlannedExcursions(result)
            }
        })

        if (findViewById<FrameLayout>(R.id.planned_excursion_fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return
            }

            val args = Bundle()

            args.putSerializable("plannedExcursions", plannedExcursions)
            // Create a new Fragment to be placed in the activity layout
            val firstFragment = PlannedExcursionMainFragment()

            currentFragment = firstFragment

            firstFragment.arguments = args

            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction()
                    .add(R.id.planned_excursion_fragment_container, firstFragment).commit()
        }
    }



    private fun proceed(fragment: Fragment, step: Int) {
        val args = Bundle()

        when (step) {

        }

        fragment.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.planned_excursion_fragment_container, fragment)
        transaction.addToBackStack(null)

        currentFragment = fragment
        transaction.commit()
    }

    private fun processPlannedExcursions(data: JSONArray){
        (0 until data.length())
                .map { data.getJSONObject(it) }
                .forEach { plannedExcursions!!.add(it as Adventure) }
    }
}