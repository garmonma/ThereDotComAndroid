package android.nni.com.theredotcomandroid.fragments.calculator

import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**
 * Created by Marcus Garmon on 2/11/2018.
 */
class CalculatorLodgingFragment: Fragment(), View.OnClickListener  {


    private var costPerNight : EditText? = null
    private var amountOfNights : EditText? = null

    private var yesButton : Button? = null
    private var noButton : Button? = null
    private var nextButton : Button? = null

    private lateinit var mCallback: CalculatorLodgingFragment.OnLodgingNextClicked

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_lodging, container, false)


        amountOfNights = v?.findViewById(R.id.lodgingNights)
        costPerNight = v?.findViewById(R.id.lodgingCostPerNight)

        yesButton = v?.findViewById(R.id.lodgingButtonYes)
        noButton = v?.findViewById(R.id.lodgingButtonNo)
        nextButton = v?.findViewById(R.id.lodgingNextButton)

        yesButton?.setOnClickListener(this)
        noButton?.setOnClickListener(this)
        nextButton?.setOnClickListener(this)

        return v
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as CalculatorLodgingFragment.OnLodgingNextClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnLodgingNextClicked")
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.lodgingNextButton) {
            onNextButtonClicked()
        }

        if(v?.id == R.id.lodgingButtonNo) {
            onNoButtonClicked()
        }

        if(v?.id == R.id.lodgingButtonYes) {
            onYesButtonClicked()
        }

    }

    interface OnLodgingNextClicked {
        fun onLodgingNextClicked()
    }

    private fun onNextButtonClicked() {
        mCallback.onLodgingNextClicked()
    }

    private fun onNoButtonClicked() {

    }

    private fun onYesButtonClicked(){}

}