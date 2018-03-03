package android.nni.com.theredotcomandroid.fragments.calculator

import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.dtos.TravelFragmentDTO
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.support.v7.widget.AppCompatCheckBox
import android.widget.Button

/**
 * Created by Marcus Garmon on 2/11/2018.
 */
class CalculatorTravelFragment : Fragment(), View.OnClickListener {

    private var checkboxPlane: AppCompatCheckBox? = null
    private var checkboxDriving: AppCompatCheckBox? = null
    private var checkboxTrain: AppCompatCheckBox? = null
    private var checkboxRail: AppCompatCheckBox? = null
    private var checkboxTaxi: AppCompatCheckBox? = null

    private var travelEditPlane: EditText? = null
    private var travelEditDriving: EditText? = null
    private var travelEditTrain: EditText? = null
    private var travelEditRail: EditText? = null
    private var travelEditTaxi: EditText? = null

    private var travelButtonNext: Button? = null

    private lateinit var mCallback: CalculatorTravelFragment.OnTravelNextClicked

    private var travelData: TravelFragmentDTO? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_travel, container, false)

        travelData = TravelFragmentDTO()

        checkboxPlane = v?.findViewById(R.id.travelCheckboxPlane)
        checkboxDriving = v?.findViewById(R.id.travelCheckboxDriving)
        checkboxTrain = v?.findViewById(R.id.travelCheckboxTrain)
        checkboxRail = v?.findViewById(R.id.travelCheckboxRail)
        checkboxTaxi = v?.findViewById(R.id.travelCheckboxTaxi)

        travelEditPlane = v?.findViewById(R.id.travelEditPlane)
        travelEditDriving = v?.findViewById(R.id.travelEditDriving)
        travelEditTrain = v?.findViewById(R.id.travelEditTrain)
        travelEditRail = v?.findViewById(R.id.travelEditRail)
        travelEditTaxi = v?.findViewById(R.id.travelEditTaxi)

        travelButtonNext = v?.findViewById(R.id.travelButtonNext)

        setupViewListeners()

        return v
    }

    fun onCheckboxClicked(view: View){
        val checked = (view as AppCompatCheckBox).isChecked

        when (view.id){
            R.id.travelCheckboxPlane ->
                travelEditPlane?.isEnabled = checked
            R.id.travelCheckboxDriving ->
                travelEditDriving?.isEnabled = checked
            R.id.travelCheckboxTrain ->
                travelEditTrain?.isEnabled = checked
            R.id.travelCheckboxRail ->
                travelEditRail?.isEnabled = checked
            R.id.travelCheckboxTaxi ->
                travelEditTaxi?.isEnabled = checked
        }
    }
    override fun onClick(v: View?) {
        if (v?.id == R.id.travelButtonNext) {
            onNextButtonClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as CalculatorTravelFragment.OnTravelNextClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnTravelNextClicked")
        }
    }

    interface OnTravelNextClicked {
        fun onTravelNextClicked(travelData: TravelFragmentDTO)
    }

    private fun onNextButtonClicked(){
        travelData!!.drivingBudget = travelEditDriving?.text.toString().toDoubleOrNull()!!

        travelData!!.planeBudget = travelEditPlane?.text.toString().toDoubleOrNull()!!

        travelData!!.railBudget = travelEditRail?.text.toString().toDoubleOrNull()!!

        travelData!!.taxiBudget = travelEditTaxi?.text.toString().toDoubleOrNull()!!

        travelData!!.trainBudget = travelEditTrain?.text.toString().toDoubleOrNull()!!

        mCallback.onTravelNextClicked(travelData!!)
    }

    private fun setupViewListeners(){
        travelButtonNext?.setOnClickListener(this)

        travelEditPlane?.setOnFocusChangeListener { v, hasFocus ->
            travelButtonNext?.isEnabled =  !travelEditPlane?.text.toString().isEmpty() && checkboxPlane?.isChecked == true
        }

        travelEditDriving?.setOnFocusChangeListener { v, hasFocus ->
            travelButtonNext?.isEnabled =  !travelEditDriving?.text.toString().isEmpty() && checkboxDriving?.isChecked == true
        }

        travelEditTrain?.setOnFocusChangeListener { v, hasFocus ->
            travelButtonNext?.isEnabled =  !travelEditTrain?.text.toString().isEmpty() && checkboxTrain?.isChecked == true
        }

        travelEditRail?.setOnFocusChangeListener { v, hasFocus ->
            travelButtonNext?.isEnabled = checkboxRail?.isChecked == true && !travelEditRail?.text.toString().isEmpty()
        }

        travelEditTaxi?.setOnFocusChangeListener { v, hasFocus ->
            travelButtonNext?.isEnabled = !travelEditTaxi?.text.toString().isEmpty() && checkboxTaxi?.isChecked == true
        }
    }
}