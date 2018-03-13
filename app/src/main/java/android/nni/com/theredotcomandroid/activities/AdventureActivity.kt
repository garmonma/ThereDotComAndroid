package android.nni.com.theredotcomandroid.activities

import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton

import kotlinx.android.synthetic.main.activity_adventure.*

class AdventureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure)
        setSupportActionBar(toolbar)
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked

        when (view.getId()) {
            R.id.adventureAirBnb -> {

            }
            R.id.adventureHotel -> {


            }
            R.id.lodgingOtherArrangments -> {

            }
        }
    }
}