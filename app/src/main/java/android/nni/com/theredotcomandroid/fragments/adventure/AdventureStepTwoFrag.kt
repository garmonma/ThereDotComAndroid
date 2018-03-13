package android.nni.com.theredotcomandroid.fragments.adventure

import android.app.DatePickerDialog
import android.app.Fragment
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.entities.Event
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.util.*
import java.text.SimpleDateFormat


/**
* Created by Marcus Garmon on 3/11/2018.
*/
class AdventureStepTwoFrag : Fragment(), OnClickListener, DatePickerDialog.OnDateSetListener {
    private val TAG = "StepTwo-Fragment"
    private var calendar = Calendar.getInstance()

    private var fromDateView: EditText? = null
    private var toDateView: EditText? = null
    private var datePickerDialog: DatePickerDialog? = null
    private var buttonIdk: Button? = null
    private var buttonNext: Button? = null

    private lateinit var mCallback: AdventureStepTwoFrag.StepTwoListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater?.inflate(R.layout.adventure_fragment_step_two, container, false)

        fromDateView = v?.findViewById(R.id.adventureFromDate)
        toDateView = v?.findViewById(R.id.adventureToDate)

        fromDateView?.setOnClickListener(this)
        toDateView?.setOnClickListener(this)

        buttonIdk = v?.findViewById(R.id.adventureDateIdk)
        buttonNext = v?.findViewById(R.id.stepTwoNextButton)

        buttonIdk?.setOnClickListener(this)
        buttonNext?.setOnClickListener(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog = DatePickerDialog(this.context)
            datePickerDialog?.setOnDateSetListener(this)
        }

        return v
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.adventureFromDate || v?.id == R.id.adventureToDate) {
             DatePickerDialog(this.context, this, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        if(v?.id == R.id.stepOneNextButton){
            onNextClicked()
        }

        if(v?.id == R.id.adventureDateIdk){
            showNoWorriesText()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(view as EditText);
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as StepTwoListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement StepTwoListener")
        }
    }

    interface StepTwoListener {
        fun onNextClicked()
    }


    private fun updateLabel(v: EditText) {
        val myFormat = "MM/dd/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        v.setText(sdf.format(calendar.time), TextView.BufferType.EDITABLE )
    }

    private fun onNextClicked(){
        mCallback.onNextClicked()
    }

    private fun showNoWorriesText(){

    }
}