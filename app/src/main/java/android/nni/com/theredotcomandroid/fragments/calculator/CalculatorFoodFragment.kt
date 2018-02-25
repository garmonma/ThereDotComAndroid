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
class CalculatorFoodFragment : Fragment(), View.OnClickListener {

    private var foodBudget: EditText? = null
    private var calculateButton: Button? = null
    
    private lateinit var mCallback: CalculatorFoodFragment.OnCalculateClicked

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_food, container, false)

        foodBudget = v?.findViewById(R.id.calculatorFoodBudget)
        calculateButton = v?.findViewById(R.id.calculateButton)

        calculateButton?.setOnClickListener(this)

        foodBudget?.setOnFocusChangeListener { v, hasFocus ->
           // calculateButton?.isEnabled = !foodBudget?.text.toString().isEmpty()
        }
        return v
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.calculateButton){
            onCalculateButtonClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as CalculatorFoodFragment.OnCalculateClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnCalculateClicked")
        }
    }

    interface OnCalculateClicked {
        fun onCalculateClicked(foodBudget: Double)
    }

    private fun onCalculateButtonClicked(){
        System.out.println("Calculate Clicked!")
        mCallback.onCalculateClicked(foodBudget?.text.toString().toDoubleOrNull()!!)
    }
}