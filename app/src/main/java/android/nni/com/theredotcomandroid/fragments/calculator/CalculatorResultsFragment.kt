package android.nni.com.theredotcomandroid.fragments.calculator

import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatCheckBox
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.calculator_fragment_result.*

/**
* Created by Marcus Garmon on 2/11/2018.
*/
class CalculatorResultsFragment: Fragment(), View.OnClickListener  {

    private var budgetTotal: Double = 0.0
    private var funBudget: Double = 0.0

    private var budgetTotalView: TextView? = null
    private var checkFunBudget: AppCompatCheckBox? = null
    private var editFunBudget: EditText? = null

    private var saveButton: Button? = null
    private var editButton: Button? = null
    private var breakdownButton: Button? = null

    private lateinit var mCallback: CalculatorResultsFragment.OnButtonClicked

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_result, container, false)

        budgetTotal = arguments.getDouble("TotalBudget")
     //   budgetTotal = savedInstanceState?.getDouble("TotalBudget")!!

        checkFunBudget = v?.findViewById(R.id.checkFunMoney)
        editFunBudget = v?.findViewById(R.id.editFunMoney)
        budgetTotalView = v?.findViewById(R.id.totalBudgetView)

        budgetTotalView?.text = budgetTotal.toString()

        saveButton = v?.findViewById(R.id.saveButton)
        editButton = v?.findViewById(R.id.editButton)
        breakdownButton = v?.findViewById(R.id.costBreakdownButton)

        setupViewListeners()

        return v
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.saveButton -> onSaveButtonClicked()
            v?.id == R.id.editButton -> onEditButtonClicked()
            v?.id == R.id.costBreakdownButton -> onBreakdownButtonClicked()
        }
    }

    fun onCheckboxClicked(view: View){
        val checked = (view as AppCompatCheckBox).isChecked

        when (view.id){
            R.id.travelCheckboxPlane ->
                checkFunBudget?.isEnabled = checked
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as CalculatorResultsFragment.OnButtonClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnButtonClicked")
        }
    }

    interface OnButtonClicked{
        fun onSaveClicked()
        fun onEditClicked()
        fun onBreakdownClicked()
    }

    private fun onSaveButtonClicked(){
        mCallback.onSaveClicked()
    }

    private fun onEditButtonClicked(){
        mCallback.onEditClicked()
    }

    private fun onBreakdownButtonClicked(){
        mCallback.onBreakdownClicked()
    }

    private fun setupViewListeners() {
        saveButton?.setOnClickListener(this)
        editButton?.setOnClickListener(this)
        costBreakdownButton?.setOnClickListener(this)

        editFunBudget?.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus)
                updateFunBudget()
        }
    }

    private fun updateFunBudget(){
        funBudget = editFunBudget?.text.toString().toDoubleOrNull()!!
        budgetTotal += funBudget
        budgetTotalView?.text = budgetTotal.toString()
    }
}