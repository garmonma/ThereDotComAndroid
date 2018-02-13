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
import android.view.View.OnFocusChangeListener



/**
 * Created by Marcus Garmon on 2/11/2018.
 */
class CalculatorInitFragment : Fragment(), View.OnClickListener  {

    private var editGroupSize : EditText? = null
    private var editAddress : EditText? = null
    private var editState : EditText? = null
    private var editCity : EditText? = null

    private var buttonNext : Button? = null

    private lateinit var mCallback: CalculatorInitFragment.OnInitNextClicked

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_init, container, false)

        editGroupSize = v?.findViewById(R.id.editGroupSize)
        editAddress = v?.findViewById(R.id.editAddress)
        editState = v?.findViewById(R.id.editState)
        editCity = v?.findViewById(R.id.editCity)
        buttonNext = v?.findViewById(R.id.initButtonNext)

        buttonNext?.setOnClickListener(this)

        editGroupSize?.setOnFocusChangeListener { v, hasFocus ->

            buttonNext?.isEnabled = !hasFocus && !editGroupSize?.text.toString().isEmpty()
        }

        return v
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.initButtonNext) {
            onNextButtonClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as OnInitNextClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnInitNextClicked")
        }
    }

    interface OnInitNextClicked {
        fun onNextClicked(groupSize: Int, address: String, state: String, city: String)
    }

    private fun onNextButtonClicked() {
        var groupSize = this.editGroupSize?.text.toString().toInt()

        mCallback.onNextClicked(
                groupSize,
                editAddress?.text.toString(),
                editState?.text.toString(),
                editCity?.text.toString())
    }
}